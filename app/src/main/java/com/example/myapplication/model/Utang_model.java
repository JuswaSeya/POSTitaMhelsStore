package com.example.myapplication.model;

public class Utang_model {
    private String fullname;
    private String contactNumber;
    private String address;
    private int id;


    public Utang_model(String fullname, String contactNumber, String address, int id) {
        this.fullname = fullname;
        this.contactNumber = contactNumber;
        this.address = address;
        this.id = id;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}