package com.dex.car_compras.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dex.car_compras.R;
import com.dex.car_compras.model.Product;

import java.util.ArrayList;

public class CustomProdAdapter extends ArrayAdapter<Product> {

    public CustomProdAdapter(Context context, ArrayList<Product> prods) {
        super(context, 0, prods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item_menu, parent, false);
        }

        Product prod = getItem(position);

        TextView tName = convertView.findViewById(R.id.prod_name);
        TextView tValue = convertView.findViewById(R.id.prod_value);
        TextView tAmount = convertView.findViewById(R.id.prod_amount);
        TextView tSub = convertView.findViewById(R.id.prod_subtotal);

        tName.setText(prod.getName());
        tValue.setText("Valor: R$ " + prod.getValue());
        tAmount.setText("X " + prod.getAmount());
        tSub.setText("- Total: R$ " + prod.getValue() * prod.getAmount());

        return convertView;
    }
}
