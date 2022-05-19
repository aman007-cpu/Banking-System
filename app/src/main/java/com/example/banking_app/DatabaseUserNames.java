package com.example.banking_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseUserNames extends SQLiteOpenHelper {
    private final String TABLE_NAME = "user_table";
    private final String TABLE_NAME1 = "transfers_table";

    public DatabaseUserNames(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(0812401842,'Aman',82044.34,'aman@gmail.com','XXXXXXXXXXXX13562','ABC00973')");
        db.execSQL("insert into user_table values(2445654354,'Harsh',1632.30,'harsh@gmail.com','XXXXXXXXXXXX0012','ABC01480')");
        db.execSQL("insert into user_table values(4851245555,'Imran',2452.05,'imran@gmail.com','XXXXXXXXXXXX1122','ABC90171')");
        db.execSQL("insert into user_table values(8456456945,'Arunima',14426.09,'arunima@gmail.com','XXXXXXXXXXXX7171','ABC90731')");
        db.execSQL("insert into user_table values(1654845656,'Lokesh',233650.02,'akshita@gmail.com','XXXXXXXXXXXX8118','ABC88832')");
        db.execSQL("insert into user_table values(8554445654,'Manan',1364.15,'manan@gmail.com','XXXXXXXXXXXX1010','ABC010101')");
        db.execSQL("insert into user_table values(8741852655,'Lmc',5746.00,'lmc@gmail.com','XXXXXXXXXXXX0001','ABC93203')");
        db.execSQL("insert into user_table values(8451411411,'Aditya',6325.22,'aditya@gmail.com','XXXXXXXXXXXX2222','ABC92340')");
        db.execSQL("insert into user_table values(7451256344,'Yash',4398.46,'yash@gmail.com','XXXXXXXXXXXX1032','ABC00002')");
        db.execSQL("insert into user_table values(7845125884,'Yuvanshu',8273.90,'yuvi@gmail.com','XXXXXXXXXXXX0192','ABC11110')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date, String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}