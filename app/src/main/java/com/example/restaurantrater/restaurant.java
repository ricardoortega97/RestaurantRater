package com.example.restaurantrater;

public class restaurant {

    private int restaurantid;
    private String name;
    private String streetaddress;
    private String city;
    private String state;
    private String zipcode;

    public restaurant() {
        restaurantid = -1;
    }
    public int getRestaurantid() {
        return restaurantid;
    }
    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }
    public String getStreetaddress() {
        return streetaddress;
    }
    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
