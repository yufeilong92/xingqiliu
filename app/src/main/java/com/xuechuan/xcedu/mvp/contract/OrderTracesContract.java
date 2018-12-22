package com.xuechuan.xcedu.mvp.contract;

import android.content.Context;

import com.xuechuan.xcedu.mvp.view.RequestResulteView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.mvp.contract
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018/8/28 10:46
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public interface OrderTracesContract {
    interface Model {
        public void requestOrderTraces(Context context,int code, RequestResulteView view);

    }

    interface View {
        public void TracesSuccess(String success);

        public void TracesError(String msg);
    }

    interface Presenter {
        public void initModelView(Model model,View view);
        public void requestOrderTraces(Context context, int code);

    }
}
