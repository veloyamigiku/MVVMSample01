package com.example.mvvmsample01;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;

public class MainViewModel {

    public Observable<Boolean> isAllOn;
    private HashMap<Integer, Observable<Boolean>> buttonOnOffMap;
    private PushButtons pushButtons;

    public MainViewModel() {
        pushButtons = new PushButtons();
        isAllOn = pushButtons.isAllOn;
        buttonOnOffMap = new HashMap<>();
        for (int buttonNumber : pushButtons.getButtonNumbers()) {
            buttonOnOffMap.put(buttonNumber, pushButtons.getButtonOnOff(buttonNumber));
        }
    }

    public Observable<Boolean> getObservableOnOff(int buttonNumber) {
        return buttonOnOffMap.get(buttonNumber);
    }

    public void pushButton(int buttonNumber) {
        pushButtons.push(buttonNumber);
    }

}
