package com.dex.car_compras.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dex.car_compras.R;

public class LineHolder extends RecyclerView.ViewHolder {

    public TextView title, value, subtotal, amount;
    public ImageButton moreButton;
    public ImageButton lessButton;
    public ImageButton deleteButton;

    public LineHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.prod_name);
        value = (TextView) itemView.findViewById(R.id.prod_value);
        subtotal = (TextView) itemView.findViewById(R.id.prod_subtotal);
        amount = (TextView) itemView.findViewById(R.id.prod_amount);
        moreButton = (ImageButton) itemView.findViewById(R.id.btn_add_icon);
        lessButton = (ImageButton) itemView.findViewById(R.id.btn_remove_icon);
        deleteButton = (ImageButton) itemView.findViewById(R.id.btn_delete_icon);
    }
}