package com.example.mvvmsample01;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.view.RxView;

public class MainActivity extends AppCompatActivity {

    private static final int[][] PUSH_BUTTONS_ID_ARY = new int[][] {
            {R.id.button1, R.id.button2, R.id.button3},
            {R.id.button4, R.id.button5, R.id.button6},
            {R.id.button7, R.id.button8, R.id.button9},
    };
    private static final int PUSH_BUTTONS_ROW_COUNT = PUSH_BUTTONS_ID_ARY.length;
    private static final int PUSH_BUTTONS_COL_COUNT = PUSH_BUTTONS_ID_ARY[0].length;
    private PushButtonsModel pbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindEventOfView();

    }

    private void bindEventOfButton(Button button, int buttonNumber) {

        RxView.clicks(button).subscribe(e -> {
            pbm.pushButton(buttonNumber);
        });

        pbm.getObservableOnOff(buttonNumber).subscribe(onOff -> {
            Log.d(MainActivity.class.getSimpleName(), String.valueOf(buttonNumber));
            if (onOff) {
                button.setBackgroundColor(Color.RED);
            } else {
                button.setBackgroundColor(Color.GRAY);
            }
        });
    }

    private void bindEventOfView() {

        pbm = new PushButtonsModel(
                PUSH_BUTTONS_ROW_COUNT,
                PUSH_BUTTONS_COL_COUNT);

        int buttonNumber = 1;
        for (int i = 0; i < PUSH_BUTTONS_ROW_COUNT; i++) {
            for (int j = 0; j < PUSH_BUTTONS_COL_COUNT; j++) {
                Button button = findViewById(PUSH_BUTTONS_ID_ARY[i][j]);
                bindEventOfButton(button, buttonNumber);
                buttonNumber++;
            }
        }

        pbm.isAllOn.subscribe(isAllOn -> {
            Log.d(MainActivity.class.getSimpleName(), "AllOn:" + isAllOn);
        });
    }

}
