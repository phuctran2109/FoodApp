package com.example.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "APP_FOOD";
    private static final int DATABASE_VERSION = 4;

    private static final String TABLE_ADMIN = "Admin";
    private static final String TABLE_USER = "User";
    private static final String TABLE_CATEGORY = "Category";
    private static final String TABLE_PRODUCT = "Product";
    private static final String TABLE_ORDERS = "Orders";
    private static final String TABLE_NOTIFICATION = "Notification";

    private static final String CREATE_TABLE_ADMIN = "CREATE TABLE " + TABLE_ADMIN + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "avatar TEXT," +
            "phone TEXT)";

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "phone TEXT," +
            "avatar TEXT," +
            "orderID TEXT)";

    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "description TEXT)";

    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "categoryId INTEGER," +
            "name TEXT," +
            "price REAL," +
            "image TEXT," +
            "description TEXT," +
            "FOREIGN KEY (categoryId) REFERENCES " + TABLE_CATEGORY + "(id))";

    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE " + TABLE_ORDERS + " (" +
            "orderID TEXT PRIMARY KEY," +
            "total REAL," +
            "userID INTEGER," +
            "FOREIGN KEY (userID) REFERENCES " + TABLE_USER + "(id))";

    private static final String CREATE_TABLE_NOTIFICATION = "CREATE TABLE " + TABLE_NOTIFICATION + " (" +
            "notificationId INTEGER PRIMARY KEY AUTOINCREMENT," +
            "content TEXT)";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_NOTIFICATION);

        // Insert initial data
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Fruits', 'All kinds of fruits')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Vegetables', 'Various vegetables')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Meat', 'Different types of meat')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Seafood', 'Fresh seafood')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Dairy', 'Dairy products')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Beverages', 'Various drinks')");

        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Thanh long ngọt', 5000, 'mon_an', 'Đây là mô tả của món ăn', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Thanh long chua', 6000, 'fb_icon', 'Đây là mô tả của món ăn', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Táo đỏ', 7000, 'gg_icon', 'Đây là mô tả của món ăn', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Thanh long ngọt', 5000, 'mon_an', 'Đây là mô tả của món ăn', 2)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Thanh long chua', 6000, 'fb_icon', 'Đây là mô tả của món ăn', 2)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Táo đỏ', 7000, 'gg_icon', 'Đây là mô tả của món ăn', 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        onCreate(db);
    }
}
