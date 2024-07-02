package com.example.csc325_firebase_webview_auth.model;

/**
 *  Resource class is a representation of the location data stored within the firebase database
 * */
public class Resource {
    //Member variables
    private String name;
    private String address;
    private String city;
    private String zipcode;
    private String state;
    private String url;
    private String hours;

    public Resource(String name, String address, String city, String zipcode, String state, String url, String hours) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.url = url;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
