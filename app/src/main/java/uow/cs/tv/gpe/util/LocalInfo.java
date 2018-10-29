//package uow.csse.tv.gpe.util;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import android.content.Context;
//import android.telephony.TelephonyManager;
//
//public class LocalInfo {
//
//	//��ȡmeid
//	public static String getMeid(Context cx) {
//		TelephonyManager tm = (TelephonyManager) cx.getSystemService(Context.TELEPHONY_SERVICE);//
//		String result = tm.getDeviceId();
//		if (result == "") {
//			return "Unknow";
//		} else {
//			return result;
//		}
//	}
//
//	// ��ȡimsi
//	public static String getImsi(Context cx) {
//		TelephonyManager tm = (TelephonyManager) cx.getSystemService(Context.TELEPHONY_SERVICE);//
//		String result = tm.getSubscriberId();
//		if (result == "") {
//			return "Unknow";
//		} else {
//			return result;
//		}
//	}
//
//	// ��ȡ�ֻ��ͺ�
//	public static String getType()
//	{
//		String result = android.os.Build.MODEL + "";
//		if (result == "") {
//			return "Unknow";
//		} else {
//			Pattern p = Pattern.compile("\\s*|\t|\r|\n");//ȥ���ո�ˮƽ�Ʊ�������С��س�
//			Matcher m = p.matcher(result);
//			result = m.replaceAll("");
//			return result;
//		}
//	}
//}
