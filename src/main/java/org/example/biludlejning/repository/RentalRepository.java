package org.example.biludlejning.repository;

import org.example.biludlejning.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}