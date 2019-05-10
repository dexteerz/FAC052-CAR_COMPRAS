package com.dex.car_compras.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dex.car_compras.R;
import com.dex.car_compras.helper.DateUtil;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AddListActivity extends AppCompatActivity {
    private TextView fieldDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        fieldDate = findViewById(R.id.dateNow);

        fieldDate.setText(DateUtil.dateNow());
    }

    public void addScan(View view) {
        startActivity(new Intent(this, ScanActivity.class));
    }
}
