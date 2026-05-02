package repositories;

import Models.Car;
import Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CarRepository {

    public void createCar(Car car) throws SQLException {
        Connection database = new ConnectionManager().getConnection();

        try {
            PreparedStatement preparedStatement = database.prepareStatement(
                    "INSERT INTO bil (bil_id, vognummer, stelnummer, maerke, model, nummerplade, status, lokation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            preparedStatement.setInt(1, car.getBil_id());
            preparedStatement.setString(2, car.getVognummer());
            preparedStatement.setString(3, car.getStelnummer());
            preparedStatement.setString(4, car.getMaerke());
            preparedStatement.setString(5, car.getModel());
            preparedStatement.setString(6, car.getNummerplade());
            preparedStatement.setString(7, car.getStatus().name());
            preparedStatement.setString(8, car.getLokation());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Kunne ikke oprette bil");
        }
    }

    public String getMostRentedBrand() throws SQLException {
        Connection database = new ConnectionManager().getConnection();


        PreparedStatement preparedStatement = database.prepareStatement(
                "SELECT maerke, COUNT(*) as antal " +
                "FROM bil WHERE status = 'UDLEJET' " +
                "GROUP BY maerke ORDER BY antal DESC LIMIT 1"
        );


        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return rs.getString("maerke");
        }
        return null;
    }
}
