package com.xuechuan.xcedu.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xuechuan.xcedu.base.DataMessageVo;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description: 穿件本地数据库
 * @author: L-BackPacker
 * @date: 2018.12.10 上午 8:51
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class UserInfomOpenHelp extends SQLiteOpenHelper {


    private static volatile UserInfomOpenHelp _singleton;
    private Context mContext;

    public static UserInfomOpenHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (UserInfomOpenHelp.class) {
                if (_singleton == null) {
                    _singleton = new UserInfomOpenHelp(context);
                }
            }
        }
        return _singleton;
    }

    public UserInfomOpenHelp(Context context) {
        super(context, DataMessageVo.USER_INFOM_DATABASE_NAME, null, DataMessageVo.USER_INFOM_DATABASE_VERISON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataMessageVo.userinfom);
        db.execSQL(DataMessageVo.look);
        db.execSQL(DataMessageVo.error);
        db.execSQL(DataMessageVo.videolook);
        db.execSQL(DataMessageVo.delete);
        db.execSQL(DataMessageVo.question);
        db.execSQL(DataMessageVo.questionChapter);
        db.execSQL(DataMessageVo.questiontagrelagtion);
        db.execSQL(DataMessageVo.tag);
        db.execSQL(DataMessageVo.questionexamrelation);
        db.execSQL(DataMessageVo.doBank);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
