package com.xuechuan.xcedu.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etiennelawlor.discreteslider.library.ui.DiscreteSlider;
import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.adapter.bank.AnswerEvaluateAdapter;
import com.xuechuan.xcedu.adapter.bank.GmEvaluateAdapter;
import com.xuechuan.xcedu.base.BaseFragment;
import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.mvp.contract.GmEvalueContract;
import com.xuechuan.xcedu.mvp.model.GmEvalueModel;
import com.xuechuan.xcedu.mvp.presenter.GmEvaluePresenter;
import com.xuechuan.xcedu.sqlitedb.UserInfomDbHelp;
import com.xuechuan.xcedu.ui.bank.NewTextActivity;
import com.xuechuan.xcedu.ui.bank.OrderTestActivity;
import com.xuechuan.xcedu.utils.GmFontSizeUtil;
import com.xuechuan.xcedu.utils.GmReadColorManger;
import com.xuechuan.xcedu.utils.GmSelectImgManageUtil;
import com.xuechuan.xcedu.utils.GmTextUtil;
import com.xuechuan.xcedu.utils.StringUtil;
import com.xuechuan.xcedu.utils.Subsciber;
import com.xuechuan.xcedu.utils.TestObserver;
import com.xuechuan.xcedu.utils.Utils;
import com.xuechuan.xcedu.vo.EvalueNewVo;
import com.xuechuan.xcedu.vo.SqliteVo.DoBankSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.LookSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.UserInfomSqliteVo;
import com.xuechuan.xcedu.weight.CommonPopupWindow;
import com.xuechuan.xcedu.weight.FlowLayout;
import com.xuechuan.xcedu.weight.WeiNestedScrollView;


import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: ReadFragment
 * @Package com.xuechuan.xcedu.fragment
 * @Description: 通用做题界面
 * @author: L-BackPacker
 * @date: 2018.12.05 下午 5:04
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.05
 */

public class GmReadFragment extends BaseFragment implements TestObserver, View.OnClickListener, GmEvalueContract.View {

    private static final String ARG_PARAM_DATA = "data";
    private static final String ARG_PARAM_COUID = "couid";
    private static final String ARG_PARAM_POSTION = "postion";
    private static String TAG = "【" + GmReadFragment.class + "】==";
    private View view;
    private QuestionSqliteVo mQuestionSqliteVo;
    private TextView mTvContent;
    private Context mContext;

    private String mCouresid;
    /**
     * 判断用户是否购买
     */
    private boolean mUserBuy = false;
    private GmReadColorManger mGmReadColorManger;
    private GmSelectImgManageUtil mGmImgManageUtil;
    private TextView mTvGmTitleFirst;
    private TextView mTvGmTitleAllnumber;
    private TextView mTvGmTitleTextLast;
    private ImageView mIvGmTitleMulu;
    private TextView mTvGmTitleTextStem;
    private LinearLayout mLlGmtitleSelectLayout;
    private TextView mTvGmoneTitleBank;
    private ImageView mIvGmOneA;
    private TextView mTvGmOneAContent;
    private ImageView mIvGmOneB;
    private TextView mTvGmOneBContent;
    private ImageView mIvGmOneC;
    private TextView mTvGmOneCContent;
    private ImageView mIvGmOneD;
    private TextView mTvGmOneDContent;
    private ImageView mIvGmOneE;
    private TextView mTvGmOneEContent;
    private LinearLayout mLlGmOneELayout;
    private Button mBtnGmoneSuerAnswer;
    private View mVGmOneLine;
    private TextView mTvGmOneAnswerTitle;
    private TextView mTvGmOneAnswerContent;
    private LinearLayout mLlGmoneAnswerLayout;
    private LinearLayout mLlGmOneSelectLayout;
    private Button mBtnBBuy;
    private LinearLayout mLiBResolveBuy;
    private TextView mTvGmTwoJiexiTitle;
    private TextView mTvGmTwoJiexiContent;
    private TextView mTvGmTwoDifficultyTitle;
    private ImageView mIvGmTwoStarOne;
    private ImageView mIvGmTwoStarTwo;
    private ImageView mIvGmTwoStarThree;
    private ImageView mIvGmTwoStarFour;
    private ImageView mIvGmTwoStarFive;
    private TextView mTvGmTwoRightTitle;
    private TextView mTvGmTwoRightContent;
    private TextView mTvGmTwoCentreTitle;
    private FlowLayout mFlowlayoutGmTwo;
    private View mVGmTwoLineTwo;
    private LinearLayout mLlGmTwoAnalysisLayout;
    private TextView mTvGmThreeNoteTitle;
    private TextView mTvGmThreeNote;
    private View mVGmThreeLine;
    private LinearLayout mLlGmthreeNoteLayout;
    private TextView mTvGmfourEvaluateTitle;
    private TextView mTvGmfourNuber;
    private ImageView mIvGmfourEvaluate;
    private RecyclerView mRlvGmfourContent;
    private RelativeLayout mRlGmfourLayout;
    private LinearLayout mLlGmfourMoreData;
    private LinearLayout mLlFourEvaluateLayout;
    private WeiNestedScrollView mSmsvLayout;
    private ImageView mIvNetEmptyContent;
    private Subsciber mSubsciber;
    private LinearLayout mLlGmoneALayout;
    private LinearLayout mLlGmoneBLayout;
    private LinearLayout mLlGmoneCLayout;
    private LinearLayout mLlGmoneDLayout;
    private GmTextUtil mGmTextUtil;
    private NewTextActivity mNewTextActivity;
    /**
     * 选项A
     */
    private String mSelectA = "A";
    /**
     * 选项B
     */
    private String mSelectB = "B";
    /**
     * 选项C
     */
    private String mSelectC = "C";
    /**
     * 选项D
     */
    private String mSelectD = "D";
    /**
     * 选项E
     */
    private String mSelectE = "E";

    //多选状况
    private String mSelectMorItemA = null;
    private String mSelectMorItemB = null;
    private String mSelectMorItemC = null;
    private String mSelectMorItemD = null;
    private String mSelectMorItemE = null;
    //记录用户是否点击选项
    private boolean mSelectMorisClickA;
    private boolean mSelectMorisClickB;
    private boolean mSelectMorisClickC;
    private boolean mSelectMorisClickD;
    private boolean mSelectMorisClickE;
    //用户是否确认多选
    private boolean mDuoXunaSure = true;
    /**
     * ,问题类型 2,单选题；3,多选题；4,简答题
     */
    private int mQuestionType;
    private View mViewGmtwoLine;
    /**
     * 集合数据
     */
    private ArrayList mArray;
    /**
     * 是否显示加载更多
     */
    private boolean mShowMoreView = false;
    //评价适配器
//    private AnswerEvaluateAdapter mEvalueAdapter;
    private GmEvaluateAdapter mEvalueAdapter;
    private GmEvaluePresenter mGmEvaluePresenter;
    private CommonPopupWindow mPopSetting;
    private LinearLayout mGmRoolLayout;
    //用户是否显示自动跳转
    private String mUserNextGo;
    //用户选中模式
    private String mShowDayOrNight;
    //用户选中删除次数
    private String mDeletenumber;

    private UserInfomDbHelp mUserDbHelp;
    //字体大小
    private String mFoneSize;
    private GmFontSizeUtil mGmFontSizeUtil;
    private View mVGmtwoLineThree;
    private int mPostion;
    private int mSaffid;
    private OrderTestActivity mOrderTestActivity;

    /***
     *
     * @param vo 数据类型
     * @param mCouserid 科目id 1 技术，2 综合，3 案例
     * @return
     */
    public static GmReadFragment newInstance(QuestionSqliteVo vo, int postion, String mCouserid) {
        GmReadFragment fragment = new GmReadFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_DATA, vo);
        args.putString(ARG_PARAM_COUID, mCouserid);
        args.putInt(ARG_PARAM_POSTION, postion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestionSqliteVo = (QuestionSqliteVo) getArguments().getSerializable(ARG_PARAM_DATA);
            mCouresid = getArguments().getString(ARG_PARAM_COUID);
            mPostion = getArguments().getInt(ARG_PARAM_POSTION);
        }

    }

/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bant_text_layout, container, false);
        initView(view);
        return view;
    }*/

    @Override
    protected int initInflateView() {
        return R.layout.fragment_bant_text_layout;
    }

    @Override
    protected void initCreateView(View view, Bundle savedInstanceState) {
        initView(view);
        if (mQuestionSqliteVo == null) {
            //滑动布局
            mSmsvLayout.setVisibility(View.GONE);
            mIvNetEmptyContent.setVisibility(View.VISIBLE);
            return;
        } else {
            //滑动布局
            mSmsvLayout.setVisibility(View.VISIBLE);
            mIvNetEmptyContent.setVisibility(View.GONE);
        }
        //初始话用户购买情况
        initUserBuy();
        //初始化配置
        initSetting();
        //初始化工具类
        initUtils();
        //初始化界面
        initShowView();
        //解读数据
        readDataBindView();
        //初始化适配器
        initEvalueAdapter();
        //注册观察者
        registerOberovce();

    }

    private void initSetting() {
        if (mUserDbHelp == null) {
            mUserDbHelp = UserInfomDbHelp.get_Instance(mContext);
        }
        UserInfomSqliteVo vo = mUserDbHelp.findUserInfomVo();
        if (vo == null) {
            return;
        }
        mSaffid = vo.getSaffid();
        //是否自动跳转下一题
        mUserNextGo = vo.getUserNextGo();
        //用户选中得模式
        mShowDayOrNight = vo.getShowDayOrNight();
        //用户删除次数
        mDeletenumber = vo.getDeletenumber();
        //字体大小
        mFoneSize = vo.getFontSize();

    }

    //初始化适配器
    private void initEvalueAdapter() {
        mGmEvaluePresenter.reqeustEvalueContentOne(mContext, 1, DataMessageVo.QUESTION, String.valueOf(mQuestionSqliteVo.getQuestion_id()));
        clearData();
        setGridLayoutManger(mContext, mRlvGmfourContent, 1);
//        mEvalueAdapter = new AnswerEvaluateAdapter(mContext, mArray);
        mEvalueAdapter = new GmEvaluateAdapter(mContext, mArray);
        mEvalueAdapter.doChangerColor(mGmReadColorManger);
        mRlvGmfourContent.setAdapter(mEvalueAdapter);
        mSmsvLayout.setmSmartScrollChangedListener(new WeiNestedScrollView.ISmartScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {
                Log.e(TAG, "onScrolledToBottom: ");
            }

            @Override
            public void onScrolledToTop() {
                Log.e(TAG, "onScrolledToTop: ");
            }
        });


    }

    private void initUtils() {
        //颜色
        mGmReadColorManger = GmReadColorManger.get_Instance(mContext);
        //选则项按钮
        mGmImgManageUtil = GmSelectImgManageUtil.get_Instance(mContext);
        //做题控制器
        mGmTextUtil = GmTextUtil.get_Instance(mContext);
        //初始化字体大小
        mGmFontSizeUtil = GmFontSizeUtil.get_Instance(mContext);
    }

    private void initView(View view) {
        mContext = getActivity();
        mTvGmTitleFirst = (TextView) view.findViewById(R.id.tv_gmTitle_first);
        mTvGmTitleFirst.setOnClickListener(this);
        mTvGmTitleAllnumber = (TextView) view.findViewById(R.id.tv_gmTitle_Allnumber);
        mTvGmTitleAllnumber.setOnClickListener(this);
        mTvGmTitleTextLast = (TextView) view.findViewById(R.id.tv_gmTitle_text_last);
        mTvGmTitleTextLast.setOnClickListener(this);
        mIvGmTitleMulu = (ImageView) view.findViewById(R.id.iv_gmTitle_mulu);
        mIvGmTitleMulu.setOnClickListener(this);
        mTvGmTitleTextStem = (TextView) view.findViewById(R.id.tv_gmTitle_text_stem);
        mTvGmTitleTextStem.setOnClickListener(this);
        mLlGmtitleSelectLayout = (LinearLayout) view.findViewById(R.id.ll_gmtitle_select_layout);
        mLlGmtitleSelectLayout.setOnClickListener(this);
        mTvGmoneTitleBank = (TextView) view.findViewById(R.id.tv_gmone_title_bank);
        mTvGmoneTitleBank.setOnClickListener(this);
        mIvGmOneA = (ImageView) view.findViewById(R.id.iv_gmOne_a);
        mTvGmOneAContent = (TextView) view.findViewById(R.id.tv_gmOne_a_content);
        mIvGmOneB = (ImageView) view.findViewById(R.id.iv_gmOne_b);
        mTvGmOneBContent = (TextView) view.findViewById(R.id.tv_gmOne_b_content);
        mIvGmOneC = (ImageView) view.findViewById(R.id.iv_gmOne_c);
        mTvGmOneCContent = (TextView) view.findViewById(R.id.tv_gmOne_c_content);
        mIvGmOneD = (ImageView) view.findViewById(R.id.iv_gmOne_d);
        mTvGmOneDContent = (TextView) view.findViewById(R.id.tv_gmOne_d_content);
        mIvGmOneE = (ImageView) view.findViewById(R.id.iv_gmOne_e);
        mTvGmOneEContent = (TextView) view.findViewById(R.id.Tv_gmOne_e_content);
        mLlGmOneELayout = (LinearLayout) view.findViewById(R.id.ll_gmOne_e_layout);
        mLlGmOneELayout.setOnClickListener(this);
        mBtnGmoneSuerAnswer = (Button) view.findViewById(R.id.btn_gmone_suer_answer);
        mBtnGmoneSuerAnswer.setOnClickListener(this);
        mVGmOneLine = (View) view.findViewById(R.id.v_gmOne_line);

        mTvGmOneAnswerTitle = (TextView) view.findViewById(R.id.tv_gmOne_answer_title);
        mTvGmOneAnswerTitle.setOnClickListener(this);
        mTvGmOneAnswerContent = (TextView) view.findViewById(R.id.tv_gmOne_answer_content);
        mTvGmOneAnswerContent.setOnClickListener(this);
        mLlGmoneAnswerLayout = (LinearLayout) view.findViewById(R.id.ll_gmone_answer_layout);
        mLlGmoneAnswerLayout.setOnClickListener(this);
        mLlGmOneSelectLayout = (LinearLayout) view.findViewById(R.id.ll_gmOne_select_layout);
        mBtnBBuy = (Button) view.findViewById(R.id.btn_b_buy);
        mBtnBBuy.setOnClickListener(this);
        //购买布局
        mLiBResolveBuy = (LinearLayout) view.findViewById(R.id.li_b_resolve_buy);
        mTvGmTwoJiexiTitle = (TextView) view.findViewById(R.id.tv_gmTwo_jiexi_title);
        mTvGmTwoJiexiTitle.setOnClickListener(this);
        mTvGmTwoJiexiContent = (TextView) view.findViewById(R.id.tv_gmTwo_jiexi_content);
        mTvGmTwoJiexiContent.setOnClickListener(this);
        mTvGmTwoDifficultyTitle = (TextView) view.findViewById(R.id.tv_gmTwo_difficulty_title);
        mTvGmTwoDifficultyTitle.setOnClickListener(this);
        mIvGmTwoStarOne = (ImageView) view.findViewById(R.id.iv_gmTwo_star_one);
        mIvGmTwoStarOne.setOnClickListener(this);
        mIvGmTwoStarTwo = (ImageView) view.findViewById(R.id.iv_gmTwo_star_two);
        mIvGmTwoStarTwo.setOnClickListener(this);
        mIvGmTwoStarThree = (ImageView) view.findViewById(R.id.iv_gmTwo_star_three);
        mIvGmTwoStarThree.setOnClickListener(this);
        mIvGmTwoStarFour = (ImageView) view.findViewById(R.id.iv_gmTwo_star_four);
        mIvGmTwoStarFour.setOnClickListener(this);
        mIvGmTwoStarFive = (ImageView) view.findViewById(R.id.iv_gmTwo_star_five);
        mIvGmTwoStarFive.setOnClickListener(this);
        mTvGmTwoRightTitle = (TextView) view.findViewById(R.id.tv_gmTwo_right_title);
        mTvGmTwoRightContent = (TextView) view.findViewById(R.id.tv_gmTwo_right_content);
        mTvGmTwoCentreTitle = (TextView) view.findViewById(R.id.tv_gmTwo_centre_title);
        mTvGmTwoCentreTitle.setOnClickListener(this);
        mFlowlayoutGmTwo = (FlowLayout) view.findViewById(R.id.flowlayout_gmTwo);
        mFlowlayoutGmTwo.setOnClickListener(this);
        mVGmTwoLineTwo = (View) view.findViewById(R.id.v_gmTwo_line_two);
        mVGmTwoLineTwo.setOnClickListener(this);
        //解析布局
        mLlGmTwoAnalysisLayout = (LinearLayout) view.findViewById(R.id.ll_gmTwo_analysis_layout);
        mTvGmThreeNoteTitle = (TextView) view.findViewById(R.id.tv_gmThree_note_title);
        mTvGmThreeNoteTitle.setOnClickListener(this);
        mTvGmThreeNote = (TextView) view.findViewById(R.id.tv_gmThree_note);
        mTvGmThreeNote.setOnClickListener(this);
        mVGmThreeLine = (View) view.findViewById(R.id.v_gmThree_line);
        mVGmThreeLine.setOnClickListener(this);
        //笔记布局
        mLlGmthreeNoteLayout = (LinearLayout) view.findViewById(R.id.ll_gmthree_note_layout);
        mTvGmfourEvaluateTitle = (TextView) view.findViewById(R.id.tv_gmfour_evaluate_title);
        mTvGmfourEvaluateTitle.setOnClickListener(this);
        mTvGmfourNuber = (TextView) view.findViewById(R.id.tv_gmfour_nuber);
        mIvGmfourEvaluate = (ImageView) view.findViewById(R.id.iv_gmfour_evaluate);
        mIvGmfourEvaluate.setOnClickListener(this);
        mRlvGmfourContent = (RecyclerView) view.findViewById(R.id.rlv_gmfour_content);
        mRlGmfourLayout = (RelativeLayout) view.findViewById(R.id.rl_gmfour_layout);
        mRlGmfourLayout.setOnClickListener(this);
        mLlGmfourMoreData = (LinearLayout) view.findViewById(R.id.ll_gmfour_more_data);
        //评价解面
        mLlFourEvaluateLayout = (LinearLayout) view.findViewById(R.id.ll_four_evaluate_layout);
        mSmsvLayout = (WeiNestedScrollView) view.findViewById(R.id.smsv_layout);
        mIvNetEmptyContent = (ImageView) view.findViewById(R.id.iv_net_empty_content);
        mIvNetEmptyContent.setOnClickListener(this);
        mLlGmoneALayout = (LinearLayout) view.findViewById(R.id.ll_gmone_a_layout);
        mLlGmoneALayout.setOnClickListener(this);
        mLlGmoneBLayout = (LinearLayout) view.findViewById(R.id.ll_gmone_b_layout);
        mLlGmoneBLayout.setOnClickListener(this);
        mLlGmoneCLayout = (LinearLayout) view.findViewById(R.id.ll_gmone_c_layout);
        mLlGmoneCLayout.setOnClickListener(this);
        mLlGmoneDLayout = (LinearLayout) view.findViewById(R.id.ll_gmone_d_layout);
        mLlGmoneDLayout.setOnClickListener(this);
        mViewGmtwoLine = (View) view.findViewById(R.id.view_gmtwo_line);
        mViewGmtwoLine.setOnClickListener(this);
        mGmRoolLayout = (LinearLayout) view.findViewById(R.id.gm_rool_layout);
        mGmRoolLayout.setOnClickListener(this);
        mVGmtwoLineThree = (View) view.findViewById(R.id.v_gmtwo_line_three);
        mVGmtwoLineThree.setOnClickListener(this);
    }

    private void initUserBuy() {
        mGmEvaluePresenter = new GmEvaluePresenter();
        mGmEvaluePresenter.initModelView(new GmEvalueModel(), this);
        UserInfomDbHelp help = UserInfomDbHelp.get_Instance(mContext);
        UserInfomSqliteVo userInfomVo = help.findUserInfomVo();
        DataMessageVo vo = DataMessageVo.get_Instance();
        String skillBuy = vo.getSkillBuy();
        String caseBuy = vo.getCaseBuy();
        String collorBuy = vo.getCollorBuy();
        if (mCouresid.equals("1")) {
            if (!StringUtil.isEmpty(userInfomVo.getSkillbook()) && userInfomVo.getSkillbook().equals(skillBuy)) {
                mUserBuy = true;
            }
        } else if (mCouresid.equals("2")) {
            if (!StringUtil.isEmpty(userInfomVo.getSkillbook()) && userInfomVo.getColligatebook().equals(collorBuy)) {
                mUserBuy = true;
            }

        } else if (mCouresid.equals("3")) {
            if (!StringUtil.isEmpty(userInfomVo.getSkillbook()) && userInfomVo.getCasebook().equals(caseBuy))
                mUserBuy = true;
        }
    }

    private void initShowView() {
        //评价布局
        mLlFourEvaluateLayout.setVisibility(View.GONE);
        //解析布局
        mLlGmTwoAnalysisLayout.setVisibility(View.GONE);
        //笔记布局
        mLlGmthreeNoteLayout.setVisibility(View.GONE);
        //阅读理解
        mLlGmtitleSelectLayout.setVisibility(View.GONE);


    }

    /**
     * 订阅者
     *
     * @return
     */
    @Override
    public Subsciber subsciber() {
        return new Subsciber();
    }

    /**
     * 具体观察者
     * 用于更新布局颜色
     */
    @Override
    public void refresh() {
        ShowSelectImg();
        setColor();
        setImgBg();
        setFontSize();
        if (mContext instanceof NewTextActivity) {
            NewTextActivity mActivity = (NewTextActivity) mContext;
            mActivity.changerColor(mGmReadColorManger);
        }
        initDoEvent();

        Log.e(TAG, "refresh: ========");
    }

    /**
     * 初始化数据
     */
    private void initDoEvent() {
        NewTextActivity mActivity;
        if (mContext instanceof NewTextActivity) {
            mActivity = (NewTextActivity) mContext;
        } else {
            return;
        }
        DoBankSqliteVo vo = mActivity.queryUserData(mQuestionSqliteVo.getQuestion_id());
        if (vo == null) return;
        if (vo.getIsDo() == 0) return;
        if (vo.getQuestiontype() == 2) {
            if (vo.getSelectA() == 1) {
                doRadioItemBgEvent(true, false, false, false, false);
                doRightErrorBg(mSelectA, 2);
            } else if (vo.getSelectB() == 1) {
                doRadioItemBgEvent(false, true, false, false, false);
                doRightErrorBg(mSelectB, 2);
            } else if (vo.getSelectC() == 1) {
                doRadioItemBgEvent(false, false, true, false, false);
                doRightErrorBg(mSelectC, 2);
            } else if (vo.getSelectD() == 1) {
                doRadioItemBgEvent(false, false, false, true, false);
                doRightErrorBg(mSelectD, 2);
            }
            setSelectABCDEClick(false);
            doRadioEventView();
        } else if (vo.getQuestiontype() == 3) {
            if (vo.getSelectA() == 1) {
                mSelectMorItemA = mSelectA;
            }
            if (vo.getSelectB() == 1) {
                mSelectMorItemB = mSelectB;
            }
            if (vo.getSelectC() == 1) {
                mSelectMorItemC = mSelectC;
            }
            if (vo.getSelectD() == 1) {
                mSelectMorItemD = mSelectD;
            }
            if (vo.getSelectE() == 1) {
                mSelectMorItemE = mSelectE;
            }
            mDuoXunaSure = true;
            mBtnGmoneSuerAnswer.setVisibility(View.GONE);
            setSelectABCDEClick(false);
            doSelectMoreEvent();
            doRadioEventView();
        }

    }

    private void setFontSize() {
        mTvGmoneTitleBank.setTextSize(mGmFontSizeUtil.getFontSize());
        mTvGmOneAContent.setTextSize(mGmFontSizeUtil.getFontSize());
        mTvGmOneBContent.setTextSize(mGmFontSizeUtil.getFontSize());
        mTvGmOneCContent.setTextSize(mGmFontSizeUtil.getFontSize());
        mTvGmOneDContent.setTextSize(mGmFontSizeUtil.getFontSize());
        mTvGmOneEContent.setTextSize(mGmFontSizeUtil.getFontSize());
        mBtnGmoneSuerAnswer.setTextSize(mGmFontSizeUtil.getFontSize());
        mTvGmOneAnswerTitle.setTextSize(mGmFontSizeUtil.getFontSize());
        mTvGmOneAnswerContent.setTextSize(mGmFontSizeUtil.getFontSize());
        mTvGmTwoJiexiTitle.setTextSize(mGmFontSizeUtil.getFontSize());
        mTvGmTwoJiexiContent.setTextSize(mGmFontSizeUtil.getFontSize());

    }

    //根据题型设置选项图标
    private void ShowSelectImg() {
        switch (mQuestionSqliteVo.getQuestiontype()) {
            case 2://单选
                mGmImgManageUtil.setStatus(GmSelectImgManageUtil.Stauts.DayRadio);
                break;
            case 3://多选
                mGmImgManageUtil.setStatus(GmSelectImgManageUtil.Stauts.Daychoice);
                break;
        }
    }

    //设置图片背景
    private void setImgBg() {
        mIvGmOneA.setImageDrawable(mGmImgManageUtil.getA());
        mIvGmOneB.setImageDrawable(mGmImgManageUtil.getB());
        mIvGmOneC.setImageDrawable(mGmImgManageUtil.getC());
        mIvGmOneD.setImageDrawable(mGmImgManageUtil.getD());
        mIvGmOneE.setImageDrawable(mGmImgManageUtil.getE());
        mIvGmfourEvaluate.setImageDrawable(mGmImgManageUtil.getP());
    }

    private void setColor() {
        mGmRoolLayout.setBackgroundColor(mGmReadColorManger.getmLayoutBgColor());

        //选择布局
        mLlGmOneSelectLayout.setBackgroundColor(mGmReadColorManger.getmLayoutBgColor());
        //购买布局
        mLiBResolveBuy.setBackgroundColor(mGmReadColorManger.getmLayoutBgColor());
        //解析布局
        mLlGmTwoAnalysisLayout.setBackgroundColor(mGmReadColorManger.getmLayoutBgColor());
        //笔记布局
        mLlGmthreeNoteLayout.setBackgroundColor(mGmReadColorManger.getmLayoutBgColor());
        //评论布局
        mLlFourEvaluateLayout.setBackgroundColor(mGmReadColorManger.getmLayoutBgColor());

        mTvGmoneTitleBank.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmOneAContent.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmOneBContent.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmOneCContent.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmOneDContent.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmOneEContent.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmOneAnswerContent.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmOneAnswerTitle.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmTwoJiexiTitle.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmTwoJiexiContent.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmTwoDifficultyTitle.setTextColor(mGmReadColorManger.getmTextFuColor());
        mTvGmTwoRightTitle.setTextColor(mGmReadColorManger.getmTextFuColor());
        mTvGmTwoRightContent.setTextColor(mGmReadColorManger.getmTextRedColor());
        mVGmTwoLineTwo.setBackgroundColor(mGmReadColorManger.getmCutLineColor());
        mViewGmtwoLine.setBackgroundColor(mGmReadColorManger.getmCutLineColor());
        mVGmThreeLine.setBackgroundColor(mGmReadColorManger.getmCutLineColor());
        mTvGmfourEvaluateTitle.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mTvGmfourNuber.setTextColor(mGmReadColorManger.getmTextTitleColor());
        mVGmtwoLineThree.setBackgroundColor(mGmReadColorManger.getmCutLineColor());
        mVGmOneLine.setBackgroundColor(mGmReadColorManger.getmCutLineColor());
    }

    private void readDataBindView() {
        String question = mQuestionSqliteVo.getQuestionStr();
        mQuestionType = mQuestionSqliteVo.getQuestiontype();
        //题干信息
        mTvGmoneTitleBank.setText(getString(question));
        mTvGmOneAContent.setText(mQuestionSqliteVo.getOption_a());
        mTvGmOneBContent.setText(mQuestionSqliteVo.getOption_b());
        mTvGmOneCContent.setText(mQuestionSqliteVo.getOption_c());
        mTvGmOneDContent.setText(mQuestionSqliteVo.getOption_d());
        mTvGmOneEContent.setText(mQuestionSqliteVo.getOption_e());
        mTvGmOneAnswerContent.setText(mQuestionSqliteVo.getChoice_answer());
        mTvGmTwoJiexiContent.setText(mQuestionSqliteVo.getExplainedStr());
        setStarNumber(mQuestionSqliteVo.getDifficulty());
        mTvGmTwoRightContent.setText(String.valueOf(mQuestionSqliteVo.getRight_rate()).concat("%"));


    }

    private void bindKaoDian() {
        mFlowlayoutGmTwo.removeAllViews();
      /*  if (teachers != null && !teachers.isEmpty()) {
            for (int i = 0; i < teachers.size(); i++) {
                TeachersBean teacherVo = teachers.get(i);
                View itemvo = mInflater.inflate(R.layout.item_home_net_teacher, holder.mFlTeacher, false);
                ImageView imagHead = (ImageView) itemvo.findViewById(R.id.iv_home_teacher);
                if (!StringUtil.isEmpty(teacherVo.getHeadimg())) {
                    MyAppliction.getInstance().displayImages(imagHead, teacherVo.getHeadimg(), false);
                } else {
                    imagHead.setImageResource(R.drawable.ic_m_hear);
                }
                TextView tvName = (TextView) itemvo.findViewById(R.id.tv_home_teacherName);
                tvName.setText(teacherVo.getName());
                mFlowlayoutGmTwo.addView(itemvo);
            }
        }*/
    }

    /**
     * 富文本
     *
     * @param textbar
     * @return
     */
    private SpannableString getString(String textbar) {
        String img = "图片";
        String concat = img.concat(textbar);
        SpannableString ss = new SpannableString(concat);
        Drawable d = getQuestionType();
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(d);
        ss.setSpan(span, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return ss;
    }

    //获取题干信息
    private Drawable getQuestionType() {
        Drawable type = null;
        boolean isShow = false;
        switch (mQuestionSqliteVo.getQuestiontype()) {
            case 2:
                type = mGmImgManageUtil.getDrawable(R.drawable.qb_danxuan);
                isShow = false;
                mLlGmOneELayout.setVisibility(View.GONE);
                break;
            case 3:
                type = mGmImgManageUtil.getDrawable(R.drawable.qb_duoxuan);
                isShow = true;
                mLlGmOneELayout.setVisibility(View.VISIBLE);
                break;
            case 4:
                type = mGmImgManageUtil.getDrawable(R.drawable.qb_jianda);
                isShow = false;
                break;
        }
        mBtnGmoneSuerAnswer.setVisibility(isShow ? View.VISIBLE : View.GONE);
        return type;
    }

    private void registerOberovce() {
        //初始化颜色
        mGmReadColorManger.setGmBgColor(GmReadColorManger.DAYTIME);
        //初始化用字体
        if (StringUtil.isEmpty(mFoneSize)) {
            mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font16);
        } else if (mFoneSize.equals(DataMessageVo.FONT_ONE)) {
            mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font14);
        } else if (mFoneSize.equals(DataMessageVo.FONT_TWO)) {
            mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font16);
        } else if (mFoneSize.equals(DataMessageVo.FONT_THREE)) {
            mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font18);
        } else if (mFoneSize.equals(DataMessageVo.FONT_FOUR)) {
            mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font20);
        } else if (mFoneSize.equals(DataMessageVo.FONT_FIVE)) {
            mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font24);
        }
        //初始化模式
        if (StringUtil.isEmpty(mShowDayOrNight)) {
            mGmReadColorManger.setGmBgColor(GmReadColorManger.DAYTIME);
        } else if (mShowDayOrNight.equals(DataMessageVo.PATTERN_ONE)) {
            mGmReadColorManger.setGmBgColor(GmReadColorManger.DAYTIME);
        } else if (mShowDayOrNight.equals(DataMessageVo.PATTERN_TWO)) {
            mGmReadColorManger.setGmBgColor(GmReadColorManger.NIGHT);
        } else if (mShowDayOrNight.equals(DataMessageVo.PATTERN_THREE)) {
            mGmReadColorManger.setGmBgColor(GmReadColorManger.EYE);
        }
        //初始化选项图片
        if (getActivity() instanceof NewTextActivity) {
            mNewTextActivity = (NewTextActivity) getActivity();
        }
        if (getActivity() instanceof OrderTestActivity) {
            mOrderTestActivity = (OrderTestActivity) getActivity();
        }
        //初始化显示按钮

        mSubsciber = new GmReadFragment().subsciber();
        mSubsciber.attch(this);
        mSubsciber.notifyChanger();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gmone_suer_answer:
                mDuoXunaSure = true;
                mBtnGmoneSuerAnswer.setVisibility(View.GONE);
                setSelectABCDEClick(false);
                doSelectMoreEvent();
                doRadioEventView();
                doSaveMoreEvent();
                break;
            case R.id.btn_b_buy:

                break;
            case R.id.ll_gmone_a_layout://a
                if (mQuestionType == 2) {//处理单选
                    doRadioItemBgEvent(true, false, false, false, false);
                    doRadioEventA();
                    doRightErrorBg(mSelectA, 2);
                    doRadioEventView();
                } else if (mQuestionType == 3) {//多选
                    mSelectMorItemA = mSelectA;
                    if (mSelectMorisClickA) {
                        mSelectMorisClickA = false;
                        mSelectMorItemA = null;
                    } else {
                        mSelectMorisClickA = true;
                    }
                    setSureKeyBg();
                    setSelectMoreItemBG(mSelectA, mSelectMorisClickA);
                } else if (mQuestionType == 4) {//简答
                }

                break;
            case R.id.ll_gmone_b_layout://b
                if (mQuestionType == 2) {//处理单选
                    doRadioItemBgEvent(false, true, false, false, false);
                    doRadioEventB();
                    doRightErrorBg(mSelectB, 2);
                    doRadioEventView();
                } else if (mQuestionType == 3) {//多选
                    mSelectMorItemB = mSelectB;
                    if (mSelectMorisClickB) {
                        mSelectMorisClickB = false;
                        mSelectMorItemB = null;
                    } else {
                        mSelectMorisClickB = true;
                    }
                    setSureKeyBg();
                    setSelectMoreItemBG(mSelectB, mSelectMorisClickB);

                } else if (mQuestionType == 4) {//简答
                }

                break;
            case R.id.ll_gmone_c_layout://c
                if (mQuestionType == 2) {//处理单选
                    doRadioItemBgEvent(false, false, true, false, false);
                    doRadioEventC();
                    doRightErrorBg(mSelectC, 2);
                    doRadioEventView();
                } else if (mQuestionType == 3) {//多选
                    mSelectMorItemC = mSelectC;
                    if (mSelectMorisClickC) {
                        mSelectMorisClickC = false;
                        mSelectMorItemC = null;
                    } else {
                        mSelectMorisClickC = true;
                    }
                    setSureKeyBg();
                    setSelectMoreItemBG(mSelectC, mSelectMorisClickC);
                } else if (mQuestionType == 4) {//简答
                }
                break;
            case R.id.ll_gmone_d_layout://d
                if (mQuestionType == 2) {//处理单选
                    doRadioItemBgEvent(false, false, false, true, false);
                    doRadioEventD();
                    doRightErrorBg(mSelectD, 2);
                    doRadioEventView();
                } else if (mQuestionType == 3) {//多选
                    mSelectMorItemD = mSelectD;
                    if (mSelectMorisClickD) {
                        mSelectMorisClickD = false;
                        mSelectMorItemD = null;
                    } else {
                        mSelectMorisClickD = true;
                    }
                    setSureKeyBg();
                    setSelectMoreItemBG(mSelectD, mSelectMorisClickD);

                } else if (mQuestionType == 4) {//简答
                }
                break;
            case R.id.ll_gmOne_e_layout://e
                if (mQuestionType == 2) {//处理单选
                    doRadioItemBgEvent(false, false, false, false, true);
                    doRadioEventE();
                    doRightErrorBg(mSelectE, 2);
                    doRadioEventView();
                } else if (mQuestionType == 3) {//多选
                    mSelectMorItemE = mSelectE;
                    if (mSelectMorisClickE) {
                        mSelectMorisClickE = false;
                        mSelectMorItemE = null;
                    } else {
                        mSelectMorisClickE = true;
                    }
                    setSureKeyBg();
                    setSelectMoreItemBG(mSelectE, mSelectMorisClickE);
                } else if (mQuestionType == 4) {//简答
                }

                break;
        }
    }

    private void doSaveMoreEvent() {
        //0为未选，1为正确 ，2 为漏选 ，3为错误
        DoBankSqliteVo vo = new DoBankSqliteVo();
        setSelectABCDEClick(false);
        String str = mQuestionSqliteVo.getChoice_answer();
        List<String> list = mGmTextUtil.getAnswerKeyList(str);
        if (list == null || list.isEmpty()) return;
        //用户选择结果
        ArrayList<String> listKeys = new ArrayList<>();
        if (listKeys == null || list.isEmpty()) return;
        addSelectList(mSelectMorItemA, listKeys);
        addSelectList(mSelectMorItemB, listKeys);
        addSelectList(mSelectMorItemC, listKeys);
        addSelectList(mSelectMorItemD, listKeys);
        addSelectList(mSelectMorItemE, listKeys);
        //判断用户是否正确
        if (listKeys.size() > list.size()) {
            vo.setIsright(3);//错误
        } else if (listKeys.size() == list.size()) {
            if (list.containsAll(listKeys)) {
                vo.setIsright(1); //正确
            } else {
                vo.setIsright(3);//错误
            }
        } else if (listKeys.size() < list.size()) {
            if (list.containsAll(listKeys)) {
                vo.setIsright(2);
            } else {
                vo.setIsright(3);
            }

        }
        //用户选中 1为做过，0 为没做
        vo.setQuestion_id(mQuestionSqliteVo.getQuestion_id());
        vo.setIsDo(1);
        //选中1 为选中，0 为不选中
        vo.setSelectA(StringUtil.isEmpty(mSelectMorItemA) ? 0 : 1);
        vo.setSelectB(StringUtil.isEmpty(mSelectMorItemB) ? 0 : 1);
        vo.setSelectC(StringUtil.isEmpty(mSelectMorItemC) ? 0 : 1);
        vo.setSelectD(StringUtil.isEmpty(mSelectMorItemD) ? 0 : 1);
        vo.setSelectE(StringUtil.isEmpty(mSelectMorItemE) ? 0 : 1);
        vo.setQuestiontype(mQuestionSqliteVo.getQuestiontype());
        doSaveEvent(vo);
    }

    private void addSelectList(String select, ArrayList<String> list) {
        if (!StringUtil.isEmpty(select))
            list.add(select);
    }

    //设置布局
    public void showGmSetting() {
        mPopSetting = new CommonPopupWindow(mContext, R.layout.gm_pop_setting, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            private DiscreteSlider mDiscreteSlider;
            private ArrayList<CheckBox> mCheckBoxList;
            private RadioGroup mRgContentDelete;
            private RadioButton mRdbGmpopNo;
            private RadioButton mRdbGmpopFive;
            private RadioButton mRdbGmpopThree;
            private RadioButton mRdbGmpopOne;
            private CheckBox mSwtGmselectNext;
            private CheckBox mRdbGmsettingEye;
            private CheckBox mRdbGmsettingNight;
            private CheckBox mRdbGmsettingDay;

            @Override
            protected void initView() {
                View view = getContentView();
                //初始化配置
                initSetting();
                mRgContentDelete = view.findViewById(R.id.rg_content_delete);
                mRdbGmpopNo = view.findViewById(R.id.rdb_gmpop_no);
                mRdbGmpopFive = view.findViewById(R.id.rdb_gmpop_five);
                mRdbGmpopThree = view.findViewById(R.id.rdb_gmpop_three);
                mRdbGmpopOne = view.findViewById(R.id.rdb_gmpop_one);
                mSwtGmselectNext = view.findViewById(R.id.swt_gmselect_next);
                mRdbGmsettingEye = view.findViewById(R.id.rdb_gmsetting_eye);
                mDiscreteSlider = (DiscreteSlider) view.findViewById(R.id.discrete_slider);
                mRdbGmsettingNight = view.findViewById(R.id.rdb_gmsetting_night);
                mRdbGmsettingDay = view.findViewById(R.id.rdb_gmsetting_day);
                mCheckBoxList = new ArrayList<>();
                mCheckBoxList.add(mRdbGmsettingDay);
                mCheckBoxList.add(mRdbGmsettingNight);
                mCheckBoxList.add(mRdbGmsettingEye);
                if (!StringUtil.isEmpty(mShowDayOrNight)) {//模式
                    if (mShowDayOrNight.equals(DataMessageVo.PATTERN_ONE)) {//白天
                        mRdbGmsettingDay.setChecked(true);
                    } else if (mShowDayOrNight.equals(DataMessageVo.PATTERN_TWO)) {//夜间
                        mRdbGmsettingNight.setChecked(true);
                    } else if (mShowDayOrNight.equals(DataMessageVo.PATTERN_THREE)) {//护眼
                        mRdbGmsettingEye.setChecked(true);
                    }
                } else {
                    mShowDayOrNight = DataMessageVo.PATTERN_ONE;
                    mRdbGmsettingDay.setChecked(true);
                    mUserDbHelp.upDataDayOrNightOrEye(DataMessageVo.PATTERN_ONE);
                }
                if (!StringUtil.isEmpty(mDeletenumber)) {
                    if (mDeletenumber.equals(DataMessageVo.PATTERN_ONE)) {
                        mRdbGmpopOne.setChecked(true);
                    } else if (mDeletenumber.equals(DataMessageVo.PATTERN_TWO)) {
                        mRdbGmpopThree.setChecked(true);
                    } else if (mDeletenumber.equals(DataMessageVo.PATTERN_THREE)) {
                        mRdbGmpopFive.setChecked(true);
                    } else if (mDeletenumber.equals(DataMessageVo.PATTERN_FOUR)) {
                        mRdbGmpopNo.setChecked(true);
                    }
                }

                if (!StringUtil.isEmpty(mFoneSize)) {
                    if (mFoneSize.equals(DataMessageVo.FONT_ONE)) {
                        mDiscreteSlider.setPosition(0);
                    } else if (mFoneSize.equals(DataMessageVo.FONT_TWO)) {
                        mDiscreteSlider.setPosition(1);
                    } else if (mFoneSize.equals(DataMessageVo.FONT_THREE)) {
                        mDiscreteSlider.setPosition(2);
                    } else if (mFoneSize.equals(DataMessageVo.FONT_FOUR)) {
                        mDiscreteSlider.setPosition(3);
                    } else if (mFoneSize.equals(DataMessageVo.FONT_FIVE)) {
                        mDiscreteSlider.setPosition(4);
                    }
                }
                if (!StringUtil.isEmpty(mUserNextGo) && mUserNextGo.equals("go")) {
                    mSwtGmselectNext.setChecked(true);
                } else {
                    mSwtGmselectNext.setChecked(false);
                }
            }

            @Override

            protected void initEvent() {
                setCheckChanger(mCheckBoxList, mRdbGmsettingDay, 1);
                setCheckChanger(mCheckBoxList, mRdbGmsettingNight, 2);
                setCheckChanger(mCheckBoxList, mRdbGmsettingEye, 3);
                setCheckChanger(mCheckBoxList, mSwtGmselectNext, 4);
                setRadioButtonChanger(mRdbGmpopNo, 1);
                setRadioButtonChanger(mRdbGmpopThree, 2);
                setRadioButtonChanger(mRdbGmpopFive, 3);
                setRadioButtonChanger(mRdbGmpopNo, 4);
                setDiscreteSlderListener(mDiscreteSlider);
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mGmTextUtil.setBackgroundAlpha(1.0f, mContext);
                    }
                });
            }
        };
        mPopSetting.showAtLocation(mGmRoolLayout, Gravity.BOTTOM, 0, 0);
        mGmTextUtil.setBackgroundAlpha(0.5f, mContext);
    }

    private void setDiscreteSlderListener(DiscreteSlider mDiscreteSlider) {
        mDiscreteSlider.setOnDiscreteSliderChangeListener(new DiscreteSlider.OnDiscreteSliderChangeListener() {
            @Override
            public void onPositionChanged(int position) {
                String number = String.valueOf(position + 1);
                mUserDbHelp.upDataFoneSize(number);
                if (number.equals(DataMessageVo.FONT_ONE)) {
                    mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font14);
                } else if (number.equals(DataMessageVo.FONT_TWO)) {
                    mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font16);
                } else if (number.equals(DataMessageVo.FONT_THREE)) {
                    mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font18);
                } else if (number.equals(DataMessageVo.FONT_FOUR)) {
                    mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font20);
                } else if (number.equals(DataMessageVo.FONT_FIVE)) {
                    mGmFontSizeUtil.status(GmFontSizeUtil.fontStatus.font24);
                }
                mSubsciber.notifyChanger();

            }
        });

    }

    /**
     * 1为白天，2为夜间，3为护眼 4，一次删除，5为三次，6为五次删除，7不删除
     *
     * @param mCheckBoxList
     * @param checkBox
     * @param id
     */
    private void setCheckChanger(final ArrayList<CheckBox> mCheckBoxList, final CheckBox checkBox, final int id) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (id != 4) {
                    for (int i = 0; i < mCheckBoxList.size(); i++) {
                        CheckBox box = mCheckBoxList.get(i);
                        box.setChecked(false);
                    }
                    if (!checkBox.isPressed()) return;
                }
                switch (id) {
                    case 1://白天
                        if (isChecked) {
                            mShowDayOrNight = DataMessageVo.PATTERN_ONE;
                            checkBox.setChecked(true);
                            mUserDbHelp.upDataDayOrNightOrEye(DataMessageVo.PATTERN_ONE);
                            mGmReadColorManger.setGmBgColor(GmReadColorManger.DAYTIME);
                            mSubsciber.notifyChanger();
                        }
                        break;
                    case 2://夜间
                        if (isChecked) {
                            mShowDayOrNight = DataMessageVo.PATTERN_TWO;
                            checkBox.setChecked(true);
                            mUserDbHelp.upDataDayOrNightOrEye(DataMessageVo.PATTERN_TWO);
                            mGmReadColorManger.setGmBgColor(GmReadColorManger.NIGHT);
                            mSubsciber.notifyChanger();
                        } else {
                            setDay();
                        }
                        break;
                    case 3://护眼
                        if (isChecked) {
                            mShowDayOrNight = DataMessageVo.PATTERN_THREE;
                            checkBox.setChecked(true);
                            mUserDbHelp.upDataDayOrNightOrEye(DataMessageVo.PATTERN_THREE);
                            mGmReadColorManger.setGmBgColor(GmReadColorManger.EYE);
                            mSubsciber.notifyChanger();
                        } else {
                            setDay();
                        }
                        break;
                    case 4://自动下一题
                        if (isChecked) {
                            mUserDbHelp.upDataUserNext();
                        } else {
                            mUserDbHelp.delectUserNext();
                        }
                        break;
                }

            }

            public void setDay() {
                mShowDayOrNight = DataMessageVo.PATTERN_ONE;
                mUserDbHelp.upDataDayOrNightOrEye(DataMessageVo.PATTERN_ONE);
                mGmReadColorManger.setGmBgColor(GmReadColorManger.DAYTIME);
                mSubsciber.notifyChanger();
            }
        });
    }

    /**
     * 1为白天，2为夜间，3为护眼 4，一次删除
     *
     * @param checkBox
     * @param id
     */
    private void setRadioButtonChanger(RadioButton checkBox, final int id) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (id) {
                    case 1://一次
                        if (isChecked) {
                            mUserDbHelp.upDataErrorNumber(DataMessageVo.PATTERN_ONE);
                        }
                        break;
                    case 2://三次
                        if (isChecked) {
                            mUserDbHelp.upDataErrorNumber(DataMessageVo.PATTERN_TWO);
                        }
                        break;
                    case 3://五次
                        if (isChecked) {
                            mUserDbHelp.upDataErrorNumber(DataMessageVo.PATTERN_THREE);
                        }
                        break;
                    case 4://不删除
                        if (isChecked) {
                            mUserDbHelp.upDataErrorNumber(DataMessageVo.PATTERN_FOUR);
                        }
                        break;
                }

            }
        });
    }

    /**
     * 处理显示多选择
     */
    private void doSelectMoreEvent() {
        String keywordStr = mQuestionSqliteVo.getChoice_answer();
        List<String> keyList = mGmTextUtil.getAnswerKeyList(keywordStr);
        for (String s : keyList) {
            if (s.equalsIgnoreCase(mSelectA)) {
                mGmTextUtil.setImgMiss(mIvGmOneA);
                mGmTextUtil.setTvMiss(mTvGmOneAContent);
            } else if (s.equalsIgnoreCase(mSelectB)) {
                mGmTextUtil.setImgMiss(mIvGmOneB);
                mGmTextUtil.setTvMiss(mTvGmOneBContent);
            } else if (s.equalsIgnoreCase(mSelectC)) {
                mGmTextUtil.setImgMiss(mIvGmOneC);
                mGmTextUtil.setTvMiss(mTvGmOneCContent);
            } else if (s.equalsIgnoreCase(mSelectD)) {
                mGmTextUtil.setImgMiss(mIvGmOneD);
                mGmTextUtil.setTvMiss(mTvGmOneDContent);
            } else if (s.equalsIgnoreCase(mSelectE)) {
                mGmTextUtil.setImgMiss(mIvGmOneE);
                mGmTextUtil.setTvMiss(mTvGmOneEContent);
            }
        }
        //用户选中结果
        if (!StringUtil.isEmpty(mSelectMorItemA)) {
            boolean isRight = mGmTextUtil.keyIsRight(keyList, mSelectMorItemA);
            mGmTextUtil.setImgRight(mIvGmOneA, isRight);
            mGmTextUtil.setTvRight(mTvGmOneAContent, isRight);
        }
        if (!StringUtil.isEmpty(mSelectMorItemB)) {
            boolean isRight = mGmTextUtil.keyIsRight(keyList, mSelectMorItemB);
            mGmTextUtil.setImgRight(mIvGmOneB, isRight);
            mGmTextUtil.setTvRight(mTvGmOneBContent, isRight);
        }
        if (!StringUtil.isEmpty(mSelectMorItemC)) {
            boolean isRight = mGmTextUtil.keyIsRight(keyList, mSelectMorItemC);
            mGmTextUtil.setImgRight(mIvGmOneC, isRight);
            mGmTextUtil.setTvRight(mTvGmOneCContent, isRight);
        }
        if (!StringUtil.isEmpty(mSelectMorItemD)) {
            boolean isRight = mGmTextUtil.keyIsRight(keyList, mSelectMorItemD);
            mGmTextUtil.setImgRight(mIvGmOneD, isRight);
            mGmTextUtil.setTvRight(mTvGmOneDContent, isRight);
        }
        if (!StringUtil.isEmpty(mSelectMorItemE)) {
            boolean isRight = mGmTextUtil.keyIsRight(keyList, mSelectMorItemE);
            mGmTextUtil.setImgRight(mIvGmOneE, isRight);
            mGmTextUtil.setTvRight(mTvGmOneEContent, isRight);
        }

    }


    /**
     * 处理选择后得效果
     */
    private void doRadioEventView() {
        //判断用户是否购买
        doShowNoBuy(mUserBuy);
    }

    private void doShowNoBuy(boolean buy) {
        //笔记
        mLlGmthreeNoteLayout.setVisibility(buy ? View.VISIBLE : View.GONE);
        //解析
        mLlGmTwoAnalysisLayout.setVisibility(buy ? View.VISIBLE : View.GONE);
        //评价
        mLlFourEvaluateLayout.setVisibility(buy ? View.VISIBLE : View.GONE);
        //购买
        mLiBResolveBuy.setVisibility(buy ? View.GONE : View.VISIBLE);
        //答案
        mLlGmoneAnswerLayout.setVisibility(View.VISIBLE);
    }


    private void doRadioEventE() {
        saveDb(mSelectE, false, false, false, false, true);
    }

    private void doRadioEventD() {
        saveDb(mSelectD, false, false, false, true, false);
    }

    private void doRadioEventC() {
        saveDb(mSelectC, false, false, true, false, false);
    }

    private void doRadioEventB() {
        saveDb(mSelectB, false, true, false, false, false);
    }

    private void doRadioEventA() {
        saveDb(mSelectA, true, false, false, false, false);
    }

    /**
     * 保存到数据库
     *
     * @param selectItem 用户选项
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     */
    private void saveDb(String selectItem, boolean a, boolean b, boolean c, boolean d, boolean e) {
        setSelectABCDEClick(false);
        //0为未选，1为正确 ，2 为漏选 ，3为错误
        String str = mQuestionSqliteVo.getChoice_answer();
        DoBankSqliteVo vo = new DoBankSqliteVo();
        List<String> list = mGmTextUtil.getAnswerKeyList(str);
        if (list == null || list.isEmpty()) return;
        int right = 0;
        if (mGmTextUtil.keyIsRight(list, selectItem)) {//正确
            right = 1;
        } else {//错误
            right = 3;
        }
        //用户选中 1为做过，0 为没做
        vo.setQuestion_id(mQuestionSqliteVo.getQuestion_id());
        vo.setIsDo(1);
        //选中1 为选中，0 为不选中
        vo.setSelectA(a ? 1 : 0);
        vo.setSelectB(b ? 1 : 0);
        vo.setSelectC(c ? 1 : 0);
        vo.setSelectD(d ? 1 : 0);
        vo.setSelectE(e ? 1 : 0);
        vo.setQuestiontype(mQuestionSqliteVo.getQuestiontype());
        //是否作对1 为作对，0为错
        vo.setIsright(right);
        doSaveEvent(vo);
    }

    /**
     * 保存用户做题记录
     *
     * @param vo
     */
    private void doSaveEvent(DoBankSqliteVo vo) {
        if (mNewTextActivity != null) {
            mNewTextActivity.saveUserDoLog(vo);
        }
        if (mOrderTestActivity != null) {
            mOrderTestActivity.saveUserDoLog(vo);
        }
    }

    /**
     * 处理选中后得对错
     *
     * @param a    选项
     * @param type 类型 2 单选其它是多选
     */
    private void doRightErrorBg(String a, int type) {
        //单选模式
        if (type == 2) {
            mGmImgManageUtil.setRightStauts(GmSelectImgManageUtil.RigthStatus.RadioRight);
        } else {
            mGmImgManageUtil.setRightStauts(GmSelectImgManageUtil.RigthStatus.ChoiceRight);
        }
        TextView tv = getSelectTextView(a);
        ImageView imageView = getSelectItemImager(a);
        String str = mQuestionSqliteVo.getChoice_answer();
        List<String> list = mGmTextUtil.getAnswerKeyList(str);
        if (tv != null)
            tv.setTextColor(mGmTextUtil.keyIsRight(list, a) ? mGmReadColorManger.getmTextRightColor() : mGmReadColorManger.getmTextRedColor());
        if (imageView != null) {
            imageView.setImageDrawable(mGmTextUtil.keyIsRight(list, a) ? mGmImgManageUtil.getRight() : mGmImgManageUtil.getError());
        }
        setRightColorImg(list);
    }

    /**
     * 设置正确得显示结果
     *
     * @param list
     */
    private void setRightColorImg(List<String> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                String select = list.get(i);
                TextView tvone = getSelectTextView(select);
                ImageView imageViewone = getSelectItemImager(select);
                if (tvone != null) {
                    tvone.setTextColor(mGmReadColorManger.getmTextRightColor());
                }
                if (imageViewone != null) {
                    imageViewone.setImageDrawable(mGmImgManageUtil.getRight());
                }
            }
        }
    }

    /**
     * 处理单选
     */
    private void doRadioItemBgEvent(boolean a, boolean b, boolean c, boolean d, boolean e) {
        if (mGmImgManageUtil == null) {
            mGmImgManageUtil = GmSelectImgManageUtil.get_Instance(mContext);
        }
        setImgBg(mIvGmOneA, a, mGmImgManageUtil.getDrawable(R.drawable.qbank_answer_icon_single_a_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_single_a_n));
        setImgBg(mIvGmOneB, b, mGmImgManageUtil.getDrawable(R.drawable.qbank_answer_icon_single_b_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_single_b_n));
        setImgBg(mIvGmOneC, c, mGmImgManageUtil.getDrawable(R.drawable.qbank_answer_icon_single_c_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_single_c_n));
        setImgBg(mIvGmOneD, d, mGmImgManageUtil.getDrawable(R.drawable.qbank_answer_icon_single_d_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_single_d_n));
        setImgBg(mIvGmOneE, e, mGmImgManageUtil.getDrawable(R.drawable.qbank_answer_icon_single_e_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_e_n));
    }

    /**
     * 设置多选确认按钮
     */
    private void setSureKeyBg() {
        if (mSelectMorisClickA || mSelectMorisClickB || mSelectMorisClickC || mSelectMorisClickD || mSelectMorisClickE) {
            mBtnGmoneSuerAnswer.setClickable(true);
            mDuoXunaSure = false;
            mBtnGmoneSuerAnswer.setTextColor(mGmTextUtil.getColorDrawable(R.color.white));
            mBtnGmoneSuerAnswer.setBackgroundResource(R.drawable.btn_b_sure_s);
        } else {
            mDuoXunaSure = true;
            mBtnGmoneSuerAnswer.setClickable(true);
            mBtnGmoneSuerAnswer.setTextColor(mGmTextUtil.getColorDrawable(R.color.text_fu_color));
            mBtnGmoneSuerAnswer.setBackgroundResource(R.drawable.bg_gm_text_sure_n);
        }
    }

    /**
     * 多选结果展示
     *
     * @param id 0a,1b,2c,3d,4e
     */
    private void setSelectMoreItemBG(String id, boolean isSelect) {
        if (mGmImgManageUtil == null) {
            mGmImgManageUtil = GmSelectImgManageUtil.get_Instance(mContext);
        }
        if (id.equalsIgnoreCase(mSelectA)) {
            setImgBg(mIvGmOneA, isSelect, mGmImgManageUtil.getDrawable(R.drawable.qbank_answe_icon_multiple_a_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_a_n));
        } else if (id.equalsIgnoreCase(mSelectB)) {
            setImgBg(mIvGmOneB, isSelect, mGmImgManageUtil.getDrawable(R.drawable.qbank_answe_icon_multiple_b_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_b_n));
        } else if (id.equalsIgnoreCase(mSelectC)) {
            setImgBg(mIvGmOneC, isSelect, mGmImgManageUtil.getDrawable(R.drawable.qbank_answe_icon_multiple_c_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_c_n));
        } else if (id.equalsIgnoreCase(mSelectD)) {
            setImgBg(mIvGmOneD, isSelect, mGmImgManageUtil.getDrawable(R.drawable.qbank_answe_icon_multiple_d_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_d_n));
        } else if (id.equalsIgnoreCase(mSelectE)) {
            setImgBg(mIvGmOneE, isSelect, mGmImgManageUtil.getDrawable(R.drawable.qbank_answe_icon_multiple_e_s), mGmImgManageUtil.getDrawable(R.drawable.ic_b_e_n));
        }

    }

    /**
     * 设置背景
     *
     * @param iv         控件
     * @param is         选中
     * @param selectid   选中图片
     * @param unselectid 未选中图片
     */
    private void setImgBg(ImageView iv, boolean is, Drawable selectid, Drawable unselectid) {
        if (iv == null)
            return;
        if (is) {//选中
            iv.setImageDrawable(selectid);
        } else {//未选中
            iv.setImageDrawable(unselectid);
        }
    }

    /**
     * 设置文体文字
     *
     * @param select
     * @return
     */
    private TextView getSelectTextView(String select) {
        if (select.equalsIgnoreCase(mSelectA)) {
            return mTvGmOneAContent;
        }
        if (select.equalsIgnoreCase(mSelectB)) {
            return mTvGmOneBContent;
        }
        if (select.equalsIgnoreCase(mSelectC)) {
            return mTvGmOneCContent;
        }
        if (select.equalsIgnoreCase(mSelectD)) {
            return mTvGmOneDContent;
        }
        if (select.equalsIgnoreCase(mSelectE)) {
            return mTvGmOneEContent;
        }
        return null;
    }

    /**
     * 设置正确图片
     *
     * @param select
     * @return
     */
    private ImageView getSelectItemImager(String select) {
        if (select.equalsIgnoreCase(mSelectA)) {
            return mIvGmOneA;
        }
        if (select.equalsIgnoreCase(mSelectB)) {
            return mIvGmOneB;
        }
        if (select.equalsIgnoreCase(mSelectC)) {
            return mIvGmOneC;
        }
        if (select.equalsIgnoreCase(mSelectD)) {
            return mIvGmOneD;
        }
        if (select.equalsIgnoreCase(mSelectE)) {
            return mIvGmOneE;
        }
        return null;
    }

    /**
     * 设置选项是否能点击
     *
     * @param click
     */
    private void setSelectABCDEClick(boolean click) {
        mLlGmoneALayout.setClickable(click);
        mLlGmoneBLayout.setClickable(click);
        mLlGmoneCLayout.setClickable(click);
        mLlGmoneDLayout.setClickable(click);
        mLlGmOneELayout.setClickable(click);
    }

    //设置难度星星
    private void setStarNumber(int number) {
        ArrayList<ImageView> list = new ArrayList<>();
        list.add(mIvGmTwoStarOne);
        list.add(mIvGmTwoStarTwo);
        list.add(mIvGmTwoStarThree);
        list.add(mIvGmTwoStarFour);
        list.add(mIvGmTwoStarFive);
        for (int i = 0; i < number; i++) {
            ImageView imageView = list.get(i);
            imageView.setImageDrawable(mGmImgManageUtil.getDrawable(R.mipmap.ic_b_difficulty_s));
        }

    }

    //请求评论
    @Override
    public void EvaluesOneSuccess(String sucess) {
        EvalueNewVo vo = Utils.getGosnT(sucess, EvalueNewVo.class);
        clearData();
        addListData(vo.getDatas());
        mTvGmfourNuber.setText("(" + vo.getTotal().getTotal() + ")");
        if (!mArray.isEmpty() && mArray.size() % DataMessageVo.CINT_PANGE_SIZE == 0) {
            mShowMoreView = true;
        } else {
            mShowMoreView = false;
        }
        if (vo.getTotal().getTotal() == mArray.size())
            mShowMoreView = false;
        showMoreView(vo.getTotal().getTotal());
        mEvalueAdapter.notifyDataSetChanged();
    }

    /**
     * 显示加载更多布局
     */
    private void showMoreView(int number) {
        mIvNetEmptyContent.setVisibility(number == 0 ? View.VISIBLE : View.GONE);
        mLlGmfourMoreData.setVisibility(mShowMoreView ? View.VISIBLE : View.GONE);
    }

    @Override
    public void EvaluesOneError(String msg) {
        T_ERROR(mContext);
    }

    @Override
    public void EvaluesMoreSuccess(String sucess) {
        EvalueNewVo vo = Utils.getGosnT(sucess, EvalueNewVo.class);
        addListData(vo.getDatas());
        if (!mArray.isEmpty() && mArray.size() % DataMessageVo.CINT_PANGE_SIZE == 0) {
            mShowMoreView = true;
        } else {
            mShowMoreView = false;
        }
        if (vo.getTotal().getTotal() == mArray.size()) {
            mShowMoreView = false;
        }
        showMoreView(vo.getTotal().getTotal());
        mEvalueAdapter.notifyDataSetChanged();

    }

    //请求评论
    @Override
    public void EvaluesMoreError(String msg) {
        T_ERROR(mContext);
    }

    private void clearData() {
        if (mArray == null) {
            mArray = new ArrayList();
        } else {
            mArray.clear();
        }
    }

    private void addListData(List<?> list) {
        if (mArray == null) {
            clearData();
        }
        if (list == null || list.isEmpty()) {
            return;
        }
        mArray.addAll(list);
    }

    /**
     * 当前数据有几页
     *
     * @return
     */
    private int getNowPage() {
        if (mArray == null || mArray.isEmpty())
            return 0;
        if (mArray.size() % DataMessageVo.CINT_PANGE_SIZE == 0)
            return mArray.size() / DataMessageVo.CINT_PANGE_SIZE;
        else
            return mArray.size() / DataMessageVo.CINT_PANGE_SIZE + 1;
    }

    /**
     * 定义获取用户操作
     */
    public interface notification {
        /**
         * 保存用做题数据
         *
         * @param vo
         */
        public void saveUserDoLog(DoBankSqliteVo vo);

        /**
         * 获取用户做题记录
         *
         * @param quesiton_id
         * @return
         */
        public DoBankSqliteVo getUserDoLog(int quesiton_id);

        /**
         * 删除用户做题记录
         *
         * @param quesiton_id
         */
        public void deleteUserDolog(int quesiton_id);

        /**
         * 下一题
         */
        public void doRightGo();

        /**
         * 查询用户做数据
         *
         * @param qustion_id
         * @return
         */
        public DoBankSqliteVo queryUserData(int qustion_id);

        /**
         * 该变颜色
         *
         * @param colorManger
         */
        public void changerColor(GmReadColorManger colorManger);

        /**
         * 保存用户观看记录
         */
        public void doSaveLook(LookSqliteVo vo);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearSelectItem();
    }

    private void clearSelectItem() {
        mSelectMorItemA = null;
        mSelectMorItemB = null;
        mSelectMorItemC = null;
        mSelectMorItemD = null;
        mSelectMorItemE = null;
    }
}
