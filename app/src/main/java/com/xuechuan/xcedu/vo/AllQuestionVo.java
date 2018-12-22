package com.xuechuan.xcedu.vo;

import com.xuechuan.xcedu.base.BaseVo;

import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.vo
 * @Description: 所有题干
 * @author: L-BackPacker
 * @date: 2018/5/5 13:38
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class AllQuestionVo extends BaseVo {

    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * 所属末节章节编号
         */
        private int chapterid;
        /**
         * 所属末节章节编号
         */
        private int courseid;
        /***
         * 难度
         */
        private int difficultydegree;
        /**
         * 题目编号
         */
        private int id;
        /**
         * 问题类型2单选题3多选题4简单题
         */
        private int type;

        public int getChapterid() {
            return chapterid;
        }

        public void setChapterid(int chapterid) {
            this.chapterid = chapterid;
        }

        public int getCourseid() {
            return courseid;
        }

        public void setCourseid(int courseid) {
            this.courseid = courseid;
        }

        public int getDifficultydegree() {
            return difficultydegree;
        }

        public void setDifficultydegree(int difficultydegree) {
            this.difficultydegree = difficultydegree;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
