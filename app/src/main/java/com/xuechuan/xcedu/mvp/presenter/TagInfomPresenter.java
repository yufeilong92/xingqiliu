package com.xuechuan.xcedu.mvp.presenter;

import android.content.Context;

import com.xuechuan.xcedu.mvp.model.TagInfomModel;
import com.xuechuan.xcedu.mvp.view.RequestResulteView;
import com.xuechuan.xcedu.mvp.view.TagInfomView;
import com.xuechuan.xcedu.utils.StringUtil;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.mvp.presenter
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018/5/13 13:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class TagInfomPresenter {
    private TagInfomModel model;
    private TagInfomView view;

    public TagInfomPresenter(TagInfomModel model, TagInfomView view) {
        this.model = model;
        this.view = view;
    }

    public void requestTagNewCont(Context context, int page, String tagid) {
        if (StringUtil.isEmpty(tagid))
            return;
        model.reqeustInfomWithTag(context, page, tagid, new RequestResulteView() {
            @Override
            public void success(String result) {
                view.ArticleTagOneConSuccess(result);
            }

            @Override
            public void error(String result) {
                view.ArticleTagOneConError(result);
            }
        });
    }

    public void requestTagMoreCont(Context context, int page, String tagid) {
        if (StringUtil.isEmpty(tagid))
            return;
        model.reqeustInfomWithTag(context, page, tagid, new RequestResulteView() {
            @Override
            public void success(String result) {
                view.ArticleTagMoreConSuccess(result);
            }

            @Override
            public void error(String result) {
                view.ArticleTagMoreConError(result);
            }
        });
    }
}
