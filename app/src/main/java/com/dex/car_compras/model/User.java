package com.dex.car_compras.model;

import com.dex.car_compras.config.AuthConfig;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class User {
    private String idUser;
    private String name;
    private String email;
    private String pass;
    private Double valorTotal = 0.0;

    public User(){

    }

    public void save(){
        DatabaseReference firebase = AuthConfig.getFirebase();
        firebase.child("users")
                .child(this.idUser)
                .setValue(this);
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Exclude
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
