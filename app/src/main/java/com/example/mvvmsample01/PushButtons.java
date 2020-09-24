package com.example.mvvmsample01;

import java.util.HashMap;
import java.util.Map;

public class PushButtons {

    private final int rowCount = 3;

    private final int colCount = 3;

    private PushButton[][] buttons;

    private HashMap<Integer, PushButton> buttonNumberMap;

    public PushButtons() {

        buttons = new PushButton[rowCount][colCount];
        buttonNumberMap = new HashMap<>();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                int buttonNumber = i * colCount + j + 1;
                PushButton button = new PushButton(buttonNumber, false, i, j);
                buttons[i][j] = button;
                buttonNumberMap.put(buttonNumber, button);
            }
        }

    }

    public Boolean isAllOn() {
        int allOnCount = rowCount * colCount;
        int onCount = 0;
        for (Map.Entry<Integer, PushButton> entry : buttonNumberMap.entrySet()) {
            if (entry.getValue().isOn()) {
                onCount++;
            }
        }
        return allOnCount == onCount;
    }

    public Boolean[] getOnOffList() {
        Boolean[] onAry = new Boolean[rowCount * colCount];
        for (Map.Entry<Integer, PushButton> entry : buttonNumberMap.entrySet()) {
            onAry[entry.getKey() - 1] = entry.getValue().isOn();
        }
        return onAry;
    }

    public void push(int buttonNumber) {

        PushButton button = buttonNumberMap.get(buttonNumber);

        int rowIndex = button.getRowIndex();
        int colIndex = button.getColIndex();

        // 押したボタンと周りのボタンのオン・オフを切り替える。
        // 押したボタンの上にボタンがある場合。
        if (rowIndex - 1 >= 0) {
            buttons[rowIndex - 1][colIndex].setOn(!buttons[rowIndex - 1][colIndex].isOn());
        }
        // 押したボタンの左にボタンがある場合。
        if (colIndex - 1 >= 0) {
            buttons[rowIndex][colIndex - 1].setOn(!buttons[rowIndex][colIndex - 1].isOn());
        }
        // 押したボタンの右にボタンがある場合。
        if (colIndex + 1 < colCount) {
            buttons[rowIndex][colIndex + 1].setOn(!buttons[rowIndex][colIndex + 1].isOn());
        }
        // 押したボタンの下にボタンがある場合。
        if (rowIndex + 1 < rowCount) {
            buttons[rowIndex + 1][colIndex].setOn(!buttons[rowIndex][colIndex].isOn());
        }
        buttons[rowIndex][colIndex].setOn(!buttons[rowIndex][colIndex].isOn());

    }

}
