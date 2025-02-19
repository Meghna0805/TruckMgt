package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TruckService {

    // Method to add a truck to the database
    public void addTruck(Truck truck) {
        String sql = "INSERT INTO truck (name, model, capacity, driver_name) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = ConnectionDetails.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, truck.getName());
            preparedStatement.setString(2, truck.getModel());
            preparedStatement.setInt(3, truck.getCapacity());
            preparedStatement.setString(4, truck.getDriver_name());

            int update = preparedStatement.executeUpdate();
            System.out.println("Rows Inserted: " + update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get truck details by ID
    public Truck getTruckById(int id) {
        String sql = "SELECT * FROM truck WHERE id = ?";
        Truck truck = new Truck();
        try {
            Connection connection = ConnectionDetails.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                truck.setId(resultSet.getInt("id"));
                truck.setName(resultSet.getString("name"));
                truck.setModel(resultSet.getString("model"));
                truck.setCapacity(resultSet.getInt("capacity"));
                truck.setDriver_name(resultSet.getString("driver_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return truck;
    }

    // Method to update an existing truck's details
    public void updateTruck(Truck truck) {
        String sql = "UPDATE truck SET name = ?, model = ?, capacity = ?, driver_name = ? WHERE id = ?";
        try {
            Connection connection = ConnectionDetails.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, truck.getName());
            preparedStatement.setString(2, truck.getModel());
            preparedStatement.setInt(3, truck.getCapacity());
            preparedStatement.setString(4, truck.getDriver_name());
            preparedStatement.setInt(5, truck.getId());

            int update = preparedStatement.executeUpdate();
            System.out.println("Rows Updated: " + update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to fetch all trucks from the database
    public List<Truck> getAllTrucks() {
        String sql = "SELECT * FROM truck";
        List<Truck> trucks = new ArrayList<>();
        try {
            Connection connection = ConnectionDetails.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Truck truck = new Truck();
                truck.setId(resultSet.getInt("id"));
                truck.setName(resultSet.getString("name"));
                truck.setModel(resultSet.getString("model"));
                truck.setCapacity(resultSet.getInt("capacity"));
                truck.setDriver_name(resultSet.getString("driver_name"));
                trucks.add(truck);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trucks;
    }

    // Method to delete a truck by ID
    public int deleteTruck(int id) {
        String sql = "DELETE FROM truck WHERE id = ?";
        try {
            Connection connection = ConnectionDetails.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int update = preparedStatement.executeUpdate();
            System.out.println("Rows Deleted: " + update);
            return update;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
