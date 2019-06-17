package com.dex.car_compras.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dex.car_compras.R;
import com.dex.car_compras.activity.AddListActivity;
import com.dex.car_compras.model.Product;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CustomProdAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> list = new ArrayList<Product>();
    private Context context;
    private static DecimalFormat round = new DecimalFormat("#.##");
    TextView tSub;
    TextView tName;
    TextView tValue;
    TextView tAmount;

    public CustomProdAdapter(Context context, ArrayList<Product> prods) {
        super(context, 0, prods);
        this.list = prods;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Product getItem(int pos) {
        return list.get(pos);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int p = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item_menu, parent, false);
        }

        Product prod = getItem(position);

        tName = convertView.findViewById(R.id.prod_name);
        tValue = convertView.findViewById(R.id.prod_value);
        tAmount = convertView.findViewById(R.id.prod_amount);
        tSub = convertView.findViewById(R.id.prod_subtotal);
        ImageButton deleteBtn = convertView.findViewById(R.id.btn_delete_icon);
        ImageButton addBtn = convertView.findViewById(R.id.btn_add_icon);
        ImageButton rmvBtn = convertView.findViewById(R.id.btn_remove_icon);

        tName.setText(prod.getName());
        tValue.setText("Valor: R$ " + round.format(prod.getValue()));
        tAmount.setText("X " + prod.getAmount());
        tSub.setText("- Total: R$ " + round.format(prod.getValue() * prod.getAmount()));


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                double total = 0;
                list.remove(p);

                for (int i = 0; i < list.size(); i++) {
                    total = total + list.get(i).getValue() * list.get(i).getAmount();
                }

                ((AddListActivity) context).updateTotal(total);
                notifyDataSetChanged();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double total = 0;
                int a = list.get(p).getAmount() + 1;
                list.get(p).setAmount(a);

                for (int i = 0; i < list.size(); i++) {
                    total = total + list.get(i).getValue() * list.get(i).getAmount();
                }

                ((AddListActivity) context).updateTotal(total);
                tSub.setText("- Total: R$ " + round.format(list.get(p).getValue() * list.get(p).getAmount()));
                notifyDataSetChanged();
            }
        });

        rmvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double total = 0;
                int a = list.get(p).getAmount() - 1;
                if (a <= 0) {
                    a = 1;
                } else {
                    list.get(p).setAmount(a);
                }

                for (int i = 0; i < list.size(); i++) {
                    total = total + list.get(i).getValue() * list.get(i).getAmount();
                }

                ((AddListActivity) context).updateTotal(total);
                tSub.setText("- Total: R$ " + round.format(list.get(p).getValue() * list.get(p).getAmount()));
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
