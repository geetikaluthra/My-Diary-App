package com.example.geetika_luthra.mydiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.geetika_luthra.mydiary.com.example.geetika_luthra.mydiary.vo.MyDiaryVO;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView empty;
    private ListAdapter adapter;
    private ListView dList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Diary_dbHelper db = new Diary_dbHelper(MainActivity.this);
        empty=(TextView)findViewById(R.id.empty);
        int count=0;
        count=db.getDiaryEntryCount();
        System.out.println("Count "+count);
        if(count==0){
            empty.setText("Write Your First Diary Entry" );

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i=new Intent(MainActivity.this,Add_diary_entry.class);
                startActivity(i);
                finish();
            }
        });

        List<MyDiaryVO> diaryList = new ArrayList<MyDiaryVO>();
        diaryList=db.getAllDiaryEntries();
        dList = (ListView)findViewById(R.id.dList);
        adapter = new ListAdapter(this,diaryList );
        dList.setAdapter(adapter);
        dList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getApplicationContext(),Show_Diary_Entry.class);
                System.out.println("pos"+position);
                intent.putExtra("Pos", position);
                startActivity(intent);
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(MainActivity.this,Settings.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ListAdapter extends BaseAdapter
    {
        @SuppressWarnings("unused")
        private Context c;
        private List<MyDiaryVO> diaryList;
        private LayoutInflater inflater;
        public ListAdapter(Context context,List<MyDiaryVO> list)
        {
            this.c = context;
            this.diaryList = list;
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return diaryList.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup arg2) {
            Holder h;
            if(convertView == null)
            {
                h=new Holder();
                convertView =inflater.inflate(R.layout.activity_diarylist_item_layout, null);
                h.date = (TextView)convertView.findViewById(R.id.ddate);
                h.title = (TextView)convertView.findViewById(R.id.dtitle);
                convertView.setTag(h);

            }
            else
            {
                h= (Holder)convertView.getTag();
            }
            h.date.setText(diaryList.get(position).getDate());
            h.title.setText(diaryList.get(position).getTitle());
            return convertView;
        }


    }
    private static class Holder
    {
        public TextView date;
        private TextView title;

    }

}
