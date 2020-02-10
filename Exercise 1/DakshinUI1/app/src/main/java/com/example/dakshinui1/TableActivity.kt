package com.example.dakshinui1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class TableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        var name=findViewById<TextView>(R.id.name);
        var address=findViewById<TextView>(R.id.address);
        var dob=findViewById<TextView>(R.id.dob);
        var gender=findViewById<TextView>(R.id.gender);
        var married = findViewById<TextView>(R.id.married);
        var phone=findViewById<TextView>(R.id.phone);
        name.text=intent.getStringExtra("name");
        address.text=intent.getStringExtra("address");
        dob.text=intent.getStringExtra("dob");
        gender.text=intent.getStringExtra("gender");
        married.text=intent.getStringExtra("married");
        phone.text=intent.getStringExtra("phone");
    }
}
