package com.example.myapplication.model;

public class add_new_utang_model {
    public String fullname;
    public String contactNumber;
    public String address;

    public add_new_utang_model(String fullname, String contactNumber, String address) {
        this.fullname = fullname;
        this.contactNumber = contactNumber;
        this.address = address;
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
}
