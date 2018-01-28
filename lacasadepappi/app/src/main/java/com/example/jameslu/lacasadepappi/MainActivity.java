package com.example.jameslu.lacasadepappi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import java.util.ArrayList;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView myTextView = findViewById(R.id.text);
        Timer timekeeper = new Timer();
        ArrayList<Button> buttons = new ArrayList<Button>();
        LinearLayout buttonContainer = (LinearLayout) findViewById(R.id.buttonContainer);


        //button.setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View v) {
                // Code here executes on main thread after user presses button
         //       myTextView.setVisibility(View.VISIBLE);
         //   }
        //});

        for (int i = 0; i < 8; i++){
            Button buttonTest = new Button(this);
            buttonContainer.addView(buttonTest);

        }

    }





}
