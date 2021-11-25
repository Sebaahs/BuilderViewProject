package com.sebaahs.builderview.src.provides.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LocalPreferences {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public LocalPreferences(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = preferences.edit();
    }

    //--> Setters

    public void setPreferenceData(Context context,PreferencesKey key , String value){
        editor.putString(key.getValue(),value).commit();
    }
    public void setPreferenceData(Context context,PreferencesKey key , Boolean value){
        editor.putBoolean(key.getValue(),value).commit();
    }

    //--> Getters

    public String string(Context context, PreferencesKey key){
        return preferences.getString(key.getValue(), "");
    }
    public Boolean bool(Context context, PreferencesKey key){
        return preferences.getBoolean(key.getValue(), false);
    }
    //--> Clear
    public void clear(Context context){
        editor.clear().commit();
    }

}
