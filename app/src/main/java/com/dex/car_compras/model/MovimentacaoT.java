package com.dex.car_compras.model;

import com.dex.car_compras.config.AuthConfig;
import com.dex.car_compras.helper.Base64Custom;
import com.dex.car_compras.helper.DateUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MovimentacaoT {
    private String date, category, description, type;
    private double value;

    public MovimentacaoT() {

    }

    public void salvar(String d){
        FirebaseAuth auth = AuthConfig.getAuth();
        DatabaseReference firebase = AuthConfig.getFirebase();
        firebase.child("movimentacao")
                .child(Base64Custom.condifyBase64(auth.getCurrentUser().getEmail()))
                .child(DateUtil.dateSaveFormat(d))
                .push()
                .setValue(this);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
