package com.example.myapplication.model;

public class add_new_utang {
    public String person_name;
    public int contact_number;
    public String what_utang;

    public Double Amount;
    public String date_borrowed;
    public String due_date;
    public String notes;

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public int getContact_number() {
        return contact_number;
    }

    public void setContact_number(int contact_number) {
        this.contact_number = contact_number;
    }

    public String getWhat_utang() {
        return what_utang;
    }

    public void setWhat_utang(String what_utang) {
        this.what_utang = what_utang;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getDate_borrowed() {
        return date_borrowed;
    }

    public void setDate_borrowed(String date_borrowed) {
        this.date_borrowed = date_borrowed;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
