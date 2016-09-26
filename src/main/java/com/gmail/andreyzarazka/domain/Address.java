package com.gmail.andreyzarazka.domain;

public class Address extends Model {
    private static final long serialVersionUID = 5085148270584144342L;

    private String country;
    private String street;
    private int zipCode;
    private User user;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street == null || street.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        int minZipCode = 100000;
        int maxZipCode = 999999;
        if (zipCode < minZipCode || zipCode > maxZipCode) {
            throw new IllegalArgumentException();
        }
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
