package com.xuechuan.xcedu.fragment.bank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.base.BaseFragment;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: ColligateNewFragment
 * @Package com.xuechuan.xcedu.fragment.bank
 * @Description: 最新综合能力界面
 * @author: L-BackPacker
 * @date: 2018.12.04 下午 4:16
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.04
 */

public class ColligateNewFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView mTvBanknewDoNumber;
    private TextView mTvBanknewAllNumber;
    private TextView mTvBanknewRightNumber;
    private LinearLayout mLlBanknewFree;
    private LinearLayout mLlBanknewSpecial;
    private LinearLayout mLlBanknewOrder;
    private TextView mTvBanknewErrorList;
    private TextView mTvBanknewFavoriteList;
    private TextView mTvBanknewNoteList;
    private LinearLayout mLlBanknewPractice;
    private LinearLayout mLlBanknewExam;
    private LinearLayout mLlBanknewError;
    private LinearLayout mLlBanknewFavaoite;
    private LinearLayout mLlBanknewNote;

    public static ColligateNewFragment newInstance(String param1, String param2) {
        ColligateNewFragment fragment = new ColligateNewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colligate_new, container, false);
        initView(view);
        return view;
    }
*/

    @Override
    protected int initInflateView() {
        return R.layout.fragment_colligate_new;
    }

    @Override
    protected void initCreateView(View view, Bundle savedInstanceState) {
        initView(view);
    }

    private void initView(View view) {
        mTvBanknewDoNumber = (TextView) view.findViewById(R.id.tv_banknew_do_number);
        mTvBanknewAllNumber = (TextView) view.findViewById(R.id.tv_banknew_all_number);
        mTvBanknewRightNumber = (TextView) view.findViewById(R.id.tv_banknew_right_number);
        mLlBanknewFree = (LinearLayout) view.findViewById(R.id.ll_banknew_free);
        mLlBanknewSpecial = (LinearLayout) view.findViewById(R.id.ll_banknew_special);
        mLlBanknewOrder = (LinearLayout) view.findViewById(R.id.ll_banknew_order);
        mTvBanknewErrorList = (TextView) view.findViewById(R.id.tv_banknew_error_list);
        mTvBanknewFavoriteList = (TextView) view.findViewById(R.id.tv_banknew_favorite_list);
        mTvBanknewNoteList = (TextView) view.findViewById(R.id.tv_banknew_note_list);
        mLlBanknewPractice = (LinearLayout) view.findViewById(R.id.ll_banknew_practice);
        mLlBanknewPractice.setOnClickListener(this);
        mLlBanknewExam = (LinearLayout) view.findViewById(R.id.ll_banknew_exam);
        mLlBanknewExam.setOnClickListener(this);
        mLlBanknewError = (LinearLayout) view.findViewById(R.id.ll_banknew_error);
        mLlBanknewError.setOnClickListener(this);
        mLlBanknewFavaoite = (LinearLayout) view.findViewById(R.id.ll_banknew_favaoite);
        mLlBanknewFavaoite.setOnClickListener(this);
        mLlBanknewNote = (LinearLayout) view.findViewById(R.id.ll_banknew_note);
        mLlBanknewNote.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_banknew_practice://章节练习
                break;
            case R.id.ll_banknew_exam://模拟考试
                break;
            case R.id.ll_banknew_free://自由组卷
                break;
            case R.id.ll_banknew_special://专项练习
                break;
            case R.id.ll_banknew_order://顺序练习
                break;
            case R.id.ll_banknew_error://错题集合
                break;
            case R.id.ll_banknew_favaoite://收藏夹
                break;
            case R.id.ll_banknew_note://笔记
                break;
        }
    }
}
