package com.example.myapplication.model;

public class Product_model {

    private int productID;
    private String productName;
    private String barcode;
    private String category;
    private double price;
    private int stock;
    private String dateAdded;

    private int UpperCountProduct;

    private String txtCustomerName;

private int txtBalance;
    private int txtContact;


    private int InventoryProductCount;

    private int txtUtangCount;

    public int getTxtUtangCount() {
        return txtUtangCount;
    }

    public void setTxtUtangCount(int txtUtangCount) {
        this.txtUtangCount = txtUtangCount;
    }

    public int getUpperCountProduct() {
        return UpperCountProduct;
    }

    public void setUpperCountProduct(int upperCountProduct) {
        UpperCountProduct = upperCountProduct;
    }



    public int getInventoryProductCount() {
        return InventoryProductCount;
    }

    public void setInventoryProductCount(int inventoryProductCount) {
        InventoryProductCount = inventoryProductCount;
    }

    // Empty constructor
    public Product_model() {
    }


    // Constructor with all product information
    public Product_model(
            int productID,
            String productName,
            String barcode,
            String category,
            double price,
            int stock,
            String dateAdded) {

        this.productID = productID;
        this.productName = productName;
        this.barcode = barcode;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.dateAdded = dateAdded;
    }


    // Getters

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getDateAdded() {
        return dateAdded;
    }


    // Setters

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}