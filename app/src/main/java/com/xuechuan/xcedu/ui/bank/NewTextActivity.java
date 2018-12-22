package com.xuechuan.xcedu.ui.bank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.adapter.bank.GmGridViewAdapter;
import com.xuechuan.xcedu.adapter.bank.TopicAdapter;
import com.xuechuan.xcedu.base.BaseActivity;
import com.xuechuan.xcedu.fragment.GmReadFragment;
import com.xuechuan.xcedu.sqlitedb.DoBankSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.LookSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.QuestionSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.UserInfomDbHelp;
import com.xuechuan.xcedu.utils.DialogUtil;
import com.xuechuan.xcedu.utils.EncryptionUtil;
import com.xuechuan.xcedu.utils.GmReadColorManger;
import com.xuechuan.xcedu.utils.GmTextUtil;
import com.xuechuan.xcedu.utils.T;
import com.xuechuan.xcedu.vo.SqliteVo.DoBankSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.LookSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.UserInfomSqliteVo;
import com.xuechuan.xcedu.weight.CommonPopupWindow;
import com.xuechuan.xcedu.weight.ReaderViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: TextActivity
 * @Package com.xuechuan.xcedu.ui.bank
 * @Description: 新做题界面
 * @author: L-BackPacker
 * @date: 2018.12.05 下午 3:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.05
 */
public class NewTextActivity extends BaseActivity implements View.OnClickListener, GmReadFragment.notification {
    /**
     * 章节id
     */
    private static String CHAPTER_ID = "com.xuechuan.xcedu.ui.bank.CHAPTER_ID";
    /**
     * 科目
     */
    private static String COUERSID = "com.xuechuan.xcedu.ui.bank.COUERSID";
    private Context mContext;

    private int mChapter_Id;
    private String mCouers_id;
    private ArrayList<QuestionSqliteVo> lists;
    private DoBankSqliteHelp mDoBankHelp;

    private GmReadFragment mReadFragment;
    private ImageView mIvTextBarBack;
    private ImageView mIvTextBarTimeImg;
    private TextView mActivityTitleText;
    private LinearLayout mTvTextBarTitle;
    private ImageView mIvTextBarDelect;
    private ImageView mIvTextBarMore;
    private LinearLayout mLlTextTitleBar;
    private View mVGmReadLine;
    private ReaderViewPager mReaderViewPager;
    private ImageView mShadowView;
    private FrameLayout mFlContentLayoutOne;
    private TextView mTvTextCollect;
    private ImageView mIvTextMenu;
    private TextView mTvTextQid;
    private TextView mTvTextAllqid;
    private TextView mTvTextShare;
    private LinearLayout mLiTextNavbar;
    private LinearLayout mLlNewtextBar;
    private LinearLayout mSlidingLayout;
    private GmTextUtil mTextUtil;
    private CommonPopupWindow mPopAnswer;
    private GmReadColorManger mColorManger;
    private LookSqliteHelp mLookSqliteHelp;
    private DialogUtil mDialogUtil;
    private QuestionSqliteVo mQuestionSqliteVo;
    private View mVGmBarLine;

    /**
     * @param context
     * @param id        章节id
     * @param mCouersid 科目id
     * @return
     */
    public static Intent start_Intent(Context context, int id, String mCouersid) {
        Intent intent = new Intent(context, NewTextActivity.class);
        intent.putExtra(CHAPTER_ID, id);
        intent.putExtra(COUERSID, mCouersid);
        return intent;
    }

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text);
        initView();
    }
*/

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_text);
        if (getIntent() != null) {
            //章节id
            mChapter_Id = getIntent().getIntExtra(CHAPTER_ID, -1);
            //科目id
            mCouers_id = getIntent().getStringExtra(COUERSID);

        }
        initView();
        initUtils();
        lists = getLists();
        //初始化翻页效果
        initReadViewPager();
        //显示下表
        initData(0);
        //显示对话框
        doShowDialogEvent();

    }

    /**
     * 显示继续答题对话框
     */
    private void doShowDialogEvent() {
        if (mLookSqliteHelp != null) {
            if (lists != null && !lists.isEmpty()) {
                final LookSqliteVo look = mLookSqliteHelp.findLookWithTidChapterId(
                        lists.get(0).getChapter_id(), lists.get(0).getCourseid());
                if (look == null) return;
                mDialogUtil.showContinueDialog(mContext, look.getRightnumber());
                mDialogUtil.setContinueClickListener(new DialogUtil.onContincueClickListener() {
                    @Override
                    public void onCancelClickListener() {
                        if (mReaderViewPager != null) {
                            mReaderViewPager.setCurrentItem(0, true);
                            mLookSqliteHelp.deleteLookItem(look.getId());
                        }
                    }

                    @Override
                    public void onNextClickListener() {
                        if (mReaderViewPager != null)
                            mReaderViewPager.setCurrentItem(Integer.parseInt(look.getRightnumber()) - 1, true);
                    }
                });
            }
        }

    }

    private void initUtils() {
        mTextUtil = GmTextUtil.get_Instance(mContext);
        mDoBankHelp = DoBankSqliteHelp.get_Instance(mContext);
        mLookSqliteHelp = LookSqliteHelp.get_Instance(mContext);
        mDialogUtil = DialogUtil.getInstance();
    }


    private void initData(int index) {
        mTvTextAllqid.setText(String.valueOf(lists.size()));
        mTvTextQid.setText(String.valueOf(++index));
    }


    /**
     * 设置答题卡布局
     */
    private void showAnswerCardLayout() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;

        mPopAnswer = new CommonPopupWindow(this, R.layout.pop_item_answer, ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight * 0.7)) {
            private Button mBtnSubmit;
            private TextView mTvPopNew;
            private TextView mTvLine;
            private TextView mTvTextPopERROR;
            private TextView mTvTextPopRight;
            private TextView mTvTextPopRegression;
            private TextView mTvPopCount;
            private RecyclerView mRlvPopContent;
            private GridView mGvPopContent;
            private LinearLayout mLLPopLayout;

            @Override
            protected void initView() {
                View view = getContentView();
                mTvPopNew = (TextView) view.findViewById(R.id.tv_pop_new);
                mTvLine = (TextView) view.findViewById(R.id.tv_line);
                mTvPopCount = (TextView) view.findViewById(R.id.tv_pop_count);
                mRlvPopContent = view.findViewById(R.id.rlv_pop_content);
                mGvPopContent = view.findViewById(R.id.gv_pop_content);
                mBtnSubmit = (Button) view.findViewById(R.id.btn_pop_answer_sumbit);
                mLLPopLayout = (LinearLayout) view.findViewById(R.id.ll_pop_layout);
                mTvTextPopRight = (TextView) view.findViewById(R.id.tv_text_pop_right);
                mTvTextPopERROR = (TextView) view.findViewById(R.id.tv_text_pop_error);
                mTvTextPopRegression = (TextView) view.findViewById(R.id.tv_text_pop_regression);
            }

            @Override
            protected void initEvent() {
                if (mColorManger != null) {
                    mTvPopNew.setTextColor(mColorManger.getmTextTitleColor());
                    mTvPopCount.setTextColor(mColorManger.getmTextTitleColor());
                    mBtnSubmit.setTextColor(mColorManger.getmTextTitleColor());
                    mLLPopLayout.setBackgroundColor(mColorManger.getmLayoutBgColor());
                    mTvTextPopRight.setTextColor(mColorManger.getmTextFuColor());
                    mTvTextPopRegression.setTextColor(mColorManger.getmTextFuColor());
                    mTvTextPopERROR.setTextColor(mColorManger.getmTextFuColor());
                    mTvLine.setTextColor(mColorManger.getmTextTitleColor());
                }
                mTvPopNew.setText(String.valueOf(curPosition2 + 1));
                mTvPopCount.setText(String.valueOf(lists.size()));
                bindGridViewAdapter();
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mTextUtil.setBackgroundAlpha(1f, NewTextActivity.this);
                    }
                });
            }

            private void bindGridViewAdapter() {
                GmGridViewAdapter adapter = new GmGridViewAdapter(mContext, lists);
                adapter.doEventListDatas(findAllDoDatas());
                adapter.doEventColor(mColorManger);
                mGvPopContent.setAdapter(adapter);
                mGvPopContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        curPosition2 = position;
                        mReaderViewPager.setCurrentItem(position);
                        mPopAnswer.getPopupWindow().dismiss();
                    }
                });
            }
        };
        mPopAnswer.showAtLocation(mSlidingLayout, Gravity.BOTTOM, 0, 0);
        mTextUtil.setBackgroundAlpha(0.5f, NewTextActivity.this);
    }

    private int prePosition2;
    private int curPosition2;

    private void initReadViewPager() {
        mReaderViewPager.setAdapter(new GmFragmentAdpater(getSupportFragmentManager(), mContext, lists, mCouers_id));
        mReaderViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mShadowView.setTranslationX(mReaderViewPager.getWidth() - positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                curPosition2 = position;
                prePosition2 = curPosition2;
                initData(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mReaderViewPager.setOffscreenPageLimit(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_text_menu://菜单
                showAnswerCardLayout();
                break;
            case R.id.iv_text_bar_more://更多
                if (mReadFragment != null) {
                    mReadFragment.showGmSetting();
                }
                break;
            case R.id.iv_text_bar_back://返回
                this.finish();
                break;


        }
    }

    private ArrayList<DoBankSqliteVo> findAllDoDatas() {
        ArrayList<DoBankSqliteVo> list = new ArrayList<>();
  /*      for (int i = 0; i < lists.size(); i++) {
            QuestionSqliteVo vo = lists.get(i);
            DoBankSqliteVo doVo = mDoBankHelp.queryWQid(vo.getQuestion_id());
            if (doVo != null)
                list.add(doVo);
        }*/
        ArrayList<DoBankSqliteVo> doBankSqliteVos = mDoBankHelp.finDAllUserDoText();
        if (doBankSqliteVos == null || doBankSqliteVos.isEmpty()) return list;
        for (int i = 0; i < lists.size(); i++) {
            QuestionSqliteVo vo = lists.get(i);
            for (int k = 0; k < doBankSqliteVos.size(); k++) {
                DoBankSqliteVo sqliteVo = doBankSqliteVos.get(k);
                if (vo.getQuestion_id()==sqliteVo.getQuestion_id()) {
                    list.add(sqliteVo);
                }
            }

        }
        return list;
    }

    private void initView() {
        mContext = this;
        mIvTextBarBack = (ImageView) findViewById(R.id.iv_text_bar_back);
        mIvTextBarBack.setOnClickListener(this);
        mIvTextBarTimeImg = (ImageView) findViewById(R.id.iv_text_bar_time_img);
        mIvTextBarTimeImg.setOnClickListener(this);
        mActivityTitleText = (TextView) findViewById(R.id.activity_title_text);
        mActivityTitleText.setOnClickListener(this);
        mTvTextBarTitle = (LinearLayout) findViewById(R.id.tv_text_bar_title);
        mTvTextBarTitle.setOnClickListener(this);
        mIvTextBarDelect = (ImageView) findViewById(R.id.iv_text_bar_delect);
        mIvTextBarDelect.setOnClickListener(this);
        mIvTextBarMore = (ImageView) findViewById(R.id.iv_text_bar_more);
        mIvTextBarMore.setOnClickListener(this);
        mLlTextTitleBar = (LinearLayout) findViewById(R.id.ll_text_title_bar);
        mLlTextTitleBar.setOnClickListener(this);
        mVGmReadLine = (View) findViewById(R.id.v_gm_read_line);
        mVGmReadLine.setOnClickListener(this);
        mReaderViewPager = (ReaderViewPager) findViewById(R.id.readerViewPager);
        mReaderViewPager.setOnClickListener(this);
        mShadowView = (ImageView) findViewById(R.id.shadowView);
        mShadowView.setOnClickListener(this);
        mFlContentLayoutOne = (FrameLayout) findViewById(R.id.fl_content_layout_one);
        mFlContentLayoutOne.setOnClickListener(this);
        mTvTextCollect = (TextView) findViewById(R.id.tv_text_collect);
        mTvTextCollect.setOnClickListener(this);
        mIvTextMenu = (ImageView) findViewById(R.id.iv_text_menu);
        mIvTextMenu.setOnClickListener(this);
        mTvTextQid = (TextView) findViewById(R.id.tv_text_qid);
        mTvTextQid.setOnClickListener(this);
        mTvTextAllqid = (TextView) findViewById(R.id.tv_text_allqid);
        mTvTextAllqid.setOnClickListener(this);
        mTvTextShare = (TextView) findViewById(R.id.tv_text_share);
        mTvTextShare.setOnClickListener(this);
        mLiTextNavbar = (LinearLayout) findViewById(R.id.li_text_navbar);
        mLiTextNavbar.setOnClickListener(this);
        mLlNewtextBar = (LinearLayout) findViewById(R.id.ll_newtext_bar);
        mLlNewtextBar.setOnClickListener(this);
        mSlidingLayout = (LinearLayout) findViewById(R.id.sliding_layout);
        mSlidingLayout.setOnClickListener(this);
        mVGmBarLine = (View) findViewById(R.id.v_gm_bar_line);
        mVGmBarLine.setOnClickListener(this);
    }

    public ArrayList<QuestionSqliteVo> getLists() {
        QuestionSqliteHelp help = QuestionSqliteHelp.get_Instance(mContext);
        ArrayList<QuestionSqliteVo> questionListData = help.getChapterQuestionListData(mChapter_Id, mCouers_id);
        if (questionListData != null && !questionListData.isEmpty()) {
            for (int i = 0; i < questionListData.size(); i++) {
                QuestionSqliteVo vo = questionListData.get(i);
                String d = EncryptionUtil.D(vo.getQuestion());
                vo.setQuestionStr(d);
                String keyword = EncryptionUtil.D(vo.getKeywords());
                vo.setKeywordStr(keyword);
                String explain = EncryptionUtil.D(vo.getExplained());
                vo.setExplainedStr(explain);
            }
        }
        return questionListData;
    }

    @Override
    public void saveUserDoLog(DoBankSqliteVo vo) {
        mDoBankHelp.addDoBankItem(vo);

    }

    @Override
    public DoBankSqliteVo getUserDoLog(int quesiton_id) {
        if (mDoBankHelp == null) return null;
        DoBankSqliteVo vo = mDoBankHelp.queryWQid(quesiton_id);
        return vo;
    }

    @Override
    public void deleteUserDolog(int quesiton_id) {
        mDoBankHelp.deleteBankWithQuestid(quesiton_id);
    }

    /**
     * 下一题
     */
    @Override
    public void doRightGo() {
        if (mReaderViewPager == null) return;
        int currentItem = mReaderViewPager.getCurrentItem();
        currentItem = currentItem + 1;
        if (currentItem < 0) {
            T.showToast(mContext, "已经是最后一题");
            currentItem = 0;
        }
        mReaderViewPager.setCurrentItem(currentItem, true);
    }

    @Override
    public void changerColor(GmReadColorManger colorManger) {
        this.mColorManger = colorManger;
        mLiTextNavbar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mTvTextCollect.setTextColor(colorManger.getmTextFuColor());
        mTvTextShare.setTextColor(colorManger.getmTextFuColor());
        mTvTextQid.setTextColor(colorManger.getmTextRedColor());
        mTvTextAllqid.setTextColor(colorManger.getmTextRedColor());
        mLlTextTitleBar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mActivityTitleText.setTextColor(colorManger.getmTextTitleColor());
        mLlTextTitleBar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mVGmReadLine.setBackgroundColor(colorManger.getmCutLineColor());
        //标题
        mLlTextTitleBar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mVGmBarLine.setBackgroundColor(colorManger.getmCutLineColor());

    }

    /**
     * 保存用户做题记录
     *
     * @param vo
     */
    @Override
    public void doSaveLook(LookSqliteVo vo) {

    }

    @Override
    public DoBankSqliteVo queryUserData(int qustion_id) {
        if (mDoBankHelp == null) return null;
        return mDoBankHelp.queryWQid(qustion_id);
    }

    public class GmFragmentAdpater extends FragmentPagerAdapter {

        private final String mCourseid;
        private Context mContext;
        private List<?> mListDatas;

        public GmFragmentAdpater(FragmentManager fm, Context mContext, List<?> mListDatas, String coursid) {
            super(fm);
            this.mListDatas = mListDatas;
            this.mContext = mContext;
            this.mCourseid = coursid;
        }

        @Override
        public Fragment getItem(int position) {
            if (mListDatas.size() == 0) {
                mReadFragment = GmReadFragment.newInstance(null, position, mCourseid);
            } else {
                mQuestionSqliteVo = (QuestionSqliteVo) mListDatas.get(position);

                mReadFragment = GmReadFragment.newInstance(mQuestionSqliteVo, position, mCourseid);
            }
            return mReadFragment;
        }

        @Override
        public int getCount() {
            if (mListDatas.size() == 0) {
                return 1;
            } else
                return mListDatas.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        doDoText();
        if (mDoBankHelp != null) {
            mDoBankHelp.delelteTable();
        }
    }

    private void doDoText() {
        if (curPosition2 == 0) return;
        if (mQuestionSqliteVo != null) {
            UserInfomDbHelp help = UserInfomDbHelp.get_Instance(mContext);
            UserInfomSqliteVo vo = help.findUserInfomVo();
            LookSqliteVo lookSqliteVo = new LookSqliteVo();
            lookSqliteVo.setChapterid(mQuestionSqliteVo.getChapter_id());
            lookSqliteVo.setKid(mQuestionSqliteVo.getCourseid());
            lookSqliteVo.setTextid(mQuestionSqliteVo.getQuestion_id());
            lookSqliteVo.setRightnumber(String.valueOf(curPosition2 + 1));
            lookSqliteVo.setUserid(String.valueOf(vo.getSaffid()));
            lookSqliteVo.setRightAllNumber(String.valueOf(lists.size()));
            mLookSqliteHelp.addDoLookItem(lookSqliteVo);
        }

    }
}