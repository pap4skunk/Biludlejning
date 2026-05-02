package org.example.biludlejning.repository;

import org.example.biludlejning.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}