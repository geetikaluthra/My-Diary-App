package com.example.geetika_luthra.mydiary;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Report_a_bug extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private Button submit;
    private String bug;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_a_bug);
        submit=(Button)findViewById(R.id.submit);
        radioGroup=(RadioGroup)findViewById(R.id.rg);
        radioButton1=(RadioButton)findViewById(R.id.rb1);
        radioButton2=(RadioButton)findViewById(R.id.rb2);
        radioButton3=(RadioButton)findViewById(R.id.rb3);
        radioButton4=(RadioButton)findViewById(R.id.rb4);
        radioGroup.setOnCheckedChangeListener(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: geetu.luthra95@gmail.com"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Bug Reported");
                emailIntent.putExtra(Intent.EXTRA_TEXT, bug);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                finish();

            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if(checkedId==radioButton1.getId())
        {
            bug="Add a new Diary Entry";
        }
        if(checkedId==radioButton2.getId())
        {
            bug="View a Diary Entry";

        }
        if(checkedId==radioButton3.getId())
        {
            bug="Locking of Diary";

        }
        if(checkedId==radioButton4.getId())
        {
            bug="Sharing App";

        }

    }
}
