package main;

import java.util.ArrayList;

public class Main {
    static ArrayList<Ad> ads = new ArrayList<Ad>();

    static void main() {
        String filePath = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("\\")) + "\\realestates.csv";
        ads = LoadFromCSV.readFile(filePath);

        System.out.print("1. Földszinti igatlanok átlagos alapterülete: ");
        atlagTerulet(0);

        // legkisebb távolság megkeresése
        int minIndex = 0;
        double minDistance = ads.getFirst().DistanceTo("47.4164220114023,19.066342425796986");

        for(int i = 0; i < ads.size(); i++){
            Ad ad = ads.get(i);

            if(!ad.isFreeOfCharge()){
                continue;
            }

            double distance = ad.DistanceTo("47.4164220114023,19.066342425796986");

            if(distance < minDistance){
                minDistance = distance;
                minIndex = i;
            }
        }

        // legközelebbi ingatlan kiírása
        Ad closestAd = ads.get(minIndex);

        System.out.println("2. Mesevár óvodához légvonalban a legközelebbi tehermentes ingalan adati: ");

        System.out.println("\t" + "Eladó neve\t\t: " + closestAd.getSeller().getName());
        System.out.println("\t" + "Eladó telefonja\t: " + closestAd.getSeller().getPhone());
        System.out.println("\t" + "Alapterület\t\t: " + closestAd.getArea());
        System.out.println("\t" + "Szobák száma\t: " + closestAd.getRooms());


    }

    static void atlagTerulet(int floor){
        int sum = 0;
        int count = 0;

        for(Ad ad : ads){
            if(ad.getFloors() == floor){
                count++;
                sum += ad.getArea();
            }
        }

        System.out.println(String.format("%.2f", (double)sum / count) + " m2");
    }

}
