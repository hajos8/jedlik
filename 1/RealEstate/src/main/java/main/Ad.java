package main;

import java.time.LocalDate;

public class Ad {
    int id;
    String description;
    int rooms;
    int area;
    int floors;
    Category category;
    Seller seller;
    boolean freeOfCharge;
    String imageUrl;
    String latlong;
    LocalDate createdAt;

    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public int getRooms() {
        return rooms;
    }
    public int getArea() {
        return area;
    }
    public int getFloors() {
        return floors;
    }
    public Category getCategory() {
        return category;
    }
    public Seller getSeller() {
        return seller;
    }
    public boolean isFreeOfCharge() {
        return freeOfCharge;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getLatlong() {
        return latlong;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
    public void setArea(int area) {
        this.area = area;
    }
    public void setFloors(int floors) {
        this.floors = floors;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    public void setFreeOfCharge(boolean freeOfCharge) {
        this.freeOfCharge = freeOfCharge;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Ad(int id, String description, int rooms, int area, int floors, Category category, Seller seller, boolean freeOfCharge, String imageUrl, String latlong, LocalDate createdAt) {
        this.id = id;
        this.description = description;
        this.rooms = rooms;
        this.area = area;
        this.floors = floors;
        this.category = category;
        this.seller = seller;
        this.freeOfCharge = freeOfCharge;
        this.imageUrl = imageUrl;
        this.latlong = latlong;
        this.createdAt = createdAt;
    }

    public double DistanceTo(String cord) {
        String[] CoordParts = cord.split(",");
        double lat = Double.parseDouble(CoordParts[0]);
        double lon = Double.parseDouble(CoordParts[1]);

        double thisLat = Double.parseDouble(this.getLatlong().split(",")[0]);
        double thisLon = Double.parseDouble(this.getLatlong().split(",")[1]);

        double distanceLat = Math.abs(lat - thisLat);
        double distanceLon = Math.abs(lon - thisLon);
        return Math.sqrt(Math.pow(distanceLat, 2) + Math.pow(distanceLon, 2));
    }
}
