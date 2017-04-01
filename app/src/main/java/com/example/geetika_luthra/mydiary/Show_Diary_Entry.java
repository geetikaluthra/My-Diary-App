package com.example.geetika_luthra.mydiary;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Show_Diary_Entry extends AppCompatActivity {
    TextView date,title,body;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__diary__entry);
        date=(TextView)findViewById(R.id.show_date);
        title=(TextView)findViewById(R.id.title_diary);
        body=(TextView)findViewById(R.id.diary);
        Intent i=getIntent();
        System.out.println(i.getExtras().getInt("Pos"));
        pos=i.getExtras().getInt("Pos");
        Diary_dbHelper db = new Diary_dbHelper(Show_Diary_Entry.this);
        Diary m=db.getDiary(pos+1);
        date.setText(m.get_date().toString());
        title.setText(m.get_title().toString());
        body.setText(m.get_body().toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.edit) {
//            Intent i=new Intent(Show_Diary_Entry.this,Edit_Diary_Entry.class);
//            startActivity(i);
//            finish();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
