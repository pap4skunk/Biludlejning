package org.example.biludlejning.controller;

import org.example.biludlejning.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CarRepository carRepository;

    public HomeController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    //http://localhost:8080/cars
    @GetMapping("/cars")
    public String getCars(Model model){
        model.addAttribute("cars", carRepository.getAllCars());
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
    public String lejeaftale(){
        return "lejeaftale";
    }
}