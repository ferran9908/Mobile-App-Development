package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    FeedReaderDbHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

                dbHelper = new FeedReaderDbHelper(this);

//         Gets the data repository in write mode
        db = dbHelper.getWritableDatabase();

//         Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, "ferran");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS, "ssn");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE,"911");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL,"sdlf@gmail.com");


//         Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        if(newRowId!=-1)
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

    }

    public void submit(View v){
        EditText name,address,phone,email;
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        email=findViewById(R.id.email);

        //         Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, name.getText().toString());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS, address.getText().toString());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE,phone.getText().toString());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL,email.getText().toString());


//         Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        if(newRowId!=-1)
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

    }
    
}
