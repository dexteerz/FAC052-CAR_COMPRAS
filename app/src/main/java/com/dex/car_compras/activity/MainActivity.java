package com.dex.car_compras.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dex.car_compras.R;
import com.dex.car_compras.config.AuthConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_orange_light)
                .fragment(R.layout.intro_1)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_green_light)
                .fragment(R.layout.intro_2)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.intro_3)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.primary_text_dark)
                .canGoForward(false)
                .canGoBackward(false)
                .fragment(R.layout.intro)
                .build()
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        verifyLoginUser();
    }

    public void btEnter(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btRegister(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void verifyLoginUser(){
        auth = AuthConfig.getAuth();
        //auth.signOut();
        if(auth.getCurrentUser() != null){
            openHomeActivity();
        }
    }

    public void openHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
