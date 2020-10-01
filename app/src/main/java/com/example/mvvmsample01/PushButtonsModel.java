package com.example.mvvmsample01;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;

public class PushButtonsModel {

    public Observable<Boolean> isAllOn;
    private HashMap<Integer, Observable<Boolean>> buttonOnOffMap;
    private PushButtons pushButtons;

    public PushButtonsModel(int rowCount, int colCount) {
        pushButtons = new PushButtons(rowCount, colCount);
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

    public void clearButton() {
        pushButtons.clear();
    }

}
