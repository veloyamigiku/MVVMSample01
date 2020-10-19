package com.example.mvvmsample01;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.jakewharton.rxbinding4.view.RxView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements InfoDialogFragment.InfoDialogListener {

    private static final int[][] PUSH_BUTTONS_ID_ARY = new int[][] {
            {R.id.button1, R.id.button2, R.id.button3},
            {R.id.button4, R.id.button5, R.id.button6},
            {R.id.button7, R.id.button8, R.id.button9},
    };
    private static final int PUSH_BUTTONS_ROW_COUNT = PUSH_BUTTONS_ID_ARY.length;
    private static final int PUSH_BUTTONS_COL_COUNT = PUSH_BUTTONS_ID_ARY[0].length;
    private PushButtonsViewModel pbvm;
    private FragmentManager fm;
    private CompositeDisposable cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindEventOfView(savedInstanceState);

    }

    private void bindEventOfButton(Button button, int buttonNumber) {

        cd.add(RxView.clicks(button).subscribe(e -> {
            pbvm.pushButton(buttonNumber);
        }));

        cd.add(pbvm.getObservableOnOff(buttonNumber).subscribe(onOff -> {
            Log.d(MainActivity.class.getSimpleName(), String.valueOf(buttonNumber));
            if (onOff) {
                button.setBackgroundColor(Color.RED);
            } else {
                button.setBackgroundColor(Color.GRAY);
            }
        }));
    }

    private void bindEventOfView(Bundle saved) {

        cd = new CompositeDisposable();

        pbvm = new ViewModelProvider(
                this,
                new PushButtonsViewModel.PushButtonsViewModelFactory(PUSH_BUTTONS_ROW_COUNT, PUSH_BUTTONS_COL_COUNT))
                .get(PushButtonsViewModel.class);
        int buttonNumber = 1;
        for (int i = 0; i < PUSH_BUTTONS_ROW_COUNT; i++) {
            for (int j = 0; j < PUSH_BUTTONS_COL_COUNT; j++) {
                Button button = findViewById(PUSH_BUTTONS_ID_ARY[i][j]);
                bindEventOfButton(button, buttonNumber);
                buttonNumber++;
            }
        }

        cd.add(pbvm.isAllOn.subscribe(isAllOn -> {
            Log.d(MainActivity.class.getSimpleName(), "AllOn:" + isAllOn);
            if (isAllOn) {

                InfoDialogFragment dialogFragment = InfoDialogFragment.newInstance(
                        "Puzzle",
                        "Puzzle Clear",
                        "OK",
                        "Puzzle Clear");
                dialogFragment.show(getSupportFragmentManager(), MainActivity.class.getSimpleName());
                pbvm.clearButton();
            }
        }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        cd.clear();
    }

    @Override
    public void onPositiveButtonPush(String tag) {
        switch (tag) {
            case "Puzzle Clear":
                pbvm.clearButton();
                break;
            default:
                break;
        }
    }
}