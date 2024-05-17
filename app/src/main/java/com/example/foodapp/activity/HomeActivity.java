package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapter.ProductAdapter;
import com.example.foodapp.dao.ProductDAO;
import com.example.foodapp.model.Products;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    FloatingActionButton btnAddProduct;
    private ArrayList<Products> listProduct;
    private ProductDAO productDAO;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rcvProduct = findViewById(R.id.rcvProduct);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        productDAO = new ProductDAO(this);
        listProduct = productDAO.getAll();
        productAdapter = new ProductAdapter(listProduct, this);
        rcvProduct.setAdapter(productAdapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
