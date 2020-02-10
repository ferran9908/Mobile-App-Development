package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void display(View v) {
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }
    public void insert(View v) {
        Intent intent = new Intent(this,InsertActivity.class);
        startActivity(intent);
    }
    public void edit(View v) {
        Intent intent = new Intent(this,EditActivity.class);
        startActivity(intent);
    }
    public void delete(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter name");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String searchname = input.getText().toString();
                deleteRecord(searchname);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void deleteRecord(String searchname) {
        FeedReaderDbHelper dbHelper =  new FeedReaderDbHelper(this);
        SQLiteDatabase writedb = dbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_NAME + " = ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = { searchname };
// Issue SQL statement.
        int deletedRows = writedb.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
        if(deletedRows>0)
            Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();

    }
}
