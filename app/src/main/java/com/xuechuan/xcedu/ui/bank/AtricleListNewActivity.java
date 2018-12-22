package com.xuechuan.xcedu.ui.bank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.multilevel.treelist.Node;
import com.multilevel.treelist.OnTreeNodeClickListener;
import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.adapter.bank.AtricleNewTreeAdapter;
import com.xuechuan.xcedu.adapter.home.AtricleTreeAdapter;
import com.xuechuan.xcedu.base.BaseActivity;
import com.xuechuan.xcedu.sqlitedb.QuestionChapterSqliteHelp;
import com.xuechuan.xcedu.vo.ChildrenBeanVo;
import com.xuechuan.xcedu.vo.SkillTextVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionChapterSqliteVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: AtricleListNewActivity
 * @Package com.xuechuan.xcedu.ui.bank
 * @Description: 章节目录
 * @author: L-BackPacker
 * @date: 2018.12.11 下午 4:51
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.11
 */

public class AtricleListNewActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 科目id
     */
    public static final String COURSEID = "courseid";
    /**
     * 所属类型1,题库(练习题)；2,历年真题；3,独家密卷
     */
    public static final String MOLD = "mold";
    private String mCouersid;
    private RecyclerView mRlvNewtreeContent;
    private Context mContext;
    private String mMold;
    private ImageView mIvNetEmptyContent;
    private AtricleNewTreeAdapter mAdapter;
    private List<Node> mNodeLists;

    /**
     * 记录用户选择的试题位置
     */
    private String mselectPostion = "-1";

    /**
     * @param context
     * @param courseid 科目id
     * @return
     */
    public static Intent newInstance(Context context, String courseid, String mold) {
        Intent intent = new Intent(context, AtricleListNewActivity.class);
        intent.putExtra(COURSEID, courseid);
        intent.putExtra(MOLD, mold);
        return intent;
    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atricle_list_new);
        initView();
    }*/

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mCouersid = getIntent().getStringExtra(COURSEID);
            mMold = getIntent().getStringExtra(MOLD);

        }
        setContentView(R.layout.activity_atricle_list_new);
        initView();
        initData();
    }

    private void initData() {
        mNodeLists = new ArrayList<>();
        QuestionChapterSqliteHelp help = (QuestionChapterSqliteHelp) QuestionChapterSqliteHelp.get_Instance(mContext);
        ArrayList<QuestionChapterSqliteVo> all = help.findQuestionChapterItemAllWiteCoureseid(mCouersid, mMold, "0");

        if (all == null) {
            mRlvNewtreeContent.setVisibility(View.GONE);
            mIvNetEmptyContent.setVisibility(View.VISIBLE);
            return;
        } else {
            mRlvNewtreeContent.setVisibility(View.VISIBLE);
            mIvNetEmptyContent.setVisibility(View.GONE);
        }
        List<SkillTextVo.DatasBean> datas = getDatasBeans(help, all);
        addListData(datas);
        setAdapter(datas);
    }

    private List<SkillTextVo.DatasBean> getDatasBeans(QuestionChapterSqliteHelp help, ArrayList<QuestionChapterSqliteVo> all) {
        List<SkillTextVo.DatasBean> datas = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            SkillTextVo.DatasBean bean = new SkillTextVo.DatasBean();
            QuestionChapterSqliteVo v = all.get(i);
            bean.setId(v.getQuestionchapterid());
            bean.setParentid(v.getParentid());
            bean.setQnum(v.getQuestionnum());
            bean.setTitle(v.getChaptername());
            String questionnum = help.queryQuestionSum("questionnum", mCouersid, v.getQuestionchapterid(), v.getMold());
            bean.setQuestionNumber(questionnum);
            ArrayList<QuestionChapterSqliteVo> chine = help.QueryCourseidZWmoidParenid(mCouersid, v.getQuestionchapterid(), v.getMold());
            List<ChildrenBeanVo> vos = new ArrayList<>();
            if (chine != null && !chine.isEmpty()) {//有章节
                bean.setRnum(chine.size());
                bean.setIsend(false);
                for (int k = 0; k < chine.size(); k++) {
                    QuestionChapterSqliteVo sqliteVo = chine.get(k);
                    ChildrenBeanVo vo = new ChildrenBeanVo();
                    vo.setId(sqliteVo.getId());
                    vo.set_id(sqliteVo.getQuestionchapterid());
                    vo.setParentid(v.getQuestionchapterid());
                    vo.setChildren(null);
                    vo.setIsend(true);
                    vo.setQnum(sqliteVo.getQuestionnum());
                    vo.setRnum(0);
                    vo.setTitle(sqliteVo.getChaptername());
                    vos.add(vo);
                }
                bean.setChildren(vos);
            } else {//无章节
                bean.setRnum(0);
                bean.setChildren(vos);
                bean.setIsend(true);
            }

            datas.add(bean);
        }
        return datas;
    }


    private void initView() {
        mContext = this;
        mRlvNewtreeContent = (RecyclerView) findViewById(R.id.rlv_newtree_content);
        mIvNetEmptyContent = (ImageView) findViewById(R.id.iv_net_empty_content);
        mIvNetEmptyContent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    private void addListData(List<SkillTextVo.DatasBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            SkillTextVo.DatasBean bean = datas.get(i);
            if (mselectPostion.equals(" -1"))
                mNodeLists.add(new Node(bean.getId() + "", bean.getParentid() + "", false, bean.getTitle(), bean));
            else if (mselectPostion.equals(String.valueOf(bean.getId())))
                mNodeLists.add(new Node(bean.getId() + "", bean.getParentid() + "", true, bean.getTitle(), bean));
            else
                mNodeLists.add(new Node(bean.getId() + "", bean.getParentid() + "", false, bean.getTitle(), bean));

            if (!bean.isIsend()) {
                List<ChildrenBeanVo> vos = bean.getChildren();
                bindData(vos);
            }

        }
    }

    private void bindData(List<ChildrenBeanVo> vos) {
        for (int i = 0; i < vos.size(); i++) {
            ChildrenBeanVo vo = vos.get(i);
            mNodeLists.add(new Node(vo.getId() + "1", vo.getParentid() + "", false, vo.getTitle(), vo));
            if (!vo.isIsend()) {
                bindData(vo.getChildren());
            }
        }
    }

    private void setAdapter(List<SkillTextVo.DatasBean> datas) {
        setGridLayoutManger(mContext, mRlvNewtreeContent, 1);
//        mRlvTreeContent.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.VERTICAL));
        mAdapter = new AtricleNewTreeAdapter(mRlvNewtreeContent, this, mNodeLists,
                0, R.mipmap.ic_spread_gray, R.mipmap.ic_more_go, mCouersid, datas);

        mRlvNewtreeContent.setAdapter(mAdapter);
        mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if (!node.isRoot()) {
                    mselectPostion = String.valueOf(node.getpId());
                    if (node.getBean() instanceof ChildrenBeanVo) {
                        ChildrenBeanVo vo = (ChildrenBeanVo) node.getBean();
                        Intent intent = NewTextActivity.start_Intent(AtricleListNewActivity.this,vo.get_id(),mCouersid);
                        startActivity(intent);
                    }
                } else {
                    List children = node.getChildren();
                    if (children == null || children.isEmpty()) {
                        Intent intent = AnswerActivity.newInstances(AtricleListNewActivity.this, String.valueOf(node.getpId()),
                                mCouersid, 0);
                        startActivity(intent);
                        mselectPostion = "-1";
                        return;
                    }
                    mselectPostion = String.valueOf(node.getId());

                }
            }
        });

    }
}
