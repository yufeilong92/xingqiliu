package com.xuechuan.xcedu.event;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.event
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.11.30 上午 9:38
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class ScrollEvent {
    private String type;

    public ScrollEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
