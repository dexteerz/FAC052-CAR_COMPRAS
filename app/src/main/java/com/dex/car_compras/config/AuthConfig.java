package com.dex.car_compras.config;

import com.google.firebase.auth.FirebaseAuth;

public class AuthConfig {
    private static com.google.firebase.auth.FirebaseAuth auth;

    public static com.google.firebase.auth.FirebaseAuth getAuth(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
