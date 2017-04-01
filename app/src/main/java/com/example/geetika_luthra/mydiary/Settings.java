package com.example.geetika_luthra.mydiary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geetika_luthra.mydiary.com.example.geetika_luthra.mydiary.vo.SettingsVO;
import java.util.LinkedList;
import java.util.List;


public class Settings extends AppCompatActivity {
    private ListView slist;
    private ListAdapter sadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        slist = (ListView)findViewById(R.id.slist);
        sadapter = new ListAdapter(this,createDummySettingsData());
        slist.setAdapter(sadapter);
        slist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case  0:
                        Intent i1=new Intent(Settings.this,Report_a_bug.class);
                        startActivity(i1);
                        break;

                    case  1:
                        if(!Preference.getInstance().isLocked)
                        {
                            Intent i2=new Intent(Settings.this,Lock_Diary.class);
                            startActivity(i2);
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Your Diary is already Locked",Toast.LENGTH_LONG).show();

                        break;

                    case  2:
                        if(Preference.getInstance().isLocked)
                        {
                            Intent i2=new Intent(Settings.this,ChangeLock.class);
                            startActivity(i2);
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Your Diary is not Locked",Toast.LENGTH_LONG).show();

                        break;


                    case  3:
                        Intent share=new Intent(android.content.Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        share.putExtra(android.content.Intent.EXTRA_TEXT, "Completed My Diary App");
                        share.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Dary App");
                        startActivity(Intent.createChooser(share, "Share link!"));
                        break;

                    case  4:
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto: geetu.luthra95@gmail.com"));
                        startActivity(Intent.createChooser(emailIntent, "Send feedback"));
                        break;

                    case  5:
                        Intent i=new Intent(Settings.this,About.class);
                        startActivity(i);
                        break;

                }


            }
        });
    }

    private List<SettingsVO> createDummySettingsData()
    {
        List<SettingsVO> list = new LinkedList<SettingsVO>();
        SettingsVO o = new SettingsVO();
        o.setName("Report a Bug");

        SettingsVO o1 = new SettingsVO();
        o1.setName("Lock the Diary");

        SettingsVO o2 = new SettingsVO();
        o2.setName("Change/Remove the Lock");

        SettingsVO o3 = new SettingsVO();
        o3.setName("Share Diary App");


        SettingsVO o4 = new SettingsVO();
        o4.setName("Talk to Developer");


        SettingsVO o5 = new SettingsVO();
        o5.setName("About");

        list.add(o);
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        list.add(o5);


        return list;
    }

    private class ListAdapter extends BaseAdapter
    {
        @SuppressWarnings("unused")
        private Context c;
        private List<SettingsVO> sList;
        private LayoutInflater inflater;
        public ListAdapter(Context context,List<SettingsVO> list)
        {
            this.c = context;
            this.sList = list;
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return sList.size();
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
                convertView =inflater.inflate(R.layout.activity_settings_list_item, null);
                h.name = (TextView)convertView.findViewById(R.id.text1);
                convertView.setTag(h);

            }
            else
            {
                h= (Holder)convertView.getTag();
            }
            h.name.setText(sList.get(position).getName());

            return convertView;
        }


    }
    private static class Holder
    {
        public TextView name;
    }
}
