package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        TableLayout table = findViewById(R.id.tableview);
        table.removeAllViewsInLayout();

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL
        };
        SQLiteDatabase readdb = dbHelper.getReadableDatabase();
        Cursor cursor = readdb.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,//                selection,              // The columns for the WHERE clause
                null,//                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null //sortOrder               // The sort order
        );

        while(cursor.moveToNext()) {
            TableRow tr=new TableRow(this);

            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));


            TextView b1 = new TextView(this);
            TextView b2 = new TextView(this);
            TextView b3 = new TextView(this);
            TextView b4 = new TextView(this);
            b1.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME)));
            b2.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS)));
            b3.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE)));
            b4.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL)));

            tr.addView(b1);
            tr.addView(b2);
            tr.addView(b3);
            tr.addView(b4);

            table.addView(tr);
        }
        cursor.close();


    }
}
