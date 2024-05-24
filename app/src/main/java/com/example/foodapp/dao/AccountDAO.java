package com.example.foodapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.database.DbHelper;

public class AccountDAO {
    private final Context context;
    private final DbHelper dbHelper;
    private SQLiteDatabase db;

    public AccountDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    // Open the database connection
    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    // Close the database connection
    public void close() {
        dbHelper.close();
    }

    // Method to register a new user

    public long registerUser(String phone, String name, String password) {
        ContentValues values = new ContentValues();
        values.put("phone", phone);
        values.put("avatar", ""); // If avatar is not provided, set it to an empty string or a default value
        values.put("orderID", ""); // If orderID is not provided, set it to an empty string or handle it as necessary
        values.put("name", name);
        values.put("password", password); // Store the password, but in a real app, hash it before storing

        return db.insert("User", null, values);
    }


    // Method to login a user
    public boolean loginUser(String phone, String password) {
        String[] columns = { "id" };
        String selection = "phone = ? AND password = ?";
        String[] selectionArgs = { phone, password };

        Cursor cursor = db.query("User", columns, selection, selectionArgs, null, null, null);

        boolean isValidUser = cursor.getCount() > 0;
        cursor.close();
        return isValidUser;
    }

}
