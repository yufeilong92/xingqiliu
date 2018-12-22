package com.xuechuan.xcedu.base;

import android.Manifest;
import android.os.Build;

import com.xuechuan.xcedu.db.Converent.DownVideoConverent;
import com.xuechuan.xcedu.utils.EncryptionUtil;
import com.xuechuan.xcedu.utils.StringUtil;
import com.xuechuan.xcedu.vo.Db.DownVideoVo;

import org.greenrobot.greendao.annotation.Convert;

import java.util.List;

import retrofit2.http.DELETE;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.base
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018/4/16 16:42
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class DataMessageVo {
    private volatile static DataMessageVo _instance;

    private DataMessageVo() {
    }

    public static DataMessageVo get_Instance() {
        if (_instance == null) {
            synchronized (DataMessageVo.class) {
                if (_instance == null) {
                    _instance = new DataMessageVo();
                }
            }
        }
        return _instance;
    }

    //微信
    public static final String APP_ID = "wx0c71e64b9e151c84";

    //接受微信登录广播
    public static final String WEI_LOGIN_ACTION = "com.weixinlogin.com";
    //用户换取access_token的code，仅在ErrCode为0时有效
    public static final String WEICODE = "code";
    //第三方程序发送时用来标识其请求的唯一性的标志，由第三方程序调用sendReq时传入，由微信终端回传，state字符串长度不能超过1K
    public static final String WEISTATE = "state";
    //用户表示
    public static final String STAFFID = "staffid";
    //时间撮
    public static final String TIMESTAMP = "timeStamp";
    //随机数
    public static final String NONCE = "nonce";
    //加密串
    public static final String SIGNATURE = "signature";
    //请求token广播
    public static final String ACITON = "com.xuechaun.action";
    //请求体
    public static final String HTTPAPPLICAITON = "application/json";
    //网络
    public static final String HTTP_AC = "ac";
    //版本（1.2.3）
    public static final String HTTP_VERSION_NAME = "version_name";
    //版本（123）
    public static final String HTTP_VERSION_CODE = "version_code";
    //手机平台（android/ios）
    public static final String HTTP_DEVICE_PLATFORM = "device_platform";
    //手机型号
    public static final String HTTP_DEVICE_TYPE = "device_type";
    //手机品牌
    public static final String HTTP_DEVICE_BRAND = "device_brand";
    //操作系统版本（8.0.0）
    public static final String HTTP_OS_VERSION = "os_version";
    //分辨率
    public static final String HTTP_RESOLUTION = "resolution";
    //dpi
    public static final String HTTP_DPI = "dpi";
    //定位信息
    public static final String LOCATIONACTION = "com.xuechaun.loaction";
    //默认请求记录数
    public static final int CINT_PANGE_SIZE = 10;
    //ac文章评论
    public static final String USERTYPEAC = "ac";
    //a文章
    public static final String USERTYPEA = "a";
    //qc问题评论
    public static final String USERTYPEQC = "qc";
    //vc视频评论
    public static final String USERTYPEVC = "vc";
    //文章
    public static final String USERTYOEARTICLE = "article";
    //视频
    public static final String USERTYOEVIDEO = "question video";
    //问题
    public static final String QUESTION = "question";
    //文章
    public static final String ARTICLE = "article";
    //图书
    public static final String BOOK = "book";
    //网课
    public static final String CLASS = "class";
    //视频
    public static final String VIDEO = "video";
    //案例
    public static final String MARKTYPECASE = "typecase";
    //技术
    public static final String MARKTYPESKILL = "typeskill";
    //综合
    public static final String MARKTYPECOLLORT = "typecoloct";
    //文章标识
    public static final String MARKTYPEORDER = "typeorder";
    //需要的权限
    public static final String[] Persmission = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE
    };
    //微信支付
    public static final int PAYTYPE_WEIXIN = 2;
    //支付包
    public static final int PAYTYPE_ZFB = 3;
    //余额
    public static final int PAYTYPE_YUER = 1;
    //兑换码
    public static final int PAYTYPE_DUHAUN = 5;
    //微信id
    public static final String WEIXINVID = "wx0c71e64b9e151c84";
    //取消订单
    public static final String CANCELORDER = "cancel";
    //删除订单
    public static final String DELETEORDER = "delete";
    //全部订单
    public static final String ORDERALL = "0";
    //未完成
    public static String ORDERAOB = "-1";
    //待付款
    public static String ORDERPAY = "10";
    //代发货
    public static final String ORDERACOM = "11";
    //代收货
    public static final String ORDERAGOODS = "12";
    //友盟key
    public static final String YOUMENGKID = "5b067baaf43e483f20000093";
    //qq id
    public static final String QQAPP_ID = "1106864981";
    //qq key
    public static final String QQAPP_KEY = "rthjDUujg74JKfsq";
    //WEIBO key
    public static final String WEI_KEY = "1739335166";
    //WEIBO Servcie
    public static final String WEIKEY_SECRET = "8efea2ca3decb2c4173ae44b9942287e";
    //APP_SECRET
    public static final String WEIXINAPP_SECRET = "19a5f96dc807032fcb7f9d2a289ec0a1";
    //协议网址
    public static final String AGREEMENT = "http://www.xuechuanedu.cn/agreement-policy/fuwuxieyi.html";
    //wifi
    public static final String WIFI = "wifi";
    //移动
    public static final String MONET = "monet";
    //没网
    public static final String NONETWORK = "nonetwork";
    //        title,
//                articleid,
//                url,
//                shareurl,
//                isarticle
//
//
//        isarticle=1,文章
//        isarticle=0,资讯
    public static final String BUGLYAPPID = "20b66083e6";
    public static final boolean BUGLYAPPID_UP = false;
    public static final String BUGLYAPPIDKEY = "d959ecd6-9225-4689-afeb-0c8dff63fb3d";

    public static final String Boli = "保利版本2.3.3";
    /**
     * 章节练习
     */
    public static final int CHAPTEREXERCISE = 1;
    /**
     * 专项练习
     */
    public static final int TAGEXERCISE = 2;
    /**
     * 顺序练习
     */
    public static final int ORDEREXERCISE = 3;
    /**
     * 错题练习
     */
    public static final int ERROREXERCISE = 4;
    /**
     * 错题标签练习
     */
    public static final int ERROR_TAGEXERCISE = 5;
    /**
     * 收藏练习
     */
    public static final int FAVORITEEXERCISE = 6;
    /**
     * 收藏标签练习
     */
    public static final int FAVORITE_TAGEXERCISE = 7;
    /**
     * 章节练习
     */
    public static final int TAGERTIDONE = 1;

    /**
     * 无继续做题提示传入参数
     */
    public static final int OTHERZONE = 0;
    /**
     * 登录
     */
    public static final String LOGINTAG = "qrcodelogin.html";
    /**
     * 登录key
     */
    public static final String LOGINKEY = "qrcode";
    /**
     * 扫码
     */
    public static final String QRTAG = "vc.html";
    /**
     * 扫码key
     */
    public static final String QRKEY = "code";
    /**
     * 增值服务
     */
    public static final String ADDVALUEHTTP = "ec.html";


    public static final String UPDATATAYPE = "更新数据排序";
    public static final String UPDATATAYPEONE = "排序";

    public static final String SELECTTAG = "select_address";
    /**
     * 等待确认地址查看
     */
    public static final String WAITADDRESS = "address";
    /**
     * 登录聊天室/ppt直播/获取回放列表所需，请填写自己的appId和appSecret，否则无法登陆。
     * appId和appSecret可以在直播系统管理后台的用户信息页的API设置中用获取。
     */
    public static final String appId = "eoqehxk1u1";
    public static final String appSecret = "6b1a4778d89d44ef93951322684ac634";

    private String TESTLIVEID = "243230";
    private String TESTUSERID = "d740a56357";

    private String chatUserId = Build.SERIAL;

    public String getTESTLIVEID() {
        return TESTLIVEID;
    }

    public String getTESTUSERID() {
        return TESTUSERID;
    }

    public String getChatUserId() {
        return chatUserId;
    }

    /**
     * 观看时间（统计用户观看时长）
     */
    private int WatchTime = 0;

    public int getWatchTime() {
        return WatchTime;
    }

    public void setWatchTime(int watchTime) {
        WatchTime = watchTime;
    }

    /**
     * 数据库判断类型 添加类型
     */
    public static String GRADEADD = "add";
    /**
     * 数据库判断类型 覆盖原来的数据
     */
    public static String COVERDREADE = "cover";
    public static String ENCRYPTIONBEFORE = "{\n" +
            "    \"delete\": \"delete\",\n" +
            "    \"do\": \"no\"\n" +
            "}";
    public static String ENCRYPTIONAFTER = "{\n" +
            "    \"delete\": \"delete\",\n" +
            "    \"do\": \"do\"\n" +
            "}";
    public static final String zipkey = "xuechuan123";
    /**
     * 用户信息库名字
     */
    public static final String USER_INFOM_DATABASE_NAME = "message.db";
    /**
     * 用户信息库名字版本
     */
    public static final int USER_INFOM_DATABASE_VERISON = 1;
    /**
     * 用户信息库中用户表名
     */
    public static final String USER_INFOM_TABLE_USER = "messageoneone";
    /**
     * 用户信息库中做题记录表表名
     */
    public static final String USER_INFOM_TABLE_LOOK = "messageonetwo";
    /**
     * 用户信息库中视频观看记录表名
     */
    public static final String USER_INFOM_TABLE_VIDEOLOOK = "messageonethree";
    /**
     * 用户信息库中错题记录表
     */
    public static final String USER_INFOM_TABLE_ERROR = "messageonefour";

    /**
     * 用户题库中删除表名
     */
    public static final String USER_QUESTION_TABLE_DELETE = "messagetwoone";
    /**
     * 用户题库中问题表名
     */
    public static final String USER_QUESTION_TABLE_QUESTION = "messagetwotwo";
    /**
     * 用户题库中问题章节表名
     */
    public static final String USER_QEUSTION_TABLE_QUESTION_CHAPTER = "messagetwothree";
    /**
     * 用户题库中问题标签关系表名
     */
    public static final String USER_INFOM_TABLE_QUESTION_TAGRELATION = "messagetwofour";
    /**
     * 用户题库中问题标表名
     */
    public static final String USER_QUESTIONTABLE_TAG = "messagetwofive";
    /**
     * 用户关系表
     */
    public static final String USER_QUESTIONTABLE_EXAMR_ELATION = "messagetwosix";
    /**
     * 用户题库中问题标表名
     */
    public static final String USER_VIDEO_DOWN_TABLE = "messagethreeone";
    /**
     * 用户做题表
     */
    public static final String USER_QUESTION_DO_BANK_TABLE = "messagethreetwo";

    public static final String tag = "create table " + DataMessageVo.USER_QUESTIONTABLE_TAG +
            "(id integer primary key AUTOINCREMENT," +
            "tagname text," +
            "courseid integer ," +
            "questionnum integer," +
            "tagid integer);";
    public static final String questiontagrelagtion = "create table " + DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION +
            "(id integer primary key AUTOINCREMENT," +
            "questionid integer ," +
            "tagid integer);";
    public static final String questionChapter = "create table " + DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER +
            "(id integer primary key AUTOINCREMENT," +
            "questionchapter_id integer," +
            "courseid integer," +
            "chaptername text," +
            "mold  integer," +
            "questionnum integer ," +
            "sort integer ," +
            "parentid integer);";
    public static final String question = "create table " + DataMessageVo.USER_QUESTION_TABLE_QUESTION +//问题表
            "(id integer primary key AUTOINCREMENT," +
            "question_id integer," +
            "question BLOB," +
            "questionimg text," +
            "isreadcom integer," +
            "parent_id integer," +
            "questiontype integer," +
            "option_a TEXT," +
            "option_b TEXT," +
            "option_c TEXT," +
            "option_d TEXT," +
            "option_e TEXT," +
            "option_f TEXT," +
            "option_g TEXT," +
            "option_h TEXT," +
            "choice_answer TEXT," +
            "explain BLOB," +
            "explainimg BLOB," +
            "chapter_id integer," +
            "question_mold integer," +
            "sort integer," +
            "courseid integer," +
            "keywords BLOB," +
            "right_rate DOUBLE," +
            "difficulty integer," +
            "ext_int1 integer," +
            "ext_int2 integer," +
            "ext_double1 DOUBLE," +
            "ext_double2 DOUBLE," +
            "ext_string1 text," +
            "ext_string2 text," +
            "score DOUBLE);";
    public static final String delete = "create table " + DataMessageVo.USER_QUESTION_TABLE_DELETE +//删除表
            "( id integer primary key AUTOINCREMENT," +
            "delete_id  integer," +
            "type text);";
    public static final String userinfom = "create table " + DataMessageVo.USER_INFOM_TABLE_USER + "(" +
            "id integer primary key AUTOINCREMENT," +//id 自动增值
            "saffid integer ," + //原数据id
            "copy integer ," + //是否拷贝(1为拷贝，0没有拷贝)
            "userinfomvo text," + //用户信息
            "moid text," + //用户标识
            "skillbook text," + //用户是否购买技术实务
            "colligatebook text," + //用户是否购买综合案例
            "casebook text," + //用户是否购买案例分析
            "showDayOrNight text," + //用户选中的观看模式
            "userNextGo text," + //用户是否跳转到下一页
            "token text," + //用户token
            "userphone text," + //用户的手机号
            "deletenumber text," + //用户删除次数
            "fontsize text," + //用户删除次数
            "username text," +
            " questionversion integer ," +
            "bankversion integer ," +
            "bankversionname text" +
            ")"; //用户的密码
    public static final String doBank = "create table " + DataMessageVo.USER_QUESTION_DO_BANK_TABLE +//用户做题表（临时用）
            "(id integer primary key autoincrement," +
            "question_id integer," +
            "isdo integer," +
            "questiontype integer," +
            "selectA integer," +
            "selectB integer," +
            "selectC integer," +
            "selectD integer," +
            "selectE integer," +
            "isright integer );";
    public static final String look = "create table " + DataMessageVo.USER_INFOM_TABLE_LOOK +//做题记录表
            "(id integer primary key AUTOINCREMENT," +
            "chapterid integer," +//章节di
            "textid integer," +//题id
            "kid integer," +//科程id
            "userid text," +//用户id
            "rightnumber text," + //第几题
            "rightallnumber text);";//当前总体数
    public static final String videolook = "create table " + DataMessageVo.USER_INFOM_TABLE_VIDEOLOOK +//观看记录表
            "(id integer primary key," +
            "chapterid integer," +//章节di
            "kname text," +//科程id
            "userid varchar," +//用户id
            "rightnumber text," + //第几题
            "rightallnumber text);";//当前总体数
    public static final String error = "create table " + DataMessageVo.USER_INFOM_TABLE_ERROR +
            "(id integer primary key AUTOINCREMENT," +
            "chapterid integer," +//当前题干id
            "kname text," +//科目id
            "rightnumber text," +//(第几次正确)
            "userid varchar," +//用户id
            "rightallnumber text);";//用户设置总对数

    public static final String questionexamrelation = "create table " + USER_QUESTIONTABLE_EXAMR_ELATION +
            "(id integer primary key AUTOINCREMENT," +
            "examid integer," +
            "questionid integer" +
            ");";
    public static final String t_delete = "t_deldate";
    public static final String t_question = "t_question";
    public static final String t_questionchapter = "t_questionchapter";
    public static final String t_questiontagrelation = "t_questiontagrelation";
    public static final String t_tag = "t_tag";
    public static final String t_questionexamrelation = "t_questionexamrelation";
    /**
     * 技术实务购买
     */
    public static final String BUYSKILL = "zhishizengjianNanduskill.159";
    /**
     * 综合能力
     */
    public static final String BUYCOL = "zhishizengjianNanducol.753";
    /**
     * 案例分析
     */
    public static final String BUYCASE = "zhishizengjianNanducase.486";
    public static final String NOBUY = "NO";

    /**
     * 技术实务购买
     */
    private String SkillBuy;
    /**
     * 综合购买
     */
    private String CollorBuy;
    /**
     * 案例购买
     */
    private String CaseBuy;

    public String getSkillBuy() {
        if (StringUtil.isEmpty(SkillBuy)) {
            return EncryptionUtil.getInstance().getUserBuy(BUYSKILL);
        }
        return SkillBuy;
    }

    public void setSkillBuy(String skillBuy) {
        SkillBuy = skillBuy;
    }

    public String getCollorBuy() {
        if (StringUtil.isEmpty(CollorBuy)) {
            return EncryptionUtil.getInstance().getUserBuy(BUYCOL);
        }
        return CollorBuy;
    }

    public void setCollorBuy(String collorBuy) {
        CollorBuy = collorBuy;
    }

    public String getCaseBuy() {
        if (StringUtil.isEmpty(CaseBuy)) {
            return EncryptionUtil.getInstance().getUserBuy(BUYCASE);
        }
        return CaseBuy;
    }

    public void setCaseBuy(String caseBuy) {
        CaseBuy = caseBuy;
    }

    //白天模式 (一次删除)
    public static final String PATTERN_ONE = "1";
    //夜间模式 （三次删除）
    public static final String PATTERN_TWO = "2";
    //护眼模式 （五次删除）
    public static final String PATTERN_THREE = "3";
    //自动下一题 （不删除）
    public static final String PATTERN_FOUR = "4";
    //字体大小
    public static final String FONT_ONE="1";
    public static final String FONT_TWO="2";
    public static final String FONT_THREE="3";
    public static final String FONT_FOUR="4";
    public static final String FONT_FIVE="5";

    public static final String COURESID_SKILL="1";
    public static final String COURESID_SYNTHESIZE="2";
    public static final String COURESID_CASE="3";
}
