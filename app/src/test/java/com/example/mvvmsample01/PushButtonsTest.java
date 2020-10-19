package com.example.mvvmsample01;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class PushButtonsTest {

    @Test
    public void test01() {

        // 初期化時の状態をテストする。
        PushButtons pushButtons = new PushButtons(3, 3);
        HashMap<Integer, Boolean> expectButtonOnOff = new HashMap<>();
        expectButtonOnOff.put(1, false);
        expectButtonOnOff.put(2, false);
        expectButtonOnOff.put(3, false);
        expectButtonOnOff.put(4, false);
        expectButtonOnOff.put(5, false);
        expectButtonOnOff.put(6, false);
        expectButtonOnOff.put(7, false);
        expectButtonOnOff.put(8, false);
        expectButtonOnOff.put(9, false);
        for (Map.Entry<Integer, Boolean> entry : expectButtonOnOff.entrySet()) {
            int buttonNumber = entry.getKey();
            boolean onOff = entry.getValue();
            Observable<Boolean> buttonOnOff = pushButtons.getButtonOnOff(buttonNumber);
            buttonOnOff.test().assertValue(onOff);
        }
        Observable<Boolean> isAllOn = pushButtons.isAllOn;
        isAllOn.test().assertValue(false);
    }

    @Test
    public void test02() {

        // 中央のボタンをクリックした時の状態をテストする。
        PushButtons pushButtons = new PushButtons(3, 3);
        pushButtons.push(5);
        HashMap<Integer, Boolean> expectButtonOnOff = new HashMap<>();
        expectButtonOnOff.put(1, false);
        expectButtonOnOff.put(2, true);
        expectButtonOnOff.put(3, false);
        expectButtonOnOff.put(4, true);
        expectButtonOnOff.put(5, true);
        expectButtonOnOff.put(6, true);
        expectButtonOnOff.put(7, false);
        expectButtonOnOff.put(8, true);
        expectButtonOnOff.put(9, false);
        for (Map.Entry<Integer, Boolean> entry : expectButtonOnOff.entrySet()) {
            int buttonNumber = entry.getKey();
            boolean onOff = entry.getValue();
            Observable<Boolean> buttonOnOff = pushButtons.getButtonOnOff(buttonNumber);
            buttonOnOff.test().assertValue(onOff);
        }
        Observable<Boolean> isAllOn = pushButtons.isAllOn;
        isAllOn.test().assertValue(false);

    }

}
