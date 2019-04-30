package com.dex.car_compras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new SimpleSlide.Builder()
                .title("Titulo 1")
                .description("Descrição 1")
                .image(R.drawable.um)
                .background(android.R.color.holo_orange_light)
                .backgroundDark(android.R.color.holo_orange_dark)
                .build()
        );

        addSlide(new SimpleSlide.Builder()
                .title("Titulo 2")
                .description("Descrição 2")
                .image(R.drawable.dois)
                .background(android.R.color.holo_orange_light)
                .backgroundDark(android.R.color.holo_orange_dark)
                .build()
        );

        addSlide(new SimpleSlide.Builder()
                .title("Titulo 3")
                .description("Descrição 3")
                .image(R.drawable.tres)
                .background(android.R.color.holo_orange_light)
                .backgroundDark(android.R.color.holo_orange_dark)
                .build()
        );

        addSlide(new SimpleSlide.Builder()
                .title("Titulo 4")
                .description("Descrição 4")
                .image(R.drawable.quatro)
                .background(android.R.color.holo_orange_light)
                .backgroundDark(android.R.color.holo_orange_dark)
                .build()
        );
    }
}
