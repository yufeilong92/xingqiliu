package com.xuechuan.xcedu.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.adapter.bank.AnswerEvaluateAdapter;
import com.xuechuan.xcedu.db.UserInfomDb;
import com.xuechuan.xcedu.mvp.presenter.AllQuestionPresenter;
import com.xuechuan.xcedu.mvp.presenter.AnswerPresenter;
import com.xuechuan.xcedu.mvp.presenter.ErrOrColListPresenter;
import com.xuechuan.xcedu.mvp.presenter.EvaluePresenter;
import com.xuechuan.xcedu.mvp.presenter.SpecailDetailPresenter;
import com.xuechuan.xcedu.utils.MyTimeUitl;
import com.xuechuan.xcedu.vo.QuestionsBean;
import com.xuechuan.xcedu.vo.TextDetailVo;
import com.xuechuan.xcedu.vo.UseSelectItemInfomVo;
import com.xuechuan.xcedu.weight.CommonPopupWindow;
import com.xuechuan.xcedu.weight.MyRecyclerView;
import com.xuechuan.xcedu.weight.SmartScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:  GmReadOneFragment
 * ckage com.xuechuan.xcedu.fragment
 * @Description: dan单选多选界面
 * @author: L-BackPacker
 * @date:   2018.12.14 上午 9:04
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.14
 */
public class GmReadOneFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    /**
     * 科目id
     */
    private static final String MTYPEID = "curseid";
    /***
     * 标签id
     */
    private static final String TAGEIDDATA = "tageiddata";
    /***
     * 标签id
     */
    private static final String TAGEIDDATAUSER = "tageiddatauser";
    /**
     * 收藏和错题类型 err 错题 fav收藏
     */
    private static final String TAGYTTPE = "typetag";
    /**
     * 考试类型
     */
    private static final String STYLECASE = "STYLECASE";
    private static final String TAG = "====================";
    private Context mContext;
    /**
     * 问题父id
     */
    private static String QUESTIONID = "courseid";

    /**
     * 搜索章节id
     */
    private static String SEARCHZID = "SearchZid";
    /**
     * 搜索题id
     */
    private static String SEARCHTID = "SearchTid";
    /**
     * 搜索类型
     */
    private static String SEARCHTYPE = "SearchTYPE";
    /**
     * 当前题干信息
     */
    private TextDetailVo mTextDetialNew;
    /**
     * 用户选择结果
     */
    private ArrayList<UseSelectItemInfomVo> mSeletList;
    /**
     * 用户查看解析
     */

    private String mOid;
    /**
     * 第几题
     */
    private static String RNUMMARK = "rnum";

    // 第几题
    private int mMark = 0;
    //题目数据
    private List<QuestionsBean> mTextDetial;
    private AlertDialog dialog;

    //用户是否点击下一题
    private boolean isNext;
    private String A = "a";
    private String B = "b";
    private String C = "c";
    private String D = "d";
    private String E = "e";
    //记录用户选中的选项 （用于单选）
    private String mSelectOnlyitem = null;
    //多选状况
    private String mSelectMorItemA = null;
    private String mSelectMorItemB = null;
    private String mSelectMorItemC = null;
    private String mSelectMorItemD = null;
    private String mSelectMorItemE = null;
    //记录用户是否点击选项
    private boolean isClickA;
    private boolean isClickB;
    private boolean isClickC;
    private boolean isClickD;
    private boolean isClickE;
    //用户是否确认多选
    private boolean isSure = true;
    //记录用户是否点击自动下一题
    private boolean mSelectNext = false;
    //用户选中的item
    private String mRightItem = null;
    //问题题干类型
    private String mTitleType = null;
    private final String mTitleTypeOnly = "only";
    private final String mTitleTypeMore = "more";
    private final String mTitleTypeWrite = "write";
    //评价数据集合
    private List mArray;

    private String mSelectViewBgZC = "zc";
    private String mSelectViewBgZCC = "zc";
    public static final String mSelectViewBgHY = "hy";
    public static final String mSelectViewBgYJ = "yj";
    private CommonPopupWindow popMore;
    private CommonPopupWindow popSetting;
    private CommonPopupWindow popAnswer;

    private CheckBox mSwtNext;
    //用户是否提交
    private boolean isSubmit;

    //剪切答案集合
    private ArrayList<String> list;
    private AnswerPresenter mPresnter;
    private AnswerEvaluateAdapter adapter;
    private ImageView mIvBMore;
    private TextView mTvRootEmpty;
    private TextView mTvBType;
    private TextView mTvBMatter;
    private ImageView mIvBA;
    private TextView mTvBAContent;
    private LinearLayout mLlBASelect;
    private ImageView mIvBB;
    private TextView mTvBBContent;
    private LinearLayout mLlBBSelect;
    private ImageView mIvBC;
    private TextView mTvBCContent;
    private LinearLayout mLlBCselect;
    private ImageView mIvBD;
    private TextView mTvBDContent;
    private LinearLayout mLlBDSelect;
    private ImageView mIvBE;
    private TextView mTvBEContent;
    private LinearLayout mLlBESelect;
    private Button mBtnBSureKey;
    private TextView mTvBAnswer;
    private LinearLayout mLlBAnswerKey;
    private TextView mTvBRosoleContent;
    private LinearLayout mLlBJiexi;
    private TextView mTvBAccuracy;
    private LinearLayout mLlBRightLu;
    private Button mBtnBBuy;
    private LinearLayout mLiBResolveBuy;
    private MyRecyclerView mRlvEualeContent;
    //    private RecyclerView mRlvEualeContent;
    private SmartScrollView mSloViewShow;
    private LinearLayout mLlBBack;
    private LinearLayout mLlBGo;
    private ImageView mIvBExpand;
    private TextView mTvBNew;
    private TextView mTvBCount;
    private CheckBox mChbBCollect;
    private LinearLayout mLlRootLayout;


    //当前结果
    private TextDetailVo.DataBean mResultData;
    private ImageView mIvBStar1;
    private ImageView mIvBStar2;
    private ImageView mIvBStar3;
    private ImageView mIvBStar4;
    private ImageView mIvBStar5;
    private LinearLayout mLlMoreData;
    private LinearLayout mLiXia;
    //加载数据
    private boolean isMoreData = true;
    private TextView mTvAnswerNumberevlue;
    private TextView mTvAnswerAddevlua;
    private EditText mEtBSubmit;
    private ImageView mIvBSubmitSend;
    private LinearLayout mLlBSubmitEvalue;
    private EvaluePresenter mEvaluePresenter;
    private AlertDialog mSubmitDialog;
    private ImageView mIvTitleBack;
    private static TextView mActivityTitleText;
    private LinearLayout mLlTitleBar;
    private TextView mTvAnswerAnswer;
    private TextView mTvAnswerJiexi;
    //护眼
    private boolean isEye = false;
    //夜间
    private boolean isNight = false;


    private View mVLine;
    private View mVLine2;
    private View mVLine3;
    private View mVLine1;
    private RelativeLayout mRlRootLayout;
    private Button mBtnLookWenAnswer;
    private View mVLine4;
    private TextView mTvLookAnswerCan;
    private TextView mTvLookAnswerWen;
    private LinearLayout mLlLookWenDa;
    private LinearLayout mLlSelectRootLayout;
    private RelativeLayout mRlLookEvalue;
    /**
     * 做题时间
     */
    private String mDoTime = "00:00:00";
    /**
     * 用户是否购买
     */
    private boolean isBuy = false;
    /**
     * 章节标识
     */
    private static String TYPEMARK = "typemark";

    private CommonPopupWindow popShare;
    /**
     * 1 技术 2 综合 3 案例
     */
    private String mTypeMark;

    //是否是模拟考试 ture 隐藏提交答题按钮
    private boolean isExamHine = false;
    //查看解析后的数据
    private String mResultMark;
    private CommonPopupWindow popResult;
    //科目id
    private String mTypeId;
    private AllQuestionPresenter mAllQuestion;
    private String startTime = "00:00:00";
    /**
     * 科目id
     */
    private static String COURSEIDKEMU = "coureshidkumu";
    /**
     * 科目id
     */
    private static String COURSEIDKEMUUSER = "courseidkemuuser";
    /**
     * 科目
     */
    private String mCouresidKuMu;
    /**
     * 标签id
     */
    private String mTagid;
    private SpecailDetailPresenter mDetailPresenter;
    private ErrOrColListPresenter mListPresenter;
    /**
     * 题干类型收藏fav/错题err
     */
    private String mTagType;
    //错题
    public static final String ERRTYPE = "err";
    //收藏
    public static final String FAVTYPE = "fav";
    private String mCouresidUser;
    //用户收藏或者错题集合
    private String mUserTagid;
    /**
     * 用户收藏题号信息
     */
    private List<Integer> mUserData;
    private UserInfomDb mInfomDb;
    /**
     * 试题类型
     */
    private String mStyleCase;
    /**
     * 用户是否查看解析
     */
    private boolean isUserLookWenJieXi;
    /**
     * 用户提交卷否查看解析
     */
    private boolean isUserLookResultJieXi = false;
    /**
     * 是否自由组卷
     */
    private String mFreeQuestion = "";
    /**
     * 是否自由组卷
     */
    private String mFreeQuestionTrue = "true";
    /**
     * 是否自由组卷
     */
    private String mFreeQuestionFalse = "false";
    private MyTimeUitl mTimeUitl;
    private ImageView mIvTimePlay;
    private LinearLayout mVBLineBar;
    private ImageView mIvBarDelect;
    private String mSearchZid;
    private String mSearchTid;
    private String mSearchType;
    private TextView mTvNam;
    private ImageView mIvJianPan;
    private boolean isBtnClickDel = false;
    private AlertDialog mStopdialog;
    private AlertDialog mStopAlertDialog;
    private TextView mTvNoTextEmpty;
    private long mCoundDate;
    private RelativeLayout rl_euale_content;
    /**
     * 当前题目所在集合（当前做题类型试题集合）顺序
     */
    private int mRunm;
    /**
     * 当前题目所在做题类型所属类型的目标编号
     */
    private int mTargetid;
    /**
     *
     */
    private int mQt;


    private String mParam1;
    private String mParam2;
    public static GmReadOneFragment newInstance(String param1, String param2) {
        GmReadOneFragment fragment = new GmReadOneFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gm_read_one_fragment_layout, container, false);
        return view;
    }




}
