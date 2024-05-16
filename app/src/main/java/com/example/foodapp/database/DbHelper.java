package com.example.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    Context context;

    public DbHelper(Context context) {
        super(context, null, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE DATA

        String sqlAdmin = "CREATE TABLE Admin(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "avatar text," +
                "phone text)";
        db.execSQL(sqlAdmin);
        String sqlUser ="CREATE TABLE User(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "phone text," +
                "avatar text," +
                "orderID text)";
        db.execSQL(sqlUser);
        String sqlProduct = "CREATE TABLE Product(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "categoryId integer," +
                "name text," +
                "price REAL," +
                "image text," +
                "description text," +
                "FOREIGN KEY (categoryId) REFERENCES categoryId)";
        db.execSQL(sqlProduct);
        String sqlCategory="CREATE TABLE Category(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name text," +
                "description text)";
        db.execSQL(sqlCategory);
        String sqlOrder = "CREATE TABLE Orders(" +
                "orderID text PRIMARY KEY," +
                "total REAL," +
                "userID INTEGER ," +
                "FOREIGN KEY (userID) REFERENCES userID)";
        db.execSQL(sqlOrder);
        String sqlNotification = "CREATE TABLE Notification(" +
                "notificationId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "content text)";
        db.execSQL(sqlNotification);

        //INSERT DATA....
        sqlProduct ="INSERT INTO Product(name,price,  image,description ) VALUES('Thanh long ngọt', 5000,'Nội dung gì đó ','Đây là mô ta của món ăn' )";
        db.execSQL(sqlProduct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Admin");
        db.execSQL("DROP TABLE IF EXISTS Product");
        db.execSQL("DROP TABLE IF EXISTS Category");
        db.execSQL("DROP TABLE IF EXISTS Orders");
        db.execSQL("DROP TABLE IF EXISTS Notification");

    }
}
