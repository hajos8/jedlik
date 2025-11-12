package main.realestategui;

import main.realestategui.data.Ad;
import main.realestategui.data.Seller;
import main.realestategui.data.Category;

import java.sql.*;
import java.util.ArrayList;

public class MySQLHandler {
    public static Connection conn;

    public static Connection createConnection() {
        String url = "jdbc:mysql://localhost:3306/ingatlan";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Connection successful!");
            return conn;
        }
        catch (Exception e) {
            System.out.println("Creating connection failed.");
            return null;
        }
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed.");
            }
        }
        catch (SQLException e) {
            System.out.println("Closing connection failed.");
        }
    }

    public static ArrayList<Ad> getData(){
        String queryStr =
                "select realestates.id, " +
                        "realestates.description, " +
                        "realestates.rooms, " +
                        "realestates.area, " +
                        "realestates.floors, " +
                        "categories.id, " +
                        "categories.name, " +
                        "sellers.id, " +
                        "sellers.name, " +
                        "sellers.phone, " +
                        "realestates.freeofcharge, " +
                        "realestates.imageUrl, " +
                        "realestates.latlong, " +
                        "realestates.createAt " +
                        "from realestates " +
                        "inner join categories on realestates.categoryId = categories.id " +
                        "inner join sellers on realestates.sellerId = sellers.id;";

        try {
            PreparedStatement stmt = conn.prepareStatement(queryStr);
            ResultSet resultSet = stmt.executeQuery();

            ArrayList<Ad> ads = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("realestates.id");
                String description = resultSet.getString("realestates.description");
                int rooms = resultSet.getInt("realestates.rooms");
                int area = resultSet.getInt("realestates.area");
                int floors = resultSet.getInt("realestates.floors");
                Category category = new Category(
                        resultSet.getInt("categories.id"),
                        resultSet.getString("categories.name"));
                Seller seller = new Seller(
                        resultSet.getInt("sellers.id"),
                        resultSet.getString("sellers.name"),
                        resultSet.getString("sellers.phone"));
                boolean freeOfCharge = resultSet.getBoolean("realestates.freeofcharge");
                String imageUrl = resultSet.getString("realestates.imageUrl");
                String latlong = resultSet.getString("realestates.latlong");
                Date createdAt = resultSet.getDate("realestates.createAt");

                ads.add(new Ad(
                        id,
                        description,
                        rooms,
                        area,
                        floors,
                        category,
                        seller,
                        freeOfCharge,
                        imageUrl,
                        latlong,
                        createdAt.toLocalDate()
                ));
            }

            return ads;

        }
        catch (Exception e) {
            System.out.println("Data retrieval failed.");
            return new ArrayList<Ad>();
        }
    }

    public static ArrayList<String[]> getNames(){
        String queryStr =
                "SELECT name, phone FROM `sellers`";

        try {
            PreparedStatement stmt = conn.prepareStatement(queryStr);
            ResultSet resultSet = stmt.executeQuery();

            ArrayList<String[]> names = new ArrayList<>();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                names.add(new String[] {name, phone});
            }
            return names;
        }
        catch(Exception e){
            System.out.println("Data retrieval failed.");
            return new ArrayList<String[]>();
        }
    }
}
