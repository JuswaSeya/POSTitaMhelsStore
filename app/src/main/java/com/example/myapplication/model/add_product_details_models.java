package com.example.myapplication.model;

public class add_product_details_models {

    private int ProductID;
private int stock;
    private String barcode;
    private String category;
    private double price;
    private String etDateExpiration;




    private String productname;

    public String getBarcode() {
        return barcode;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

public String getEtDateExpiration() {
    return etDateExpiration;
}

public void setEtDateExpiration(String etDateExpiration) {
    this.etDateExpiration = etDateExpiration;
}

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }
}
