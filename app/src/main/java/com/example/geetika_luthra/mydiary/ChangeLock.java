package com.example.geetika_luthra.mydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeLock extends AppCompatActivity {

    private Button change;
    private Button remove;
    private EditText newcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_lock);
        change=(Button)findViewById(R.id.changecode);
        remove=(Button)findViewById(R.id.removecode);
        newcode=(EditText)findViewById(R.id.vcode);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newcode.getText().toString()!=""){

                    Preference.getInstance().loadPreference(ChangeLock.this);
                    String p=newcode.getText().toString();
                    Preference.getInstance().dcode=p;
                    Preference.getInstance().savePreference(ChangeLock.this);
                    Toast.makeText(getApplicationContext(),"Lock has been changed",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(ChangeLock.this,Settings.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Enter the new code",Toast.LENGTH_LONG).show();
                }

            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.getInstance().loadPreference(ChangeLock.this);
                Preference.getInstance().isLocked=false;
                Preference.getInstance().dcode=null;
                Preference.getInstance().savePreference(ChangeLock.this);
                Toast.makeText(getApplicationContext(),"Lock has been removed",Toast.LENGTH_LONG).show();
                Intent i=new Intent(ChangeLock.this,Settings.class);
                startActivity(i);
                finish();

            }
        });
    }
}
