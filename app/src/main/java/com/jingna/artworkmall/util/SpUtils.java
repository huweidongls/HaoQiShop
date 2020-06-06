package com.jingna.artworkmall.util;

import android.content.Context;

import com.vise.xsnow.cache.SpCache;

/**
 * Created by Administrator on 2018/10/26.
 */

public class SpUtils {

    private static SpCache spCache;
    public static String USER_ID = "user_id";
    public static String PHONENUM = "phonenum";
    public static String TOKEN = "token";
    public static String SEARCH_HISTORY = "search_history";
    public static String REAL = "real";
    public static String LOGIN_URL = "login_url";
    public static String LANYA_TIME = "lanya_time";

    public static void setLanyaTime(Context context, boolean time){
        spCache = new SpCache(context, "user_info");
        spCache.put(LANYA_TIME, time);
    }

    public static boolean getLanyaTime(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(LANYA_TIME, false);
    }

    public static void setLoginUrl(Context context, String loginUrl){
        spCache = new SpCache(context, "user_info");
        spCache.put(LOGIN_URL, loginUrl);
    }

    public static String getLoginUrl(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(LOGIN_URL, "");
    }

    public static void setReal(Context context, String real){
        spCache = new SpCache(context, "user_info");
        spCache.put(REAL, real);
    }

    public static String getReal(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(REAL, "");
    }

    public static void setSearchHistory(Context context, String token){
        spCache = new SpCache(context, "user_info");
        spCache.put(SEARCH_HISTORY, token);
    }

    public static String getSearchHistory(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(SEARCH_HISTORY, "");
    }

    public static void setToken(Context context, String token){
        spCache = new SpCache(context, "user_info");
        spCache.put(TOKEN, token);
    }

    public static String getToken(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(TOKEN, "0");
    }

    public static void setPhoneNum(Context context, String phonenum){
        spCache = new SpCache(context, "user_info");
        spCache.put(PHONENUM, phonenum);
    }

    public static String getPhoneNum(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(PHONENUM, "0");
    }

    public static void setUserId(Context context, String userid){
        spCache = new SpCache(context, "user_info");
        spCache.put(USER_ID, userid);
    }

    public static String getUserId(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(USER_ID, "0");
    }

    public static void clear(Context context){
        spCache = new SpCache(context, "user_info");
        spCache.clear();
    }

}
