package main;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadFromCSV {
    public static ArrayList<Ad> readFile(String filePath) {
        try{
            File csvFile = new File(filePath);

            Scanner sc = new Scanner(csvFile);

            ArrayList<Ad> list = new ArrayList<>();

            sc.nextLine(); // skip header

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(";");

                int id = Integer.parseInt(parts[0]);
                int rooms = Integer.parseInt(parts[1]);
                String latlong = parts[2];
                int floors = Integer.parseInt(parts[3]);
                int area = Integer.parseInt(parts[4]);
                String description = parts[5];
                boolean freeCharge = Boolean.parseBoolean(parts[6]);
                String imgUrl = parts[7];
                LocalDate createAt = LocalDate.parse(parts[8]);
                Seller seller = new Seller(Integer.parseInt(parts[9]), parts[10], parts[11]); //id, name, phone
                Category category = new Category(Integer.parseInt(parts[12]), parts[13]); //id, name

                Ad ad = new Ad(id, description, rooms, area, floors, category, seller, freeCharge, imgUrl, latlong, createAt);

                list.add(ad);
            }

            return list;
        }
        catch (Exception e){
            System.out.println("An error occurred while reading the file: " + e.getMessage());

            return new ArrayList<>();
        }
    }
}
