package com.example.mvvmsample01;

import org.junit.Assert;
import org.junit.Test;

public class PushButtonsTest {

    @Test
    public void test01() {

        // 初期化時の状態をテストする。
        PushButtons pushButtons = new PushButtons();
        Boolean[] onOffList = pushButtons.getOnOffList();
        boolean isAllOn = pushButtons.isAllOn();
        int onCount = 0;
        for (boolean onOff : onOffList) {
            if (onOff) {
                onCount++;
            }
        }
        Assert.assertEquals(0, onCount);
        Assert.assertEquals(isAllOn, false);
    }

    @Test
    public void test02() {

        // 中央のボタンをクリックした時の状態をテストする。
        PushButtons pushButtons = new PushButtons();
        pushButtons.push(5);
        Boolean[] onOffList = pushButtons.getOnOffList();
        Assert.assertArrayEquals(
                new Boolean[]{false, true, false, true, true, true, false, true, false},
                pushButtons.getOnOffList());
        boolean isAllOn = pushButtons.isAllOn();
        int onCount = 0;
        for (boolean onOff : onOffList) {
            if (onOff) {
                onCount++;
            }
        }
        Assert.assertEquals(5, onCount);
        Assert.assertEquals(isAllOn, false);

    }

}
