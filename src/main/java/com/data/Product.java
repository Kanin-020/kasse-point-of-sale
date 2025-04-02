package com.data;

public class Product {

    private int code;
    private String name;
    private String category;
    private int quantity;
    private double supplierCost;
    private double costOfSale;

    public Product(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public Product(int code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public Product(String name, String category, int quantity, double supplierCost, double costOfSale) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.supplierCost = supplierCost;
        this.costOfSale = costOfSale;
    }

    public Product(int code, String name, String category, int quantity, double supplierCost, double costOfSale) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.supplierCost = supplierCost;
        this.costOfSale = costOfSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSupplierCost() {
        return supplierCost;
    }

    public void setSupplierCost(double supplierCost) {
        this.supplierCost = supplierCost;
    }

    public double getCostOfSale() {
        return costOfSale;
    }

    public void setCostOfSale(double costOfSale) {
        this.costOfSale = costOfSale;
    }

    @Override
    public String toString() {
        return "Product [code=" + code + ", name=" + name + ", category=" + category + ", quantity=" + quantity
                + ", supplierCost=" + supplierCost + ", costOfSale=" + costOfSale + "]";
    }

}
