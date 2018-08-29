package com.example.uselessmachine;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch switchUseless;
    private Button buttonSelfdestruct;
    
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }

    private void setListeners(){
        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){

                StartSwitchOffTimer();

                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void StartSwitchOffTimer(){
        new CountDownTimer(5000, 100){
            @Override
            public void onTick(long millsUntilFinish) {
                if(switchUseless.isChecked()){
                    Log.d(TAG, "onTick: canceling");
                    cancel();
                }

            }

            @Override
            public void onFinish() {switchUseless.setChecked(false);
                Log.d(TAG, "onFinish: Switch set to false");
            }
        }.start();
    }

    private void wireWidgets() {
        buttonSelfdestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
    }
}

