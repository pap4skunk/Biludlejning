package org.example.biludlejning.controller;

import org.example.biludlejning.model.Car;
import org.example.biludlejning.model.Customer;
import org.example.biludlejning.model.Rental;
import org.example.biludlejning.repository.CarRepository;
import org.example.biludlejning.repository.CustomerRepository;
import org.example.biludlejning.repository.RentalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;

    public HomeController(CarRepository carRepository, CustomerRepository customerRepository, RentalRepository rentalRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }


    //http://localhost:8080/cars
    @GetMapping("/cars")
    public String getCars(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "cars";
    }


    //http://localhost:8080
    @GetMapping("/")
    public String index(){
        return "index";
    }


    //http://localhost:8080/faq
    @GetMapping("/faq")
    public String faq(){
        return "faq";
    }


    //http://localhost:8080/om-os
    @GetMapping("/om-os")
    public String omOs(){
        return "om-os";
    }

    //http://localhost:8080/login
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    //http://localhost:8080/lejeaftale
    @GetMapping("/lejeaftale")
    public String lejeaftale(Model model){

        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());

        return "lejeaftale";
    }


    @PostMapping("/createRental")
    public String createRental(@RequestParam int carId,
                               @RequestParam Long customerId,
                               String startDate,
                               String endDate){

        Car car = carRepository.findById(carId).orElse(null);
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if(car != null && customer != null){

            Rental rental = new Rental();
            rental.setCar(car);
            rental.setCustomer(customer);
            rental.setStartDate(startDate);
            rental.setEndDate(endDate);
            rental.setStatus("ACTIVE");

            rentalRepository.save(rental);

            car.setStatus("RENTED");
            carRepository.save(car);
        }

        return "redirect:/cars";
    }
}