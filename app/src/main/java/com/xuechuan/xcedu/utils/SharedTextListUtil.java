package com.xuechuan.xcedu.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.xuechuan.xcedu.vo.TextDetailVo;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.utils
 * @Description: 保存数据
 * @author: L-BackPacker
 * @date: 2018/4/27 13:34
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class SharedTextListUtil {
    // 用户名key
    public final static String KEY_NAME = "3249937100970977953L";
    public final static String KEY_LEVEL = "KEY_LEVEL";
    private static SharedTextListUtil s_SharedTextListUtil;
    private static List<TextDetailVo.DataBean > s_User = null;
    private SharedPreferences msp;
    // 初始化，一般在应用启动之后就要初始化
    public static synchronized void initSharedPreference(Context context)
    {
        if (s_SharedTextListUtil == null)
        {
            s_SharedTextListUtil = new SharedTextListUtil(context);
        }
    }
    /**
     * 获取唯一的instance
     *
     * @return
     */
    public static synchronized SharedTextListUtil getInstance()
    {
        return s_SharedTextListUtil;
    }
    @SuppressLint("WrongConstant")
    public SharedTextListUtil(Context context)
    {
        msp = context.getSharedPreferences("SharedPreUtil",
                Context.MODE_PRIVATE | Context.MODE_APPEND);
    }
    public SharedPreferences getSharedPref()
    {
        return msp;
    }
    public synchronized void putListAdd(List<TextDetailVo.DataBean >user)
    {
        SharedPreferences.Editor editor = msp.edit();
        String str="";
        try {
            str = SerializableUtil.list2String(user);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        editor.putString(KEY_NAME,str);
        editor.commit();
        s_User = user;
    }
    public synchronized List <TextDetailVo.DataBean >getUser()
    {
        if (s_User == null)
        {
            s_User = new ArrayList();
            //获取序列化的数据
            String str = msp.getString(SharedTextListUtil.KEY_NAME, "");
            try {
                List<TextDetailVo.DataBean> list = SerializableUtil.string2List(str);
                if(list != null){
                    s_User = (List<TextDetailVo.DataBean>) list;
                }
            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return s_User;
    }
    public synchronized void DeleteUser()
    {
        SharedPreferences.Editor editor = msp.edit();
        editor.putString(KEY_NAME,"");
        editor.commit();
        s_User = null;
    }
}
