package com.xuechuan.xcedu.mvp.presenter;

import android.content.Context;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.mvp.model.ErrOrCollModel;
import com.xuechuan.xcedu.mvp.view.RequestResulteView;
import com.xuechuan.xcedu.mvp.view.ErrOrColNumView;
import com.xuechuan.xcedu.utils.StringUtil;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: SkillPresenter.java
 * @Package com.xuechuan.xcedu.mvp.presenter
 * @Description: todo
 * @author: YFL
 * @date: 2018/5/4 00:02
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/5/4 星期五
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class ErrOrColPresenter {
    private ErrOrCollModel model;
    private ErrOrColNumView view;


    public ErrOrColPresenter(ErrOrCollModel model, ErrOrColNumView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * 获取题号
     *
     * @param context
     * @param courseid
     */
    public void getErrOrCollNumber(Context context, String courseid) {
        if (StringUtil.isEmpty(courseid)) {
            return;
        }
        model.reqeustErrOrCoNumber(context, courseid, new RequestResulteView() {
            @Override
            public void success(String result) {
                view.ErrorOrCollortNumberSuccess(result);
            }

            @Override
            public void error(String result) {
                view.ErrorOrCollortNumberError(result);
            }
        });
    }





}
