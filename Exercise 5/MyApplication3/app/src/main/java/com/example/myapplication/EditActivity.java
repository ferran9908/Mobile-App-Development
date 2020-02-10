package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText address,phone,email;
    FeedReaderDbHelper dbHelper;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        address=findViewById(R.id.edit_address);
        phone=findViewById(R.id.edit_phone);
        email=findViewById(R.id.edit_email);

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
                display(searchname);
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

    private void display(String searchname) {
        this.name=searchname;
//        Toast.makeText(this, searchname, Toast.LENGTH_SHORT).show();
        dbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase readdb = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL
        };

        Cursor cursor = readdb.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                FeedReaderContract.FeedEntry.COLUMN_NAME_NAME+"=?",//                selection,              // The columns for the WHERE clause
                new String[]{searchname},//                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null //sortOrder               // The sort order
        );
        if(cursor.moveToFirst())
        {
            address.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS)));
            phone.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE)));
            email.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL)));
        }
        else
        {
            Toast.makeText(this, "Data not found!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void update(View v)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

// New value for one column
//        String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS, address.getText().toString());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL,email.getText().toString());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE,phone.getText().toString());

// Which row to update, based on the title
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = { this.name };

        int count = db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        Toast.makeText(this, "Update returned "+count, Toast.LENGTH_SHORT).show();

    }
}
