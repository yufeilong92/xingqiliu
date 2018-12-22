package com.xuechuan.xcedu.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.db.DbHelp.DatabaseContext;
import com.xuechuan.xcedu.utils.DbQueryUtil;
import com.xuechuan.xcedu.utils.StringUtil;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionTagRelationSqliteVo;

import java.io.File;
import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description: 问题
 * @author: L-BackPacker
 * @date: 2018.12.11 下午 2:46
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class QuestionTagreLationSqliteHelp {
    private static volatile QuestionTagreLationSqliteHelp _singleton;
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;
    private final DbQueryUtil mDbQueryUtil;

    private QuestionTagreLationSqliteHelp(Context context) {
        this.mContext = context;
        mSqLiteDatabase = createtable();
        mDbQueryUtil = DbQueryUtil.get_Instance();
    }

    public static QuestionTagreLationSqliteHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (QuestionTagreLationSqliteHelp.class) {
                if (_singleton == null) {
                    _singleton = new QuestionTagreLationSqliteHelp(context);
                }
            }
        }
        return _singleton;
    }

    public void initOpenUserInfom() {
        DbPathUitl instance = DbPathUitl.get_Instance(mContext);
        String dbPath = instance.getDbPath();
        if (StringUtil.isEmpty(dbPath)) return;
        String concat = dbPath.concat(DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION);
        File file = new File(concat);
        mSqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
        if (mSqLiteDatabase == null) {
            createtable();
        }
    }

    private SQLiteDatabase createtable() {
        DatabaseContext context = new DatabaseContext(mContext);
//        UserInfomOpenHelp userInfomOpenHelp =new UserInfomOpenHelp(context);
        UserInfomOpenHelp userInfomOpenHelp = UserInfomOpenHelp.get_Instance(context);
        return userInfomOpenHelp.getWritableDatabase();
    }

    private boolean empty() {
        if (mSqLiteDatabase == null)
            return true;
        if (mSqLiteDatabase.isReadOnly())
            return true;
        return false;
    }

    public synchronized void addQuestionTagerlationItem(QuestionTagRelationSqliteVo sqliteVo) {
        if (empty()) return;
/*        String sql = "insert into " + DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION +
                "(questionid ,tagid) values (" +
                sqliteVo.getQuestionid() + "," + sqliteVo.getTagid() + ");";
        mSqLiteDatabase.execSQL(sql);*/
        try {
//            mSqLiteDatabase.beginTransaction();
            if (quesryIsAdd(sqliteVo.getQuestionid(), sqliteVo.getTagid())) return;
            ContentValues values = setValues(sqliteVo);
            mSqLiteDatabase.insert(DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION, null, values);
//            mSqLiteDatabase.setTransactionSuccessful();
//            mSqLiteDatabase.endTransaction();
        } catch (Exception e) {
            if (mSqLiteDatabase.isOpen()) {
                mSqLiteDatabase.close();
            }
            e.printStackTrace();
        }


    }
    public void close() {
        if (empty()) return;
        mSqLiteDatabase.close();
    }
    /**
     * 批量添加
     *
     * @param list
     */
    public void addListQuestionData(ArrayList<QuestionTagRelationSqliteVo> list) {
        if (empty()) return;
        try {
//            mSqLiteDatabase.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                QuestionTagRelationSqliteVo sqliteVo = list.get(i);
                if (quesryIsAdd(sqliteVo.getQuestionid(), sqliteVo.getTagid())) continue;
                ContentValues values = setValues(sqliteVo);
                mSqLiteDatabase.insert(DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION, null, values);
            }
//            mSqLiteDatabase.setTransactionSuccessful();
//            mSqLiteDatabase.endTransaction();
            mSqLiteDatabase.close();

        } catch (Exception e) {
            if (mSqLiteDatabase.isOpen()) {
                mSqLiteDatabase.close();
            }
            e.printStackTrace();
        }

    }

    private boolean quesryIsAdd(int questionid, int tagid) {
        if (empty()) return false;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION, null,
                "questionid=? and tagid=?", new String[]{String.valueOf(questionid), String.valueOf(tagid)},
                null, null, null);
        if (query.moveToNext()) {
            return true;
        }
        return false;
    }

    public static ContentValues setValues(QuestionTagRelationSqliteVo sqliteVo) {
        ContentValues values = new ContentValues();
        values.put("questionid", sqliteVo.getQuestionid());
        values.put("tagid", sqliteVo.getTagid());
        return values;
    }

    public void UpdataQuestionTagerRaltionItem(QuestionTagRelationSqliteVo sqliteVo) {
        if (empty()) return;
        String sql = "update " + DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION
                + " set questionid =" + sqliteVo.getQuestionid() + "," +
                "tagid=" + sqliteVo.getTagid() + " where id=" + sqliteVo.getId();
        mSqLiteDatabase.execSQL(sql);
    }

    public void deleteQuesitonRaltion() {
        if (empty()) return;
        mSqLiteDatabase.delete(DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION,
                "", new String[]{});

    }


    public ArrayList<QuestionTagRelationSqliteVo> findAll() {
        if (empty()) return null;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION, null, null, null, null, null, null);
        mDbQueryUtil.initCursor(query);
        ArrayList<QuestionTagRelationSqliteVo> list = new ArrayList<>();
        while (query.moveToNext()) {
            QuestionTagRelationSqliteVo sqliteVo = getQuestionTagRelationSqliteVo(mDbQueryUtil);
            list.add(sqliteVo);
        }
        return list;
    }

    public static QuestionTagRelationSqliteVo getQuestionTagRelationSqliteVo(DbQueryUtil mDbQueryUtil) {
        QuestionTagRelationSqliteVo sqliteVo = new QuestionTagRelationSqliteVo();
        int questionid = mDbQueryUtil.queryInt("questionid");
        int tagid = mDbQueryUtil.queryInt("tagid");
        sqliteVo.setId(questionid);
        sqliteVo.setTagid(tagid);
        return sqliteVo;
    }
}
