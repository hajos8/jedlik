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
        double[] distances = DistanceTo(ads.getFirst().getLatlong(), "47.497912,19.040235");
        double minDistance = distances[0] + distances[1];

        for(int i = 0; i < ads.size(); i++){
            Ad ad = ads.get(i);
            if(!ad.isFreeOfCharge()){
                continue;
            }

            distances = DistanceTo(ad.getLatlong(), "47.497912,19.040235");

            double distance = distances[0] + distances[1];

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

    static double[] DistanceTo(String fromLatLong, String toLatLong){
        String[] fromParts = fromLatLong.split(",");

        double lat1 = Double.parseDouble(fromParts[0]);
        double lon1 = Double.parseDouble(fromParts[1]);

        String[] toParts = toLatLong.split(",");

        double lat2 = Double.parseDouble(toParts[0]);
        double lon2 = Double.parseDouble(toParts[1]);

        double distanceLat = Math.sqrt(Math.pow(lat1, 2) + Math.pow(lat2, 2));
        double distanceLon = Math.sqrt(Math.pow(lon1, 2) + Math.pow(lon2, 2));

        return new double[]{distanceLat, distanceLon};
    }
}
