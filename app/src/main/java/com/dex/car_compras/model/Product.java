package com.dex.car_compras.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Product {
    private int barCode;
    private String name;
    private String category;
    private double value;
    private int amount = 1;

    final FirebaseDatabase databse = FirebaseDatabase.getInstance();
    DatabaseReference ref = databse.getReference("products");

    public Product(String n, String c, Double v, int a) {
        this.name = n;
        this.category = c;
        this.value = v;
        this.amount = a;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static ArrayList<Product> getProds() {
        ArrayList<Product> prods = new ArrayList<>();
        prods.add(new Product("Sabão", "Limpeza", 5.0, 3));
        prods.add(new Product("Sabonete", "Limpeza", 3.5, 5));
        prods.add(new Product("Saco de pão", "Alimpento", 5.5, 2));
        return prods;
    }
}
