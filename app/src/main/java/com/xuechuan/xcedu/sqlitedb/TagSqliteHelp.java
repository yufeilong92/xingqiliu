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
import com.xuechuan.xcedu.vo.SqliteVo.TagSqliteVo;

import java.io.File;
import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description:tag tag帮助类
 * @author: L-BackPacker
 * @date: 2018.12.11 下午 3:04
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class TagSqliteHelp {
    private static volatile TagSqliteHelp _singleton;
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;
    private final DbQueryUtil mDbQueryUtil;

    private TagSqliteHelp(Context context) {
        this.mContext = context;
        mSqLiteDatabase = createtable();
        mDbQueryUtil = DbQueryUtil.get_Instance();
    }

    public static TagSqliteHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (TagSqliteHelp.class) {
                if (_singleton == null) {
                    _singleton = new TagSqliteHelp(context);
                }
            }
        }
        return _singleton;
    }


    public void initOpenUserInfom() {
        DbPathUitl instance = DbPathUitl.get_Instance(mContext);
        String dbPath = instance.getDbPath();
        if (StringUtil.isEmpty(dbPath)) return;
        String concat = dbPath.concat(DataMessageVo.USER_QUESTIONTABLE_TAG);
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
    public void close() {
        if (empty()) return;
        mSqLiteDatabase.close();
    }
    public void addTagItem(TagSqliteVo sqliteVo) {
        if (empty()) return;
    /*    String sql = "insert into " + DataMessageVo.USER_QUESTIONTABLE_TAG +
                "(tagname,courseid,questionnum,tagid)" +
                " values ('" +
                sqliteVo.getTagname() + "'" +
                sqliteVo.getCourseid() + "," +
                sqliteVo.getQuestionnum() + "," +
                sqliteVo.getTagid() +
                ") ;";
        mSqLiteDatabase.execSQL(sql);*/
        try {
//            mSqLiteDatabase.beginTransaction();
            if (quesryIsAdd(sqliteVo.getTagid())) return;
            ContentValues values = setContentValues(sqliteVo);
            mSqLiteDatabase.insert(DataMessageVo.USER_QUESTIONTABLE_TAG, null, values);
//            mSqLiteDatabase.setTransactionSuccessful();
//            mSqLiteDatabase.endTransaction();
        } catch (Exception e) {
            if (mSqLiteDatabase.isOpen()) {
                mSqLiteDatabase.close();
            }
            e.printStackTrace();
        }

    }

    /**
     * 批量添加
     *
     * @param list
     */
    public void addListTagItem(ArrayList<TagSqliteVo> list) {
        if (empty()) return;
        try {
//            mSqLiteDatabase.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                TagSqliteVo tagSqliteVo = list.get(i);
                if (quesryIsAdd(tagSqliteVo.getTagid())) continue;
                ContentValues values = setContentValues(tagSqliteVo);
                mSqLiteDatabase.insert(DataMessageVo.USER_QUESTIONTABLE_TAG, null, values);
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

    private boolean quesryIsAdd(int id) {
        if (empty()) return false;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QUESTIONTABLE_TAG, null, "tagid=?",
                new String[]{String.valueOf(id)}, null, null, null);
        while (query.moveToNext()) {
            return true;
        }
        return false;
    }

    public static ContentValues setContentValues(TagSqliteVo sqliteVo) {
        ContentValues values = new ContentValues();
        values.put("tagname", sqliteVo.getTagname());
        values.put("courseid", sqliteVo.getCourseid());
        values.put("questionnum", sqliteVo.getQuestionnum());
        values.put("tagid", sqliteVo.getTagid());
        return values;
    }


    public ArrayList<TagSqliteVo> findTagAll() {
        if (empty()) return null;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QUESTIONTABLE_TAG, null, null, null, null
                , null, null);
        mDbQueryUtil.initCursor(query);
        ArrayList<TagSqliteVo> list = new ArrayList<>();
        while (query.moveToNext()) {
            TagSqliteVo vo = getTagSqliteVo(mDbQueryUtil, true);
            list.add(vo);
        }
        return list;

    }

    public ArrayList<TagSqliteVo> finTagAllWithCourserid(int courseid) {
        if (empty()) {
            return null;
        }
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QUESTIONTABLE_TAG, null,
                "courseid=?", new String[]{String.valueOf(courseid)}, null, null, null);
        mDbQueryUtil.initCursor(query);
        ArrayList<TagSqliteVo> list = new ArrayList<>();
        while (query.moveToNext()) {
            TagSqliteVo tagSqliteVo = getTagSqliteVo(mDbQueryUtil, true);
            list.add(tagSqliteVo);
        }
        return list;

    }


    public synchronized static TagSqliteVo getTagSqliteVo(DbQueryUtil mDbQueryUtil, boolean load) {
        TagSqliteVo vo = new TagSqliteVo();
        int id = mDbQueryUtil.queryInt("id");
        if (load) {
            int tagid = mDbQueryUtil.queryInt("tagid");
            vo.setTagid(tagid);
            vo.setId(id);
        } else {
            vo.setTagid(id);
        }
        String tagname = mDbQueryUtil.queryString("tagname");
        int courseid = mDbQueryUtil.queryInt("courseid");
        int questionnum = mDbQueryUtil.queryInt("questionnum");
        vo.setCourseid(courseid);
        vo.setQuestionnum(questionnum);
        vo.setTagname(tagname);
        return vo;
    }
}
