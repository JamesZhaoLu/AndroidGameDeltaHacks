package com.example.jameslu.lacasadepappi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.media.MediaPlayer;

// Java stuff
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Animation stuff
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.os.Handler;
import android.graphics.PorterDuff;

// Timer Classes
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    long startTime = 0;
    ArrayList<Integer> sequence = new ArrayList<Integer>();
    // Create ArrayList of Buttons
    ArrayList<Button> buttons = new ArrayList<Button>();

    // ArrayList of Colours
    ArrayList<String> coulours = new ArrayList<String>(Arrays.asList("#ffbcbc", "#ffcebc", "#fff0b2", "#e9ffb2",
            "#c7ffb2", "#b2f7b7", "#b1f7e1", "#b1f7f7", "#b1dbf7", "#b1bbf7", "#cdb1f7", "#eab1f7", "#f7b1f0", "#f7b1d8", "#f7b1b1"));
    ArrayList<String> reds = new ArrayList<String>(Arrays.asList("#bc1a0b", "#e02918", "#ef402f", "#ff5138","#f45942","#ff7f6d","#f49c90","#ffd2cc","#f7e5e3", "#fff5f4"));
    ArrayList<String> yellows = new ArrayList<String>(Arrays.asList("#f4f0e3", "#ede0b8", "#d8b48a", "#e2ae71", "#eda95a", "#ed9836", "#e58516", "#d18812"));
    ArrayList<String> greens = new ArrayList<String>(Arrays.asList("#0a9647", "#17aa57", "#28bf69", "#43db85", "#65d897", "#8bedb6", "#a9e8c4", "#d2f7e2", "#e3efe8"));
    ArrayList<String> purples = new ArrayList<String>(Arrays.asList("#e3e9ef", "#bfd8f2", "#bfbff1", "#c9bef0", "#d9bdef", "#bf93e2", "#a775ce", "#9156bf", "#b856bf", "#bf569f"));

    //counts number
    int counter = 0;
    // Random number

    int prev = 0;
    // saves previous number

    Random rand =  new Random();

    //background music
    MediaPlayer music;


    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);

            // get next int!
            int n = rand.nextInt(9);

            // put it in the sequence

            if (seconds % 2 == 0){
                if (counter < 10)  {
                    buttons.get(n).getBackground().setColorFilter(Color.parseColor(reds.get(counter)), PorterDuff.Mode.DARKEN);
                } else if (counter < 18){
                    buttons.get(n).getBackground().setColorFilter(Color.parseColor(yellows.get(counter - 10)), PorterDuff.Mode.DARKEN);
                } else if (counter < 27){
                    buttons.get(n).getBackground().setColorFilter(Color.parseColor(greens.get(counter - 18)), PorterDuff.Mode.DARKEN);
                } else if (counter < 37) {
                    buttons.get(n).getBackground().setColorFilter(Color.parseColor(purples.get(counter - 27)), PorterDuff.Mode.DARKEN);
                }
                sequence.add(n);
                // check to redo loop
                if (counter == 37) counter = 0;
                else counter++;
                prev = n;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();

// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // add buttons to ArrayList
        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));
        buttons.add((Button) findViewById(R.id.button5));
        buttons.add((Button) findViewById(R.id.button6));
        buttons.add((Button) findViewById(R.id.button7));
        buttons.add((Button) findViewById(R.id.button8));
        buttons.add((Button) findViewById(R.id.button9));


        //buttons and text views declared to use timer
        Button b = (Button) findViewById(R.id.startButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                music = MediaPlayer.create(MainActivity.this,R.raw.calmmuisc);
                music.start();
                if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");

                    for(int i = 0; i < 9; ++i) {
                        buttons.get(i).getBackground().setColorFilter(Color.parseColor("#b2cfb5"), PorterDuff.Mode.DARKEN);
                    }
                } else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    b.setText("stop");
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.startButton);
        b.setText("start");
    }



    public static void intializeValues(ArrayList<Integer> initialSequence, int numTimes){
        //initialize the sequence of ArrayList for 3 integers

        Random rand = new Random();

        for (int i = 0; i < numTimes; i++){
            initialSequence.add(rand.nextInt(9));
        }
    }

}
