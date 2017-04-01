package com.example.geetika_luthra.mydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class VerifyLock extends AppCompatActivity {
    private EditText verifycode;
    private Button submitbtn;
    private Button forgotcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_lock);
        verifycode=(EditText)findViewById(R.id.verifycode);
        submitbtn=(Button)findViewById(R.id.checkcode);
        forgotcode=(Button)findViewById(R.id.forgotcode);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(Preference.getInstance().dcode);
                System.out.println(verifycode.getText().toString());
                String match=Preference.getInstance().dcode;
                if(verifycode.getText().toString()!="") {


                    if (verifycode.getText().toString().equalsIgnoreCase(match)) {
                        Intent i = new Intent(VerifyLock.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Secret Code is wrong", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter the code", Toast.LENGTH_LONG).show();
                }
            }
        });
        forgotcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"Code has been sent to your email id",Toast.LENGTH_LONG).show();
                //Preference.getInstance().dcode=toString(i1);
                //AsyncTaskRunner runner = new AsyncTaskRunner();
                //runner.execute("");
                Intent i = new Intent(VerifyLock.this, ForgotCode.class);
                startActivity(i);
                finish();
            }
        });

    }
}
