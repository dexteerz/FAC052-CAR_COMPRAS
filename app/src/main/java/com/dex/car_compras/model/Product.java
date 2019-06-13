package com.dex.car_compras.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    private String barCode;
    private String name;
    private String category;
    private double value;
    private int amount = 1;
    public static ArrayList<Product> prods = new ArrayList<>();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("products");

    public Product(){

    }

    public Product(String n, String c, Double v, int a) {
        this.name = n;
        this.category = c;
        this.value = v;
        this.amount = a;
    }

    @Exclude
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static ArrayList<Product> getProds() {
        return prods;
    }

    public void setProds(String n, String c, Double v, int a){
        prods.add(new Product(n,c,v,a));
    }

}
