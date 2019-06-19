package com.dex.car_compras.model;

import com.dex.car_compras.config.AuthConfig;
import com.dex.car_compras.helper.Base64Custom;
import com.dex.car_compras.helper.DateUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product implements Serializable {
    private String barCode;
    private String name;
    private String category;
    private double value;
    private int amount = 1;
    public static ArrayList<Product> prods = new ArrayList<>();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("products");

    public Product() {

    }

    public Product(String cod, String n, String c, Double v, int a) {
        this.barCode = cod;
        this.name = n;
        this.category = c;
        this.value = v;
        this.amount = a;
    }

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

    @Exclude
    public static ArrayList<Product> listProds() {
        return prods;
    }

    public void newProds(String cod, String n, String c, Double v, int a) {
        prods.add(new Product(cod, n, c, v, a));
    }

    public void updateProds(int i, int a) {
        prods.get(i).setAmount(a);
    }

    public static void setProds(ArrayList<Product> prods) {
        Product.prods = prods;
    }

}
