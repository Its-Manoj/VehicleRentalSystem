package com.VehicleRentalSystem;

import java.sql.*;
import java.sql.Timestamp;

public class RentalService {

    // Generate next customer ID like CU001, CU002...
    public String generateNextCustomerId() {
        String prefix = "CU";
        int maxId = 0;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT customer_id FROM customer";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("customer_id");
                if (id.startsWith(prefix)) {
                    try {
                        int num = Integer.parseInt(id.substring(prefix.length()));
                        if (num > maxId) {
                            maxId = num;
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prefix + String.format("%03d", maxId + 1);
    }

    // Add customer only if not already present
    public void addCustomerIfNotExists(Customer c) {
        try (Connection conn = DBConnection.getConnection()) {
            String checkSql = "SELECT customer_id FROM customer WHERE customer_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, c.getCustomerId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("⚠️ Customer with ID " + c.getCustomerId() + " already exists.");
            } else {
                String insertSql = "INSERT INTO customer (customer_id, name) VALUES (?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setString(1, c.getCustomerId());
                insertStmt.setString(2, c.getName());
                insertStmt.executeUpdate();
                System.out.println("✅ Customer added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch vehicle by ID from DB
    public Vehicle getVehicleByIdFromDB(String vehicleId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM vehicle WHERE vehicle_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicleId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("vehicle_name");
                String brand = rs.getString("brand");
                String type = rs.getString("type");

                if (type.equalsIgnoreCase("Car")) {
                    return new Car(vehicleId, name, brand);
                } else if (type.equalsIgnoreCase("Bike")) {
                    return new Bike(vehicleId, name, brand);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Rent vehicle with timestamp
    public void rentVehicleToDB(Customer c, Vehicle v, int days) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO rental (customer_id, vehicle_id, days, rental_time) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCustomerId());
            ps.setString(2, v.getVehicleId());
            ps.setInt(3, days);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            System.out.println("✅ Rental recorded in database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show all vehicles
    public void showVehiclesFromDB() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM vehicle";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("\n--- Available Vehicles ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("vehicle_id") +
                                   ", Name: " + rs.getString("vehicle_name") +
                                   ", Brand: " + rs.getString("brand") +
                                   ", Type: " + rs.getString("type") +
                                   ", Rent/Day: ₹" + rs.getDouble("rent_per_day"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show rental history with timestamp
    public void showRentalHistoryFromDB() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT r.rental_id, c.name, v.vehicle_name, v.brand, r.days, v.rent_per_day, r.rental_time " +
                         "FROM rental r JOIN customer c ON r.customer_id = c.customer_id " +
                         "JOIN vehicle v ON r.vehicle_id = v.vehicle_id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("\n--- Rental History ---");
            while (rs.next()) {
                double cost = rs.getInt("days") * rs.getDouble("rent_per_day");
                Timestamp rentalTime = rs.getTimestamp("rental_time");
                System.out.println("Rental ID: " + rs.getInt("rental_id") +
                                   ", Customer: " + rs.getString("name") +
                                   ", Vehicle: " + rs.getString("vehicle_name") +
                                   ", Brand: " + rs.getString("brand") +
                                   ", Days: " + rs.getInt("days") +
                                   ", Cost: ₹" + cost +
                                   ", Rented On: " + rentalTime.toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}