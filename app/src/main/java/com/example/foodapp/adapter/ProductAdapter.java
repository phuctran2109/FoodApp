package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    ArrayList<Products> list;
    Context context;

    public ProductAdapter(ArrayList<Products> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_products, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Products products = list.get(position);
        holder.tvNameFood.setText(products.getName());
        holder.tvPriceFood.setText(String.valueOf(products.getPrice()));

        String img = products.getImage();
        // Convert resource name to resource ID
        int resImg = context.getResources().getIdentifier(img, "drawable", context.getPackageName());
        holder.imgFood.setImageResource(resImg);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView tvNameFood, tvPriceFood;

        public MyViewHolder(@NonNull View view) {
            super(view);
            imgFood = view.findViewById(R.id.imgFood);
            tvNameFood = view.findViewById(R.id.tvNameFood);
            tvPriceFood = view.findViewById(R.id.tvPriceFood);
        }
    }
}
