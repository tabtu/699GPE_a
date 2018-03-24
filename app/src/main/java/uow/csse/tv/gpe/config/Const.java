package uow.csse.tv.gpe.config;

/**
 * Created by Vian on 2/10/2018.
 */

public class Const {

    private static String hostname = "http://gpe.tabtu.top/";
//    private static String hostname = "http://127.0.0.1:8080/";

    public static String PAGE = "page=";
    public static String ID = "id=";

    public static String getallusers = hostname + "us/all?";

    public static String getsportlist = hostname + "sport";
    public static String getvenuelist = hostname + "venue?city=";
    public static String getvenuenewslist = hostname + "vnews?";
    public static String getclublist = hostname + "club?city=";
    public static String getschoollist = hostname + "club?city=";
    public static String loginlgtl = hostname + "lg/tl?";
    public static String registtl = hostname + "reg/tl?";
    public static String getupnewslist = hostname + "home?city=";
    public static String getdownnewslist = hostname + "news?city=";
    public static String getcitylist = hostname + "city";
    public static String getinboxmsg = hostname + "us/in?";
    public static String getoutboxmsg = hostname + "us/out?";
    public static String postmsg = hostname + "us/sd?";
    public static String getmsgread = hostname + "us/chk?";
    public static String postdeletemsg = hostname + "us/del?";
    public static String getvenueactivitylist = hostname + "act/venue?";
    public static String getclubactivitylist = hostname + "act/club?";
    public static String gethomeactivitylist = hostname + "act/home?";
    public static String getuserlist = hostname + "lg/gusr?";
    public static String postsearchuser = hostname + "sh/user?";
    public static String postsearchvenue = hostname + "sh/venue?";
    public static String postsearchactivity = hostname + "sh/activity?";
    public static String postsearchclub = hostname + "sh/club?";
}
