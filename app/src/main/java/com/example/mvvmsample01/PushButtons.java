package com.example.mvvmsample01;

import com.jakewharton.rxrelay3.BehaviorRelay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class PushButtons {

    private final int rowCount = 3;

    private final int colCount = 3;

    private PushButton[][] buttons;

    private HashMap<Integer, PushButton> buttonNumberMap;

    private HashMap<Integer, BehaviorRelay<Boolean>> buttonOnOffMap;

    private BehaviorRelay<Boolean> isAllOnRelay;

    public Observable<Boolean> isAllOn;

    public PushButtons() {

        buttons = new PushButton[rowCount][colCount];
        buttonNumberMap = new HashMap<>();
        buttonOnOffMap = new HashMap<>();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                int buttonNumber = i * colCount + j + 1;
                PushButton button = new PushButton(buttonNumber, false, i, j);
                buttons[i][j] = button;
                buttonNumberMap.put(buttonNumber, button);
                buttonOnOffMap.put(buttonNumber, BehaviorRelay.createDefault(false));
            }
        }

        isAllOnRelay = BehaviorRelay.createDefault(false);
        isAllOn = isAllOnRelay.hide();

    }

    public ArrayList<Integer> getButtonNumbers() {
        ArrayList<Integer> buttonNumbers = new ArrayList<>();
        for (int buttonNumber : buttonNumberMap.keySet()) {
            buttonNumbers.add(buttonNumber);
        }
        Collections.sort(buttonNumbers);
        return buttonNumbers;
    }

    public Observable<Boolean> getButtonOnOff(int buttonNumber) {
        return buttonOnOffMap.get(buttonNumber).hide();
    }

    private void isAllOn() {
        int allOnCount = rowCount * colCount;
        int onCount = 0;
        for (Map.Entry<Integer, PushButton> entry : buttonNumberMap.entrySet()) {
            if (entry.getValue().isOn()) {
                onCount++;
            }
        }
        if (allOnCount == onCount) {
            isAllOnRelay.accept(true);
        } else {
            isAllOnRelay.accept(false);
        }
    }

    /*
    public Boolean[] getOnOffList() {
        Boolean[] onAry = new Boolean[rowCount * colCount];
        for (Map.Entry<Integer, PushButton> entry : buttonNumberMap.entrySet()) {
            onAry[entry.getKey() - 1] = entry.getValue().isOn();
        }
        return onAry;
    }
    */

    public void push(int buttonNumber) {

        PushButton button = buttonNumberMap.get(buttonNumber);

        int rowIndex = button.getRowIndex();
        int colIndex = button.getColIndex();

        // 押したボタンと周りのボタンのオン・オフを切り替える。
        // 押したボタンの上にボタンがある場合。
        if (rowIndex - 1 >= 0) {
            PushButton topButton = buttons[rowIndex - 1][colIndex];
            topButton.setOn(!topButton.isOn());
            buttonOnOffMap.get(topButton.getNumber()).accept(topButton.isOn());
        }
        // 押したボタンの左にボタンがある場合。
        if (colIndex - 1 >= 0) {
            PushButton leftButton = buttons[rowIndex][colIndex - 1];
            leftButton.setOn(!leftButton.isOn());
            buttonOnOffMap.get(leftButton.getNumber()).accept(leftButton.isOn());
        }
        // 押したボタンの右にボタンがある場合。
        if (colIndex + 1 < colCount) {
            PushButton rightButton = buttons[rowIndex][colIndex + 1];
            rightButton.setOn(!rightButton.isOn());
            buttonOnOffMap.get(rightButton.getNumber()).accept(rightButton.isOn());
        }
        // 押したボタンの下にボタンがある場合。
        if (rowIndex + 1 < rowCount) {
            PushButton bottomButton = buttons[rowIndex + 1][colIndex];
            bottomButton.setOn(!bottomButton.isOn());
            buttonOnOffMap.get(bottomButton.getNumber()).accept(bottomButton.isOn());
        }
        button.setOn(!button.isOn());
        buttonOnOffMap.get(buttonNumber).accept(button.isOn());

        isAllOn();
    }

}
