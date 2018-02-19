package uow.csse.tv.gpe.config;

/**
 * Created by Vian on 2/10/2018.
 */

public class Const {

    private static String hostname = "http://gpe.tabtu.top/";
//    private static String hostname = "http://192.168.0.7:8080/";

    public static String PAGE = "page=";
    public static String ID = "id=";

    public static String getsportlist = hostname + "sport";
    public static String getvenuelist = hostname + "venue?" + PAGE + "0";
    public static String getvenuenewslist = hostname + "vnews?";
    public static String getclublist = hostname + "club?" + PAGE + "0";
    public static String getschoollist = hostname + "school?" + PAGE + "0";
    public static String getuserlist = hostname + "user";
    public static String loginlgnm = hostname + "lg/nm?";
}
