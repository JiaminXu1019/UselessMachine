package com.example.uselessmachine;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch switchUseless;
    private Button buttonSelfdestruct;
    private ConstraintLayout layout;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }

    private void setListeners() {
        buttonSelfdestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelfDestructSequence();
            }
        });

        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    StartSwitchOffTimer();

                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void startSelfDestructSequence() {
        buttonSelfdestruct.setClickable(false);
        {
            startSelfDestruct();
        }
    }

    private void startSelfDestruct() {
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
             {
                    Toast.makeText(MainActivity.this, "Destruct in: " +  (1000 + millisUntilFinished)/1000, Toast.LENGTH_SHORT).show();
                    if(millisUntilFinished <= 10000 && millisUntilFinished % 2 == 0 )
                        layout.setBackgroundColor(Color.rgb(255, 80, 80));
                    else if(millisUntilFinished <= 10000 && millisUntilFinished % 2 != 0)
                 layout.setBackgroundColor(Color.rgb(255, 255, 255));
             }
            }

            @Override
            public void onFinish() {
                finish();
            }

            }.start();
        }

        private void StartSwitchOffTimer () {
            new CountDownTimer(5000, 100) {
                @Override
                public void onTick(long millsUntilFinish) {
                    if (switchUseless.isChecked()) {
                        Log.d(TAG, "onTick: canceling");
                        cancel();

                    }

                }

                @Override
                public void onFinish() {
                    switchUseless.setChecked(false);
                    Log.d(TAG, "onFinish: Switch set to false");
                }
            }.start();
        }

        private void wireWidgets ()
        {
            buttonSelfdestruct = findViewById(R.id.button_main_selfdestruct);
            switchUseless = findViewById(R.id.switch_main_useless);
            layout = findViewById(R.id.layout_main_background);
        }

    }


