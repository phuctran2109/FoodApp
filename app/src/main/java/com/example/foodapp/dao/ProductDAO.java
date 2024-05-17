package com.example.foodapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.Products;

import java.util.ArrayList;

public class ProductDAO {
    private static final String TAG = "ProductDAO";
    private final DbHelper helper;

    public ProductDAO(Context context) {
        helper = new DbHelper(context);
    }

    // GetAll
    public ArrayList<Products> getAll() {
        ArrayList<Products> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = database.rawQuery("SELECT id, name, price, image, description FROM Product", null);
            if (cursor.moveToFirst()) {
                do {
                    Integer id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    Integer price = cursor.getInt(2);
                    String image = cursor.getString(3);
                    String description = cursor.getString(4);
                    Products products = new Products(id, name, price, description, image);
                    list.add(products);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.e(TAG, "Error while trying to get products from database", ex);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    // Insert

    // Update, Delete methods to be added here
}
