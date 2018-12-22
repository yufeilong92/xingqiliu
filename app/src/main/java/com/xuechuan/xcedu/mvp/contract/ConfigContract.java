package com.xuechuan.xcedu.mvp.contract;

import android.content.Context;

import com.xuechuan.xcedu.mvp.view.RequestResulteView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.mvp.contract
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.11.27 上午 10:41
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public interface ConfigContract {
    interface Model {
        public void requestConfig(Context context, RequestResulteView resulteView);
    }

    interface View {
        public void ConfigSuccess(String success);
        public void ConfigError(String msg);
    }

    interface Presenter {
         public void initModelView(Model model, View view);
        public void requestConfig(Context context);
    }
}
