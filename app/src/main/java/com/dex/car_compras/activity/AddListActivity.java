package com.dex.car_compras.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.dex.car_compras.R;
import com.dex.car_compras.adapter.CustomProdAdapter;
import com.dex.car_compras.helper.DateUtil;
import com.dex.car_compras.model.Product;
import com.dex.car_compras.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class AddListActivity extends AppCompatActivity {
    private TextView fieldDate;
    // TESTES
    private double total = 0;
    private ListView listProd;
    private TextView nomeProd, valueSub, valueAmount, amount;
    private ImageButton moreBtn, lessBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        populateList();
    }

    private void populateList() {
        ArrayList<Product> arrayList = Product.getProds();
        CustomProdAdapter adapter = new CustomProdAdapter(this, arrayList);

        ListView listView = findViewById(R.id.recycler_view_layour_recycler);
        listView.setAdapter(adapter);

        TextView tTotal = findViewById(R.id.totalValueProd);
        total = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            total = total + arrayList.get(i).getAmount() * arrayList.get(i).getValue();
        }

        tTotal.setText("R$ " + total);
    }


    public void addScan(View view) {
        Intent i = new Intent(this, ScanActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();

        populateList();
    }

}
