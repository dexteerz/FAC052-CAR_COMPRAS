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

import java.text.DecimalFormat;
import java.util.ArrayList;


public class AddListActivity extends AppCompatActivity {
    private static DecimalFormat round = new DecimalFormat("##.##");
    private TextView fieldDate;
    private TextView tTotal;
    private double total = 0;
    // TESTES
    private ImageButton moreBtn, lessBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        populateList();
    }

    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        populateList();
    }

    public void addScan(View view) {
        Intent i = new Intent(this, ScanActivity.class);
        startActivityForResult(i, 1);
    }

    public void updateTotal(double total) {
        this.total = 0;
        tTotal.setText("R$ " + round.format(total));
    }

    private void populateList() {
        ArrayList<Product> arrayList = Product.listProds();
        CustomProdAdapter adapter = new CustomProdAdapter(this, arrayList);

        tTotal = findViewById(R.id.totalValueProd);

        for (int i = 0; i < arrayList.size(); i++) {
            total = total + arrayList.get(i).getAmount() * arrayList.get(i).getValue();
        }

        updateTotal(total);

        ListView listView = findViewById(R.id.recycler_view_layour_recycler);
        listView.setAdapter(adapter);
    }

    public void saveList(View view) {

    }

}
