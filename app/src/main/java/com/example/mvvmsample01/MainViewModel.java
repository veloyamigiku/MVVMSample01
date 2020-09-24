package com.example.mvvmsample01;

import com.jakewharton.rxrelay3.BehaviorRelay;

import io.reactivex.rxjava3.core.Observable;

public class MainViewModel {

    private BehaviorRelay<Boolean> isAllOnRelay;
    public Observable<Boolean> isAllOn;
    private BehaviorRelay<Boolean[]> onOffRelay;
    public Observable<Boolean[]> onOff;
    private PushButtons pushButtons;

    public MainViewModel() {
        pushButtons = new PushButtons();
        isAllOnRelay = BehaviorRelay.createDefault(pushButtons.isAllOn());
        isAllOn = isAllOnRelay;
        onOffRelay = BehaviorRelay.createDefault(pushButtons.getOnOffList());
        onOff = onOffRelay;
    }

    public void pushButton(int buttonNumber) {
        pushButtons.push(buttonNumber);
        isAllOnRelay.accept(pushButtons.isAllOn());
        onOffRelay.accept(pushButtons.getOnOffList());
    }

}
