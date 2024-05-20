package com.example.foodapp.fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.ProductAdminAdapter;
import com.example.foodapp.dao.ProductDAO;
import com.example.foodapp.model.Products;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductEditFragment extends Fragment {
    private RecyclerView rcvProduct;
    private FloatingActionButton btnAddProduct;
    private ArrayList<Products> listProduct;
    private ProductDAO productDAO;
    private ProductAdminAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_product, container, false);

        rcvProduct = view.findViewById(R.id.rcvProduct);
        btnAddProduct = view.findViewById(R.id.btnAddProduct);
        rcvProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        productDAO = new ProductDAO(getContext());
        listProduct = productDAO.getAll();
        productAdapter = new ProductAdminAdapter(listProduct, getContext());
        rcvProduct.setAdapter(productAdapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductDialog();
            }
        });

        return view;
    }

    private void showAddProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_product_dialog, null);
        builder.setView(view);

        EditText etProductName = view.findViewById(R.id.etProductName);
        EditText etProductPrice = view.findViewById(R.id.etProductPrice);
        EditText etProductDescription = view.findViewById(R.id.etProductDescription);
        EditText etProductType = view.findViewById(R.id.etProductType);
        View btnAddProduct = view.findViewById(R.id.btnAddProduct);

        AlertDialog dialog = builder.create();

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etProductName.getText().toString();
                Integer price = Integer.parseInt(etProductPrice.getText().toString());
                String description = etProductDescription.getText().toString();
                String type = etProductType.getText().toString();

                Products newProduct = new Products(name, price, description, type, "mon_an"); // Thêm hình ảnh mặc định nếu cần

                productDAO.insert(newProduct);
                listProduct.add(newProduct);
                productAdapter.notifyItemInserted(listProduct.size() - 1);
                dialog.dismiss();
                Toast.makeText(getContext(), "Thêm món ăn thành công", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
