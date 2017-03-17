package com.example.geetika_luthra.mydiary;



import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

/**
 * Created by Geetika_Luthra on 17-03-2017.
 */

public class Add_diary_entry extends AppCompatActivity {

    private EditText text,date,title;
    private Button save;
    private ImageButton calender;
    private int mYear, mMonth, mDay;
    //private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        title=(EditText)findViewById(R.id.title_diary);
        text=(EditText)findViewById(R.id.diary);
        save=(Button)findViewById(R.id.save);
        calender=(ImageButton)findViewById(R.id.calender);
        date=(EditText)findViewById(R.id.date);
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        date.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(mDay).append(" ").append("-").append(mMonth + 1).append("-")
                .append(mYear));
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_diary_entry.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(title.getText().toString().equalsIgnoreCase("") || text.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"All Fields are Mandatory",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Diary_dbHelper db = new Diary_dbHelper(Add_diary_entry.this);
                    db.createDiary(title.getText().toString(),text.getText().toString());
                    System.out.println("Entry Added");
                    Intent i=new Intent(Add_diary_entry.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }

        });



    }
    public void onBackPressed(){
        Intent i=new Intent(Add_diary_entry.this,MainActivity.class);
        startActivity(i);
        finish();

    }

}
