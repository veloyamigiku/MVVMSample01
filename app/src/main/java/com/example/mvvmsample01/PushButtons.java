package com.example.mvvmsample01;

import java.util.HashMap;

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

    public void push(int buttonNumber) {

        PushButton button = buttonNumberMap.get(buttonNumber);
        

    }

}
