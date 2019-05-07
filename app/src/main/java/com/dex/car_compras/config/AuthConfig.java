package com.dex.car_compras.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthConfig {
    private static com.google.firebase.auth.FirebaseAuth auth;
    private static DatabaseReference firebase;

    public static com.google.firebase.auth.FirebaseAuth getAuth(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static DatabaseReference getFirebase(){
        if(firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }

        return firebase;
    }
}
