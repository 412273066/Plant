package com.jlk.plant.app;

/**
 * Created by test on 2016/2/23.
 */
public class AppInterface {

    /**
     * 服务器ip地址
     */
    public static final String SERVER_IMG_URL = "http://192.168.3.67";
    /**
     * 服务器接口地址
     */
    public static final String SERVER_URL = SERVER_IMG_URL+"/plant_server/app/";
    /**
     * 百度识图接口
     */
    public static final String BAIDUSHITU = "http://image.baidu.com/pictureup/uploadshitu";
    /**
     * 广告栏借口
     */
    public static final String GETBANNERLIST = SERVER_URL + "banner/";
    /**
     * 植物分类借口
     */
    public static final String GETCATEGORYLIST = SERVER_URL + "category/";
    /**
     * 植物列表接口
     */
    public static final String GETPLANTLIST = SERVER_URL + "plant/";
    /**
     * 文章分类接口
     */
    public static final String GETARTICLTTYPELIST = SERVER_URL + "articleType/";
    /**
     * 文章列表接口
     */
    public static final String GETARTICLTLIST = SERVER_URL + "articleList/";
    /**
     * 关键字搜索接口
     */
    public static final String SEARCHPLANT = SERVER_URL + "searchPlant/";
    /**
     * 注册接口
     */
    public static final String REGISTER = SERVER_URL + "user/register";
    /**
     * 登录接口
     */
    public static final String LOGIN = SERVER_URL + "user/login";
}
