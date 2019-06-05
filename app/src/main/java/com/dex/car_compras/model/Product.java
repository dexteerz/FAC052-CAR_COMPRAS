package com.dex.car_compras.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class Product {
    private int barCode;
    private String name;
    private String category;
    private double value;

    final FirebaseDatabase databse = FirebaseDatabase.getInstance();
    DatabaseReference ref = databse.getReference("products");

    public void Product(String name, String category, double value){
        setName(name);
        setCategory(category);
        setValue(value);
    }

    @Exclude
    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String product) {
        this.name = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
