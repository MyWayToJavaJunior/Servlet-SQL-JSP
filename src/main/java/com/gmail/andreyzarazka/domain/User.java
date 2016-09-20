package com.gmail.andreyzarazka.domain;

import java.util.List;

public class User extends Model {
    private static final long serialVersionUID = 9196757935541515410L;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private Role role;
    private Address address;
    private List<MusicType> musicTypes;
    private int roleId;
    private int addressId;

    public User() {
    }

    public User(int id, String login, String password, String firstName, String lastName, int age) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String login, String password, String firstName, String lastName, int age, int roleId, int addressId) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.addressId = addressId;
        this.roleId = roleId;
    }

    public User(int id, String login, String password, String firstName, String lastName, int age, int roleId, int addressId) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.addressId = addressId;
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        int minAge = 0;
        int maxAge = 150;
        if (age <= minAge || age >= maxAge) {
            throw new IllegalArgumentException();
        }
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<MusicType> getMusicTypes() {
        return musicTypes;
    }

    public void setMusicTypes(List<MusicType> musicTypes) {
        this.musicTypes = musicTypes;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        if (addressId <= 0) {
            throw new IllegalArgumentException();
        }
        this.addressId = addressId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        if (roleId <= 0) {
            throw new IllegalArgumentException();
        }
        this.roleId = roleId;
    }
}
