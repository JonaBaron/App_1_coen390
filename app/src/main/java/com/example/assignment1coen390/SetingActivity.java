package com.example.assignment1coen390;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SetingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText e1,e2,e3,e4;
    private Button button;
    private TextView text_error;
    protected SharedPreferenceHelper sharedPreferenceHelper;

    private String name;

    private String actions;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Things that are created
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setings);

        // Initialize SharedPreferenceHelper
        sharedPreferenceHelper = new SharedPreferenceHelper(SetingActivity.this);


        // find the elements
        toolbar = findViewById(R.id.toolbar3);
        e1 = findViewById(R.id.editTextText);
        e2 = findViewById(R.id.editTextText2);
        e3 = findViewById(R.id.editTextText3);
        e4 = findViewById(R.id.editTextText4);
        text_error = findViewById(R.id.textView8);
        button = findViewById(R.id.button);

        //Set the EditText fields to be not editable
        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);
        e4.setEnabled(false);

        // Set the title in the toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        toolbar.setTitleTextColor(Color.BLACK);

        // Set the background color of the button
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.orangelight));

        // Set the status bar color
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bluelight));

        // Set the return back icon
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);

        // set the button to invisible
        button.setVisibility(View.INVISIBLE);

        // Set the OnClick listener for the return back icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name != null) {
                    Intent intent = new Intent(SetingActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    text_error.setText("Empty Name Input");
                }
            }
        });

        // Set the Edittext hints
        name = null;
        // Retrieve the name from intent
        if (getIntent().getStringExtra("word") != null) {
            name = getIntent().getStringExtra("word");
            String[] lines = name.split("\n");

            String var1 = lines[0]; // name text one
            String var2 = lines[1]; // name text two
            String var3 = lines[2]; // name text three
            String var4 = lines[3]; // max text
            String var5 = lines[4]; // live count
            String var6 = lines[5]; // actions done in order
            actions = var5 + "\n" +var6;
            e1.setHint(var1);
            e2.setHint(var2);
            e3.setHint(var3);
            e4.setHint(var4);
        }
        else{
            e1.setHint("Name");
            e2.setHint("Name");
            e3.setHint("Name");
            e4.setHint("Count");

        }

        // Set the OnClick listener for the save button
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String Str_1 = e1.getText().toString();
                String Str_2 = e2.getText().toString();
                String Str_3 = e3.getText().toString();

                try {
                    if (!isValidName(Str_1) && !isValidName(Str_2) && !isValidName(Str_3)) {
                        text_error.setText("Name is invalid");
                    } else if (!isValidCount(Integer.parseInt(e4.getText().toString()))) {
                        text_error.setText("Count is invalid");
                    } else {
                        text_error.setText("");
                        e1.setEnabled(false);
                        e2.setEnabled(false);
                        e3.setEnabled(false);
                        e4.setEnabled(false);

                        actions = 0+"\n"+0+"\n";

                        name = Str_1 + "\n" + Str_2 + "\n" + Str_3 + "\n" + e4.getText().toString() +"\n"+ actions;

                        sharedPreferenceHelper.setName(name);
                        button.setVisibility(View.INVISIBLE);

                        Log.d("action--------------------", actions);
                        Log.d("name--------------------", name);
                        Log.d("name--------------------", sharedPreferenceHelper.getName());

                    }
                }
                catch (NumberFormatException e) {
                    text_error.setText("Invalid input");
                }
            }
        });

    }

    //verify the count is valid
    private boolean isValidCount(int count) {
        return count >= 5 && count <= 200;

    }

    //verify the name is valid
    private boolean isValidName(String str) {
        return str.matches("[a-zA-Z'\\s]+") && str.length() <= 20;
    }

    // 3 point for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu); // Use your actual menu filename

        return true;
    }
    // 3 point for menu action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item) {
            e1.setEnabled(true);
            e2.setEnabled(true);
            e3.setEnabled(true);
            e4.setEnabled(true);
            button.setVisibility(View.VISIBLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
