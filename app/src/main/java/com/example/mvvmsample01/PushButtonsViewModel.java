package com.example.mvvmsample01;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;

public class PushButtonsViewModel extends ViewModel {

    public Observable<Boolean> isAllOn;
    private HashMap<Integer, Observable<Boolean>> buttonOnOffMap;
    private PushButtons pushButtons;

    public PushButtonsViewModel(int rowCount, int colCount) {
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

    static class PushButtonsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

        private int rowCount;
        private int colCount;

        public PushButtonsViewModelFactory(int rowCount, int colCount) {
            this.rowCount = rowCount;
            this.colCount = colCount;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PushButtonsViewModel(rowCount, colCount);
        }
    }
}
