package com.example.mvvmsample01;

public class PushButton {

    private int number;
    private boolean on;
    private int rowIndex;
    private int colIndex;

    public int getNumber() {
        return number;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public PushButton(
            int number,
            boolean on,
            int rowIndex,
            int colIndex) {
        this.number = number;
        this.on = on;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

}
