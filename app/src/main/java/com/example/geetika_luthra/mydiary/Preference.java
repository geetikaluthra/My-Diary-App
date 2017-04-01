package com.example.geetika_luthra.mydiary;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.prefs.Preferences;

/**
 * Created by Geetika_Luthra on 30-03-2017.
 */

public class Preference extends Activity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String DCODE="DCODE";
    private final String IS_LOCKED="IS_LOCKED";
    private final String EMAIL_ID="EMAIL_ID";


    public boolean isLocked;
    public String dcode;
    public String emailid;
    private static Preference instance=null;
    private Preference(){

    }
    public static synchronized Preference getInstance(){
        if(instance==null)
            instance=new Preference();

        return instance;
    }

    public void loadPreference(Context context){
        preferences=PreferenceManager.getDefaultSharedPreferences(context);
        isLocked=preferences.getBoolean(IS_LOCKED, false);
        dcode = preferences.getString(DCODE, null);
        emailid=preferences.getString(EMAIL_ID,null);


    }


    public void savePreference(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        editor.putString(DCODE, dcode);
        editor.putString(EMAIL_ID,emailid);
        editor.putBoolean(IS_LOCKED,isLocked);
        editor.commit();
    }
}
