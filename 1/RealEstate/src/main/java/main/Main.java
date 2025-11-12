package main;

import java.util.ArrayList;

public class Main {
    static void main() {
        ArrayList<Ad> ads = new ArrayList<Ad>();

        String filePath = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("\\")) + "\\realestates.csv";
        ads = LoadFromCSV.readFile(filePath);

        


    }
}
