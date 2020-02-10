package com.example.dakshinui1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import org.w3c.dom.Text
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class Ex2Activity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex2)
        val calendarButton = findViewById<Button>(R.id.button2);
        val dateTextView = findViewById<TextView>(R.id.textView4);
        val resetButton = findViewById<Button>(R.id.button3);
        val radiogroup = findViewById<RadioGroup>(R.id.radiogroup);
        val maleradio = findViewById<RadioButton>(R.id.radioButton9);
        val femalradio = findViewById<RadioButton>(R.id.radioButton8);
        val nametext = findViewById<EditText>(R.id.editText10);
        val address = findViewById<EditText>(R.id.editText11);
        val spinner = findViewById<Spinner>(R.id.spinner);
        val phone = findViewById<EditText>(R.id.editText14);
        val checkbox1=findViewById<CheckBox>(R.id.checkBox);
        val checkbox2=findViewById<CheckBox>(R.id.checkBox2);
        val submitButton = findViewById<Button>(R.id.button)

        calendarButton.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                dateTextView.setText("" + dayOfMonth + "/" + (monthOfYear +1)+ "/" + year);
            },year,month,day);

            dpd.show()
        }
        resetButton.setOnClickListener {
            radiogroup.clearCheck();
            nametext.setText("");
            address.setText("");
            dateTextView.setText("Click the button to select a date");
            spinner.setSelection(0);
            phone.setText("");
            if(checkbox1.isChecked)
                checkbox1.toggle();
            if(checkbox2.isChecked)
                checkbox2.toggle();
        }

        submitButton.setOnClickListener {
//            Toast.makeText(this,nametext.text,Toast.LENGTH_SHORT).show();
            val intent = Intent(this, TableActivity::class.java);
            intent.putExtra("name", nametext.text.toString());
            intent.putExtra("address",address.text.toString());
            intent.putExtra("dob",dateTextView.text.toString());
            if(maleradio.isSelected)
                intent.putExtra("gender","male");
            else
                intent.putExtra("gender","female");
            val married :String = (spinner.selectedItem as String );
            intent.putExtra("married",married);
            intent.putExtra("phone",phone.text.toString());
            var lang:String="";
            if(checkbox1.isChecked)
                lang+="English ";
            if(checkbox2.isChecked)
                lang=="Tamil";
            intent.putExtra("languages",lang);
            startActivity(intent);
        }

    }
}
