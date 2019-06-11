package com.dex.car_compras.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dex.car_compras.R;
import com.dex.car_compras.helper.DateUtil;
import com.dex.car_compras.helper.LineAdapter;
import com.dex.car_compras.model.Product;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AddListActivity extends AppCompatActivity {
    private TextView fieldDate;
    RecyclerView mRecyclerView;
    private LineAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        fieldDate = findViewById(R.id.dateNow);

        fieldDate.setText(DateUtil.dateNow());
    }

    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapter(new ArrayList<>(0));
        mRecyclerView.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    public void addScan(View view) {
        startActivity(new Intent(this, ScanActivity.class));
    }
}
