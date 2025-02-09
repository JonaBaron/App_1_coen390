package com.example.assignment1coen390;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button settings;
    private Button b1,b2,b3;
    private Button Counts;

    private TextView textView;

    protected SharedPreferenceHelper sharedPreferenceHelper;
    private int maxCount;

    private String str ;

    private String var1 ; // name button one
    private String var2 ; // name button two
    private String var3 ; // name button three
    private String var4 ; // max count
    private String var5; // live count
    private String var6; // actions done in order



    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Things that are created
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferenceHelper
        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);

        // Find elements
        settings = findViewById(R.id.buttonSettings);
        b1 = findViewById(R.id.button8);
        b2 = findViewById(R.id.button6);
        b3 = findViewById(R.id.button7);
        textView = findViewById(R.id.textView);
        Counts = findViewById(R.id.button2);

        // Set the background color of the buttons
        b1.setBackgroundColor(ContextCompat.getColor(this, R.color.bluelight));
        b2.setBackgroundColor(ContextCompat.getColor(this, R.color.bluelight));
        b3.setBackgroundColor(ContextCompat.getColor(this, R.color.bluelight));
        Counts.setBackgroundColor(ContextCompat.getColor(this, R.color.greenlight));
        settings.setBackgroundColor(ContextCompat.getColor(this, R.color.orangelight));

        // Set click listeners for setting button
        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // intent to setting activity
                Intent intent = new Intent(MainActivity.this, SetingActivity.class);
                setParts();
                setWord();
                intent.putExtra("word", sharedPreferenceHelper.getName());
                startActivity(intent);
            }
        });

        // Set click listeners for event 1 buttons
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setParts();
                if(canCount()){
                    //+1 to counter
                    var5 = Integer.toString(Integer.parseInt(var5) + 1);
                    //add to actions
                    var6 += "counter1"+ "\n";
                    //to shared preference
                    setWord();
                    //set text to elements
                    setTextView();
                    Log.d("error------------------------------", sharedPreferenceHelper.getName());

                }
            }
        });

        // Set click listeners for event 2 buttons
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setParts();
                if(canCount()){
                    var5 = Integer.toString(Integer.parseInt(var5) + 1);
                    var6 += "counter2"+ "\n";
                    setWord();
                    setTextView();
                    Log.d("error------------------------------", sharedPreferenceHelper.getName());

                }
            }
        });

        // Set click listeners for event 3 buttons
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setParts();
                if(canCount()){
                    var5 = Integer.toString(Integer.parseInt(var5) + 1);
                    var6 += "counter3" + "\n";
                    setWord();
                    setTextView();
                    Log.d("error------------------------------", sharedPreferenceHelper.getName());

                }
            }
        });

        // Set click listeners for dataActivity buttons
        Counts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent to data activity
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                setParts();
                setWord();
                intent.putExtra("word", sharedPreferenceHelper.getName());
                startActivity(intent);

            }
        });


    }
    // Check if the counter is valid
    private boolean canCount() {
        getWord();
        return Integer.parseInt(var5) < maxCount;
    }

    // Get data from shared preference
    @Override
    protected void onStart() {
            super.onStart();
            if (sharedPreferenceHelper.getName() != null) {
                setTextButton();
            } else {
                Intent intent = new Intent(MainActivity.this, SetingActivity.class);
                startActivity(intent);
            }
    }

    // Set the text for the text view
    private void setTextView() {
        String text = "TOTAL COUNTS:";
        if(var5 == "")
            text = "TOTAL COUNTS: 0";
        else
            text += var5;
        textView.setText(text);
    }

    // Set the text for the button
    private void setTextButton() {

        try {
            getWord();
            setParts();
        } catch (RuntimeException e) {
            Log.d("error------------------------------", sharedPreferenceHelper.getName());
            Intent intent = new Intent(MainActivity.this, SetingActivity.class);
            startActivity(intent);
        }

    }

    //Get the string which comes from shared preference to variable
    private void setParts(){
        String[] lines = str.split("\n");

        var1 = lines[0]; // name button one
        var2 = lines[1]; // name button two
        var3 = lines[2]; // name button three
        var4 = lines[3]; // max count
        var5 = lines[4]; // live count
        var6 = lines[5]; // actions done in order
        b1.setText(var1);
        b2.setText(var2);
        b3.setText(var3);
        if(var4 != null)
            maxCount = Integer.parseInt(var4);
        if(var5 != null)
            setTextView();
    }

    //Get the string from shared preference
    private void getWord(){
            str = sharedPreferenceHelper.getName();
        }

    //Set the string to shared preference
    private void setWord(){
        str = var1 + "\n" + var2 + "\n" + var3 + "\n" + var4 + "\n" + var5 + "\n" + var6;
        sharedPreferenceHelper.setName(str);
    }
}

