package org.example.biludlejning.repository;

import org.example.biludlejning.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Car> getAllCars() {

        String sql = "SELECT * FROM car";

        RowMapper<Car> rowMapper = (rs, rowNum) -> {
            Car car = new Car();

            car.setCarId(rs.getInt("car_id"));
            car.setBrand(rs.getString("brand"));
            car.setModel(rs.getString("model"));
            car.setStatus(rs.getString("status"));

            return car;
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}
