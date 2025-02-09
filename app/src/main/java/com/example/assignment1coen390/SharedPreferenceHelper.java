package com.example.assignment1coen390;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {

    private SharedPreferences sharedPreferences;
    public SharedPreferenceHelper(Context context){
        sharedPreferences = context.getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE);
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profileName", name);
        editor.commit();
        }

    public String getName() {
        return sharedPreferences.getString("profileName", null);
    }

}
