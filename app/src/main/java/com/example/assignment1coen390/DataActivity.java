package com.example.assignment1coen390;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.Window;
import androidx.core.content.ContextCompat;

public class DataActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;

    protected SharedPreferenceHelper sharedPreferenceHelper;
    private String str ;

    private String var1 ; // name button one
    private String var2 ; // name button two
    private String var3 ; // name button three
    private String var4 ; // max count
    private String var5; // live count
    private String var6; // actions done in order
    private String info;
    private String activities;

    private boolean choice = true;

    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Things that are created
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);

        // Initialize SharedPreferenceHelper
        sharedPreferenceHelper = new SharedPreferenceHelper(DataActivity.this);

        // Find elements
        toolbar = findViewById(R.id.toolbar);
        textView = findViewById(R.id.textView2);

        // Set the title in the toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Activity");


        // Set the status bar color
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bluelight));


        // Set the return back icon
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);

        // Set the OnClick listener for the return back icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(DataActivity.this, MainActivity.class);
                    startActivity(intent);
            }
        });


        // Retrieve the name from intent
        if (getIntent().getStringExtra("word") != null) {
            str = getIntent().getStringExtra("word");
            String[] lines = str.split("\n");

            var1 = lines[0]; // name text one
            var2 = lines[1]; // name text two
            var3 = lines[2]; // name text three
            var4 = lines[3]; // max text
            var5 = lines[4]; // live count
            var6 = lines[5]; // actions done in order

        }
        else{

            textView.setText("No activity");
        }


        activities = "";

        count1 = 0;
        count2 = 0;
        count3 = 0;

        String[] lines = str.split("\n");
        String[] section = lines[5].split("counter");
        str = lines[5];
        for (String line : section) {
            if (line.contains("1")) {
                count1++;
                activities += "Event A\n";
            } else if (line.contains("2")) {
                count2++;
                activities += "Event B\n";
            } else if (line.contains("3")) {
                count3++;
                activities += "Event C\n";
            }
        }

        // Set the text for the text view of activities
        setInfo();

    }

    // Set the text for the text view depending on the choice of view
    private void setInfo() {
        StringBuilder newActivities = new StringBuilder();
        String info;

        if (choice) {
            info = "Event A: " + count1 + " events\n" +
                    "Event B: " + count2 + " events\n" +
                    "Event C: " + count3 + " events\n" +
                    "Total Events: " + (count1 + count2 + count3) + "\n";

            if (str != null) {
                Log.d("error------------------------------", str);
                String[] lines = str.split("counter");
                for (String line : lines) {
                    if (line.trim().equals("1")) {
                        newActivities.append("Event A\n");
                    } else if (line.trim().equals("2")) {
                        newActivities.append("Event B\n");
                    } else if (line.trim().equals("3")) {
                        newActivities.append("Event C\n");
                    }
                }
            }
       } else {
            info = "Counter 1: " + count1 + " events\n" +
                    "Counter 2: " + count2 + " events\n" +
                    "Counter 3: " + count3 + " events\n" +
                    "Total Events: " + (count1 + count2 + count3) + "\n";

            if (str != null) {
                String[] lines = str.split("counter");
                for (String line : lines) {
                    if (line.trim().equals("1")) {
                        newActivities.append("1\n");
                    } else if (line.trim().equals("2")) {
                        newActivities.append("2\n");
                    } else if (line.trim().equals("3")) {
                        newActivities.append("3\n");
                    }
                }
            }
        }

        // Send text to text view
        textView.setText(info.concat("\n".concat(newActivities.toString())));
    }



    //Get data to variable
    private void setParts(){
        String[] lines = str.split("\n");

        var1 = lines[0]; // name button one
        var2 = lines[1]; // name button two
        var3 = lines[2]; // name button three
        var4 = lines[3]; // max count
        var5 = lines[4]; // live count
        var6 = lines[5]; // actions done in order
    }

    //Get data from shared preference
    private void getWord(){
        str = sharedPreferenceHelper.getName();
    }

    //Set data to shared preference
    private void setWord(){
        str = var1 + "\n" + var2 + "\n" + var3 + "\n" + var4 + "\n" + var5 + "\n" + var6;
        sharedPreferenceHelper.setName(str);
    }


    // 3 point for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_data, menu); // Use your actual menu filename
        return true;
    }
    // 3 point for menu action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item) {
            choice = !choice;
            setInfo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}