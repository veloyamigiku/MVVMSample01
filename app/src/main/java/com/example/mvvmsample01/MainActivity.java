package com.example.mvvmsample01;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.view.RxView;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private HashMap<Integer, Button> buttonNumberMap;
    private MainViewModel mvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindEventOfView();

        mvm.isAllOn
                .subscribe(
                        isAllOn -> {
                            Log.d(MainActivity.class.getSimpleName(), "AllOn");
                        }
                );
        mvm.onOff
                .subscribe(
                        onOffList -> {
                            for (int i = 0; i < onOffList.length; i++) {
                                // todo ここから再開。
                            }
                        }
                )
    }

    private void bindEventOfButton(Button button, int buttonNumber) {
        RxView.clicks(button)
                .subscribe(e -> {
                    mvm.pushButton(buttonNumber);
                });
        buttonNumberMap.put(buttonNumber, button);
    }

    private void bindEventOfView() {

        mvm = new MainViewModel();

        button1 = findViewById(R.id.button1);
        bindEventOfButton(button1, 1);
        button2 = findViewById(R.id.button2);
        bindEventOfButton(button2, 2);
        button3 = findViewById(R.id.button3);
        bindEventOfButton(button3, 3);
        button4 = findViewById(R.id.button4);
        bindEventOfButton(button4, 4);
        button5 = findViewById(R.id.button5);
        bindEventOfButton(button5, 5);
        button6 = findViewById(R.id.button6);
        bindEventOfButton(button6, 6);
        button7 = findViewById(R.id.button7);
        bindEventOfButton(button7, 7);
        button8 = findViewById(R.id.button8);
        bindEventOfButton(button8, 8);

    }
}