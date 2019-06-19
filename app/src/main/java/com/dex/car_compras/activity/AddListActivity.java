package com.dex.car_compras.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dex.car_compras.R;
import com.dex.car_compras.adapter.CustomProdAdapter;
import com.dex.car_compras.config.AuthConfig;
import com.dex.car_compras.helper.Base64Custom;
import com.dex.car_compras.helper.DateUtil;
import com.dex.car_compras.model.ListProduct;
import com.dex.car_compras.model.Product;
import com.dex.car_compras.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class AddListActivity extends AppCompatActivity {
    private static DecimalFormat round = new DecimalFormat("##.##");
    private DateUtil datenow;
    private TextView fieldDate;
    private TextView tTotal;
    private double total = 0;
    private ListProduct product;
    ArrayList<Product> arrayList;
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
        arrayList = Product.listProds();
        CustomProdAdapter adapter = new CustomProdAdapter(this, arrayList);

        tTotal = findViewById(R.id.totalValueProd);

        for (int i = 0; i < arrayList.size(); i++) {
            total = total + arrayList.get(i).getAmount() * arrayList.get(i).getValue();
        }

        updateTotal(total);

        ListView listView = findViewById(R.id.recycler_view_layour_recycler);
        listView.setAdapter(adapter);
    }

    public void saveList(View view) throws JSONException {
        String[] codBar = new String[arrayList.size()];
        String name[] = new String[arrayList.size()];
        String amount[] = new String[arrayList.size()];
        String value[] = new String[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            codBar[i] = arrayList.get(i).getBarCode();
            name[i] = arrayList.get(i).getName();
            amount[i] = Integer.toString(arrayList.get(i).getAmount());
            value[i] = Double.toString(arrayList.get(i).getValue());
        }

        makJsonObject(codBar, name, amount, value);
    }

    public JSONObject makJsonObject(String codBar[], String name[], String amount[], String value[]) throws JSONException {
        FirebaseAuth auth = AuthConfig.getAuth();
        DatabaseReference firebase = AuthConfig.getFirebase();

        JSONObject obj = null;
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++) {
            obj = new JSONObject();
            try {
                obj.put("codBar", codBar[i]);
                obj.put("name", name[i]);
                obj.put("amount", amount[i]);
                obj.put("value", value[i]);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(obj);
        }

        JSONObject finalobject = new JSONObject();
        finalobject.put("" + Base64Custom.condifyBase64(auth.getCurrentUser().getEmail()) + DateUtil.dateNow(), jsonArray);
        firebase.child("listprods").push().setValue(finalobject.toString());

        return finalobject;

    }


}
