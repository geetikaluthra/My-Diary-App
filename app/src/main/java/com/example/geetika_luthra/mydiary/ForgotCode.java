package com.example.geetika_luthra.mydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotCode extends AppCompatActivity {
    private TextView text;
    private EditText necode;
    private EditText femail;
    private Button savenewcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_code);
        text=(TextView)findViewById(R.id.locktext);
        text.setText("Change your code to protect your Diary Entry");
        necode=(EditText)findViewById(R.id.necode);
        femail=(EditText)findViewById(R.id.femail);
        savenewcode=(Button)findViewById(R.id.savenewcode);
        savenewcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (necode.getText().length() >= 4 && necode.getText().length() <= 10) {


                    if (Preference.getInstance().emailid.equalsIgnoreCase(femail.getText().toString())) {

                        Preference.getInstance().loadPreference(ForgotCode.this);
                        Preference.getInstance().dcode = necode.getText().toString();
                        Preference.getInstance().savePreference(ForgotCode.this);
                        Toast.makeText(getApplicationContext(), "Your Code has been changed now", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ForgotCode.this, VerifyLock.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter your correct Email ", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Code length should be between 4 and 10",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
