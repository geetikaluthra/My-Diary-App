package com.example.geetika_luthra.mydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class Lock_Diary extends AppCompatActivity {

    private TextView text;
    private EditText code;
    private EditText email;
    private Button savecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock__diary);
        text=(TextView)findViewById(R.id.locktext);
        text.setText("Use a code to protect your Diary Entry");
        code=(EditText)findViewById(R.id.code);
        email=(EditText)findViewById(R.id.email);
        savecode=(Button)findViewById(R.id.savecode);
        System.out.println(email.getText().toString());
        savecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(code.getText().length()>=4 && code.getText().length()<=10 )
                    {
                        System.out.println(email.getText().toString());
                        System.out.println(code.getText().toString());
                        String pswd=code.getText().toString();
                        String emailid=email.getText().toString();
                        Preference.getInstance().loadPreference(Lock_Diary.this);
                        Preference.getInstance().isLocked=true;
                        Preference.getInstance().dcode=pswd;
                        Preference.getInstance().emailid=emailid;
                        Preference.getInstance().savePreference(Lock_Diary.this);
                        Toast.makeText(getApplicationContext(),"Diary is now locked",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Lock_Diary.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Code length should be between 4 and 10",Toast.LENGTH_LONG).show();
                    }

            }
        });
    }
}
