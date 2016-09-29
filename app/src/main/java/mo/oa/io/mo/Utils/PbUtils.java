package mo.oa.io.mo.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类主要存储用户各项信息以及设备信息
 * 
 * @author 张铭心
 * 
 */
public class PbUtils {
	public static final String HEADIMAGE = "headImage";
	//消息列表版本
	public static final String MSGVERSION = "msgversion";
	//公告消息版本
	public static final String GGVERSION = "ggversion";
	// 设备信息
	public static final String PHONEMESSAGE = "phoneMessage"; // 获取手机信息所用的KEY
	public static final String IMEI = "imei"; // 获取手机信息IMEI码所用的KEY
	public static final String MODEL = "model"; // 获取手机型号所用的KEY
	public static final String BRAND = "brand"; // 获取手机品牌所用的KEY
	public static final String VERSIONCODE = "versioncode"; // 应用版本号
	public static final String VERSIONNAME = "versionname"; // 应用版本号
	public static final String NUMBER = "number"; // 获取手机号码所用的KEY
	public static final String DeviceFLAG = "flag"; // 登陆信息中登陆标示符所用的KEY

	public static final String LOGINMESSAGE = "loginMessage";
	//头像信息(用于三级缓存)
	public static final String HEADICONINFO = "headiconinfo";
	//fileid
	public static final String FILEID = "fileid";
	public static final String FLAG = "flag"; // 登陆信息中登陆标示符所用的KEY
	public static final String TXLICON="txlIcon";// 联系人默认头像颜色
	public static final String TXLCOLLECT="txlCollect";// 联系人收藏
	// 账户是否被锁定
	public static final String LOCK = "lock";
	// 用户名
	public static final String USERNAME = "username"; // 登陆信息中姓名所用的KEY
	// 账号
	//已读db
	public static final String DBISREAD = "dbisread";
	//已读dbversion
	public static final int DBVersion = 1;
	//已读id
	public static final String ISREADID = "isreadid";
	
	public static final String USERACCOUNT = "useraccount"; // 登陆信息中账号
	public static final String STATUS = "status"; // 是否在岗
	public static final String LASTLOGIN = "lastlogin"; // 最后一次登录时间
	public static final String GWUSERID = "gwuserid"; // 公文系统userid
	public static final String USERTYPE = "usertype"; // 账户类型
	public static final String MOBILEPHONE = "mobilephone"; // 手机号码
	public static final String EMAIL = "email"; // 电子邮件
	public static final String XYBZ = "xybz"; // 选用标志
	public static final String SWJGDM = "swjgdm"; // 税务机关代码
	public static final String USERID = "userid"; // 税务机关代码
	public static final String GestureFlag = "gestureflag";// 是否首次设置手势密码
	public static final String ACTORID = "actorid"; // actorid
	public static final String ORGALIAS = "orgalias"; // OrgAlias

	public static final String TYPE = "type"; // �û����0������ҵ�͸���1������Ȼ��
	public static final String NSRLSH = "nsrlsh"; // ��½��Ϣ���������õ�KEY
	public static final String NSRSBH = "nsrsbh"; // ��½��Ϣ����˰��ʶ���
	public static final String ZIRANREN = "03"; // ע��������Ȼ��03
	public static final String GETIJINGYING = "01"; // ע�����͸��徭Ӫ01
	public static final String QIYEDANWEI = "02"; // ע��������ҵ��λ02

	public static final String TAX = "TAX"; // ȫ����ʹ�õĳ���

	public static final int VPN_MSG_STATUS_UPDATE = 100; // VPN״̬֪ͨ��Ϣ��
	public static final String TAG = "VPNMESSAGE"; // VPN��ʾ�����Ϣ
	public static final int QUERY_VPN_MSG_STATUS_UPDATE = 101; // VPN״̬֪ͨ��Ϣ��

	public static final int MSG_GET_UKEY_SERIALNO_SUCCESS = 0; // ����VPN��ȡ���к�---�ɹ�
	public static final int MSG_GET_UKEY_SERIALNO_FAIL = 1; // ����VPN��ȡ���к�---ʧ��
	public static final int MSG_GET_UKEY_LOGINSIGN_SUCCESS = 2; // ����VPN��ȡ��½ǩ�������ǰ��ַ�������---�ɹ�
	public static final int MSG_GET_UKEY_LOGINSIGN_FAIL = 3; // ����VPN��ȡ��½ǩ�������ǰ��ַ�������---ʧ��
	public static final int MSG_GET_LONGIN_SUCCESS = 4; // ����VPN����VPN---�ɹ�
	public static final int MSG_GET_LONGIN_FAIL = 5; // ����VPN����VPN---ʧ��
	public static final int MSG_GET_UKEY_TRANSSIGN_SUCCESS = 6;
	public static final int MSG_GET_UKEY_TRANSSIGN_FAIL = 7;
	public static final int MSG_GET_SERVICELIST_SUCCESS = 8; // ����VPN��ȡ���ʶ˿��б�---�ɹ�
	public static final int MSG_GET_SERVICELIST_FAIL = 9; // ����VPN��ȡ���ʶ˿��б�---ʧ��
	public static final int MSG_PROXY_SUCCESS = 10; // ����VPNӦ�ô���ӿڴ���---�ɹ�
	public static final int MSG_PROXY_FAIL = 11; // ����VPNӦ�ô���ӿڴ���---ʧ��

	public static final String AUDIOTAG = "AUDIOVPNMESSAGE"; // ��Ƶ��VPN��ʾ�����Ϣ

	public static final String LOCALAPKNAME = "hbdsonline_android.apk"; // ���ص��°�Ӧ�����ֻ��˴������
	public static final String APKNAME = "Tax"; // ����˴���°�Ӧ�õ�����

	private static PbUtils pbUtils;
	public static synchronized PbUtils getPbUtils() {
		if(pbUtils==null){
			pbUtils = new PbUtils();
		}
		return pbUtils;
	}


	static public void setPhoneMessage(String flag, String imei, String model,
									   String brand, String number, Context activity) {
		SharedPreferences phoneMessage = activity.getSharedPreferences(
				PbUtils.PHONEMESSAGE, 0);
		SharedPreferences.Editor editor = phoneMessage.edit();
		editor.putString(PbUtils.DeviceFLAG, flag);
		editor.putString(PbUtils.IMEI, imei);
		editor.putString(PbUtils.MODEL, model);
		editor.putString(PbUtils.BRAND, brand);
		editor.putString(PbUtils.NUMBER, number);
		editor.commit();
	}
	
	static public void setHeadImage(String path, Context activity) {
		SharedPreferences headimage = activity.getSharedPreferences(
				PbUtils.HEADIMAGE, 0);
		SharedPreferences.Editor editor = headimage.edit();
		editor.putString(PbUtils.HEADIMAGE, path);
		editor.commit();
	}
	
	static public String getHeadImage(Context activity) {
		SharedPreferences headimage = activity.getSharedPreferences(
				PbUtils.HEADIMAGE, 0);
		return headimage.getString(PbUtils.HEADIMAGE, "");
	}

	static public void setPhoneMessage(Map<String, String> map, Context activity) {
		SharedPreferences phoneMessage = activity.getSharedPreferences(
				PbUtils.PHONEMESSAGE, 0);
		SharedPreferences.Editor editor = phoneMessage.edit();
		// editor.putString(PbUtils.DeviceFLAG, flag);
		editor.putString(PbUtils.IMEI, map.get(PbUtils.IMEI));
		editor.putString(PbUtils.MODEL, map.get(PbUtils.MODEL));
		editor.putString(PbUtils.BRAND, map.get(PbUtils.BRAND));
		editor.putString(PbUtils.NUMBER, map.get(PbUtils.NUMBER));
		editor.commit();
	}

	static public Map<String, String> getPhoneMessage(Context activity) {
		SharedPreferences phoneMessage = activity.getSharedPreferences(
				PbUtils.PHONEMESSAGE, 0);
		Map<String, String> map = new HashMap<String, String>();
		map.put(PbUtils.IMEI, phoneMessage.getString(PbUtils.IMEI, ""));
		map.put(PbUtils.MODEL, phoneMessage.getString(PbUtils.MODEL, ""));
		map.put(PbUtils.BRAND, phoneMessage.getString(PbUtils.BRAND, ""));
		map.put(PbUtils.NUMBER, phoneMessage.getString(PbUtils.NUMBER, ""));
		return map;
	}
	//获取用户头像信息(非个人中心数据)
	static public String getHeadIconMessage(String userid,Context activity) {
		SharedPreferences phoneMessage = activity.getSharedPreferences(PbUtils.HEADICONINFO, 0);
		String fileid = phoneMessage.getString(userid, "");
		return fileid;
	}
	
	static public void setHeadIconMessag(String userid,String fileid, Context activity) {
		SharedPreferences phoneMessage = activity.getSharedPreferences(
				PbUtils.HEADICONINFO, 0);
		SharedPreferences.Editor editor = phoneMessage.edit();
		editor.putString(userid,fileid);
		editor.commit();
	}
	
	// static public Map<String,String> getPhoneMessag(Context activity) {
	// SharedPreferences phoneMessage = activity.getSharedPreferences(
	// PbUtils.PHONEMESSAGE, 0);
	// Map<String,String> map = new HashMap<String,String>();
	// map.put(PbUtils.IMEI, phoneMessage.getString(PbUtils.IMEI, ""));
	// map.put(PbUtils.MODEL, phoneMessage.getString(PbUtils.MODEL, ""));
	// map.put(PbUtils.BRAND, phoneMessage.getString(PbUtils.BRAND, ""));
	// map.put(PbUtils.NUMBER, phoneMessage.getString(PbUtils.NUMBER, ""));
	// return map;
	// }
	public static void setTXLIconColor(String name,int color,Context activity){
		SharedPreferences phoneMessage = activity.getSharedPreferences(
				PbUtils.TXLICON, 0);
		SharedPreferences.Editor editor = phoneMessage.edit();
		editor.putString(name, String.valueOf(color));
		editor.commit();
	}
	static public int getTXLIconColor(String name,Context activity) {
		SharedPreferences phoneMessage = activity.getSharedPreferences(
				PbUtils.TXLICON, 0);
		return Integer.valueOf(phoneMessage.getString(name, "0"));
	}
	
	public static void setTXLCollect(String name_number,int collect,Context activity){
		SharedPreferences phoneMessage = activity.getSharedPreferences(
				PbUtils.TXLCOLLECT, 0);
		SharedPreferences.Editor editor = phoneMessage.edit();
		editor.putString(name_number, String.valueOf(collect));
		editor.commit();
	}
	static public String getTXLCollect(String name,Context activity) {
		SharedPreferences phoneMessage = activity.getSharedPreferences(
				PbUtils.TXLCOLLECT, 0);
		Log.e("phoneMessage", phoneMessage.getString(name, "0"));
		return phoneMessage.getString(name, "0");
	}
	
	public static void Prompt(Context context, String message) {
		Toast t = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		t.show();
	}

	public static String add(String... s1) {
		BigDecimal hj = new BigDecimal("0");
		for (String m : s1) {
			BigDecimal temp = new BigDecimal(m);
			hj = hj.add(temp);
		}
		double f1 = hj.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(f1);
	}

	public static String mul(String... s1) {
		BigDecimal hj = new BigDecimal("1");
		for (String m : s1) {
			BigDecimal temp = new BigDecimal(m);
			hj = hj.multiply(temp);
		}
		double f1 = hj.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(f1);
	}

	public String formatNum(String s1, int b) {
		BigDecimal hj = new BigDecimal(s1);
		double f1 = hj.setScale(b, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(f1);
	}

	static public void setLoginMessage(Map map, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		// editor.putString(PbUtils.SWZCLSH, (String) map.get(PbUtils.SWZCLSH));
		// 手势密码
		if (loginMessage.getString(PbUtils.GestureFlag, "true").equals("")) {
			editor.putString(PbUtils.GestureFlag,
					(String) map.get(PbUtils.GestureFlag));
		}
		// actorid
		editor.putString(PbUtils.ACTORID, (String) map.get(PbUtils.ACTORID));
		// orgalias
		editor.putString(PbUtils.ORGALIAS, (String) map.get(PbUtils.ORGALIAS));
		// 角色标志位
		editor.putString(PbUtils.LOCK, (String) map.get(PbUtils.LOCK));
		// 已登录标志位
		editor.putString(PbUtils.FLAG, (String) map.get(PbUtils.FLAG));
		// 用户姓名
		editor.putString(PbUtils.USERNAME, (String) map.get(PbUtils.USERNAME));
		// 用户账号
		editor.putString(PbUtils.USERACCOUNT,
				(String) map.get(PbUtils.USERACCOUNT));
		// 用户id
		editor.putString(PbUtils.USERID, (String) map.get(PbUtils.USERID));
		// 税务机关代码
		editor.putString(PbUtils.SWJGDM, (String) map.get(PbUtils.SWJGDM));
		// 状态
		editor.putString(PbUtils.STATUS, (String) map.get(PbUtils.STATUS));
		// 用户类型
		editor.putString(PbUtils.USERTYPE, (String) map.get(PbUtils.USERTYPE));
		// 用户手机
		editor.putString(PbUtils.MOBILEPHONE,
				(String) map.get(PbUtils.MOBILEPHONE));
		// 用户邮箱
		editor.putString(PbUtils.EMAIL, (String) map.get(PbUtils.EMAIL));
		// 选用标志
		editor.putString(PbUtils.XYBZ, (String) map.get(PbUtils.XYBZ));
		// 最后一次登录时间
		editor.putString(PbUtils.LASTLOGIN, (String) map.get(PbUtils.LASTLOGIN));
		//公文系统userid
		editor.putString(PbUtils.GWUSERID, (String) map.get(PbUtils.GWUSERID));
		editor.commit();
	}

	static public void resetLoginMessage(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString(PbUtils.FLAG, "false");
		editor.putString(PbUtils.USERNAME, "");
		editor.putString(PbUtils.USERACCOUNT, "");
		editor.putString(PbUtils.USERID, "");
		editor.putString(PbUtils.SWJGDM, "");
		editor.putString(PbUtils.LOCK, "");
		editor.putString(PbUtils.STATUS, "");
		editor.putString(PbUtils.USERTYPE, "");
		editor.putString(PbUtils.MOBILEPHONE, "");
		editor.putString(PbUtils.EMAIL, "");
		editor.putString(PbUtils.XYBZ, "");
		editor.putString(PbUtils.LASTLOGIN, "");
		editor.putString(PbUtils.ACTORID, "");
		editor.putString(PbUtils.ORGALIAS, "");
		editor.commit();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static public Map getLoginMessage(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				LOGINMESSAGE, 0);
		Map map = new HashMap();
		map.put(PbUtils.GestureFlag,
				loginMessage.getString(PbUtils.GestureFlag, "true"));
		map.put(PbUtils.FLAG, loginMessage.getString(PbUtils.FLAG, "false"));
		map.put(PbUtils.USERNAME, loginMessage.getString(PbUtils.USERNAME, ""));
		map.put(PbUtils.USERACCOUNT,
				loginMessage.getString(PbUtils.USERACCOUNT, ""));
		map.put(PbUtils.USERID, loginMessage.getString(PbUtils.USERID, ""));
		map.put(PbUtils.SWJGDM, loginMessage.getString(PbUtils.SWJGDM, ""));
		map.put(PbUtils.LOCK, loginMessage.getString(PbUtils.LOCK, ""));
		map.put(PbUtils.STATUS, loginMessage.getString(PbUtils.STATUS, ""));
		map.put(PbUtils.USERTYPE, loginMessage.getString(PbUtils.USERTYPE, ""));
		map.put(PbUtils.MOBILEPHONE,
				loginMessage.getString(PbUtils.MOBILEPHONE, ""));
		map.put(PbUtils.EMAIL, loginMessage.getString(PbUtils.EMAIL, ""));
		map.put(PbUtils.XYBZ, loginMessage.getString(PbUtils.XYBZ, ""));
		map.put(PbUtils.LASTLOGIN,
				loginMessage.getString(PbUtils.LASTLOGIN, ""));
		map.put(PbUtils.ACTORID, loginMessage.getString(PbUtils.ACTORID, ""));
		map.put(PbUtils.ORGALIAS, loginMessage.getString(PbUtils.ORGALIAS, ""));
		map.put(PbUtils.GWUSERID, loginMessage.getString(PbUtils.GWUSERID, ""));
		return map;
	}

	static public String getLoginMessageFLAG(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);

		return loginMessage.getString(PbUtils.FLAG, "");
	}

	static public String getLoginMessageSWJGDM(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		return loginMessage.getString(PbUtils.SWJGDM, "");
	}

	static public String getLoginMessageActorID(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);

		return loginMessage.getString(PbUtils.ACTORID, "");
	}
	
	static public String getLoginMessageUserID(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);

		return loginMessage.getString(PbUtils.USERID, "");
	}
	
	static public String getLoginMessageORGALIAS(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);

		return loginMessage.getString(PbUtils.ORGALIAS, "");
	}

	static public String getLoginMessageUSERNAME(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		return loginMessage.getString(PbUtils.USERNAME, "");
	}

	static public void setLoginMessageUSERNAME(String username, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString(PbUtils.USERNAME, username);
		editor.commit();
	}

	static public String getLoginMessageACCOUNT(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		return loginMessage.getString(PbUtils.USERACCOUNT, "");
	}

	static public void setLoginMessageACCOUNT(String account, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString(PbUtils.USERACCOUNT, account);
		editor.commit();
	}

	static public String getUserType(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		return loginMessage.getString(PbUtils.TYPE, "");
	}

	static public void setUserType(String type, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString(PbUtils.TYPE, type);
		editor.commit();
	}

	// ������֤��ˢ�±�־ 0���� 1�Ѿ����͹�
	static public String getVerifyFlag(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		return loginMessage.getString("VerifyFlag", "");
	}

	static public void setVerifyFlag(String flag, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("VerifyFlag", flag);
		editor.commit();
	}

	public String[] getProvince() {
		return new String[] { "����ʡ" };
	}

	public String[] getCity(String province) {
		return new String[] { "�人��" };
	}

	public ProgressDialog getProgressDialog(Activity activity, String title,
			String content) {
		ProgressDialog pd;
		pd = ProgressDialog.show(activity, title, content, false, true);
		return pd;
	}

	public List getShenBao(JSONObject json) {
		List list = null;
		JSONArray jsonItems;
		try {
			list = new ArrayList();
			jsonItems = json.getJSONArray("items");

			for (int i = 0; i < jsonItems.length(); i++) {
				JSONObject jsonResult = (JSONObject) jsonItems.get(i);
				String[] jsjy1 = ((String) jsonResult.get("jsyj")).split(":");
				Map map = new HashMap();
				map.put("name", jsjy1[0]);
				map.put("money", "��" + jsjy1[1]);
				map.put("date", (String) jsonResult.get("rq"));
				list.add(map);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// HRY @2015/10/12 ���SP ������
	// ��������
	static public final String SP_GESTURE_PWD = "GESTUREPWD";
	static public final String KEY_SET_PWD = "SET_FLAG";

	static public boolean isGestruePWDSet(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SP_GESTURE_PWD,
				Context.MODE_PRIVATE);
		return sp.getBoolean(KEY_SET_PWD, false);
	}

	static public void setGestruePWD(Context context, boolean flag) {
		SharedPreferences sp = context.getSharedPreferences(SP_GESTURE_PWD,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(KEY_SET_PWD, flag).commit();
	}

	static public void setGestureFLAG(String flag, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString(PbUtils.GestureFlag, flag);
		editor.commit();
	}

	// ����ҳ
	static public final String SP_GUIDE_PAGE = "GUIDE";
	static public final String KEY_SET_GUIDE = "GUIDE_SET_FLAG";

	static public boolean isGuideNeed(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SP_GUIDE_PAGE,
				Context.MODE_PRIVATE);
		return sp.getBoolean(KEY_SET_GUIDE, false);
	}

	static public void setGuide(Context context, boolean flag) {
		SharedPreferences sp = context.getSharedPreferences(SP_GUIDE_PAGE,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(KEY_SET_GUIDE, flag).commit();
	}

	static public void setServerIp(String ip, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("IP", ip);
		editor.commit();
	}

	static public void setIp1(String ip, Context activity) { // ��������������IP��ַ
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("REMOTEIP", ip);
		editor.commit();
	}

	static public String getIp1(Context activity) { // ��ȡ����������IP��ַ
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		// String remoteIp = loginMessage.getString("REMOTEIP",
		// "http://221.232.132.162/webroot");
		String remoteIp = loginMessage.getString("REMOTEIP",
				"https://www.ehbds.gov.cn/webroot");
		return remoteIp;
	}

	/**
	 * 张铭心 获取访问root路径,endip为相关接口后缀地址
	 * 
	 * @param endIP
	 * @return
	 */
	static public String getNowIp(String endIP) {
		String IP = "http://192.168.1.104:8080/ydhl/OAService.do?URL=odps/services/"
				+ endIP;
		return IP;
	}

	static public void setIp2(String ip, Context activity) { // ����TF��VPN������IP��ַ
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("VPNIP", ip);
		editor.commit();
	}

	static public String getIp2(Context activity) { // ��ȡTF��VPN������IP��ַ
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		String vpnIp = loginMessage.getString("VPNIP", "219.140.63.1");
		return vpnIp;
	}

	static public void setIp3(String ip, Context activity) { // ������Ƶ����VPN������IP��ַ
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("AUDIOVPNIP", ip);
		editor.commit();
	}

	static public String getIp3(Context activity) { // ��ȡ��Ƶ����VPN������IP��ַ
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		String audioVpnIp = loginMessage
				.getString("AUDIOVPNIP", "219.140.63.2");
		return audioVpnIp;
	}

	static public void setTempServerIp(String ip, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("TEMPIP", ip);
		editor.commit();
	}

	static public String getTempServerIp(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		String tempIp = loginMessage.getString("TEMPIP", "");
		return tempIp;
	}

	static public void setAudioVPNPort(String audioVPNPort, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("AUDIOVPNPORT", audioVPNPort);
		editor.commit();
	}

	static public void setAudioVPNFlag(boolean audioVPNFlag, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putBoolean("AUDIOVPN_FLAG", audioVPNFlag);
		editor.commit();
	}

	static public boolean getAudioVPNFlag(Context activity) {
		SharedPreferences serverInfo = activity.getSharedPreferences(
				"serverInfo", 0);
		boolean audioVPNFlag = serverInfo.getBoolean("AUDIOVPN_FLAG", false);
		return audioVPNFlag;
	}

	static public void setVPNFlag(boolean VPNFlag, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putBoolean("VPN_FLAG", VPNFlag);
		editor.commit();
	}

	static public boolean getVPNFlag(Context activity) {
		SharedPreferences serverInfo = activity.getSharedPreferences(
				"serverInfo", 0);
		boolean VPNFlag = serverInfo.getBoolean("VPN_FLAG", false);
		return VPNFlag;
	}

	static public String getAudioVPNPort(Context activity) {
		SharedPreferences serverInfo = activity.getSharedPreferences(
				"serverInfo", 0);
		String audioVPNPort = serverInfo.getString("AUDIOVPNPORT", "443");
		return audioVPNPort;
	}

	static public void setVPNUserName(String VPNUserName, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("VPNUSERNAME", VPNUserName);
		editor.commit();
	}

	static public String getVPNUserName(Context activity) {
		SharedPreferences serverInfo = activity.getSharedPreferences(
				"serverInfo", 0);
		String VPNUserName = serverInfo.getString("VPNUSERNAME", "");
		return VPNUserName;
	}

	static public void setVPNPassword(String VPNUserName, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("VPNPASSWORD", VPNUserName);
		editor.commit();
	}

	static public String getVPNPassword(Context activity) {
		SharedPreferences serverInfo = activity.getSharedPreferences(
				"serverInfo", 0);
		String VPNPassword = serverInfo.getString("VPNPASSWORD", "");
		return VPNPassword;
	}

	static public void setVPNIpv6(String VPNIpv6, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("VPNIPV6", VPNIpv6);
		editor.commit();
	}

	static public String getVPNIpv6(Context activity) {
		SharedPreferences serverInfo = activity.getSharedPreferences(
				"serverInfo", 0);
		String VPNIpv6 = serverInfo.getString("VPNIPV6",
				"240e:b5:8001:b:9410:1a1:0:5");
		return VPNIpv6;
	}

	static public void setVPNServerPort(String VPNPort, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("VPNSERVERPORT", VPNPort);
		editor.commit();
	}

	static public String getVPNServerPort(Context activity) {
		SharedPreferences serverInfo = activity.getSharedPreferences(
				"serverInfo", 0);
		String VPNServerPort = serverInfo.getString("VPNSERVERPORT", "443");
		return VPNServerPort;
	}

	static public void setVPNIntranetIp(String port, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("IP", "http://127.0.0.1:" + port + "/webroot");
		editor.commit();
	}

	static public void setAudioVPNIntranetIp(String port, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("IP", "http://127.0.0.1:" + port + "/webroot");
		editor.commit();
	}
	//写入消息版本
	static public void setMSGVERSION(String version, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				LOGINMESSAGE, 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString(MSGVERSION,  version);
		editor.commit();
	}
	//写入公告版本
	static public void setGGVERSION(String version, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				LOGINMESSAGE, 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString(GGVERSION,  version);
		editor.commit();
	}
	//获取消息版本
	static public String getMSGVERSION(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		return loginMessage.getString(PbUtils.MSGVERSION, "");
	}
	//获取公告版本
	static public String getGGVERSION(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.LOGINMESSAGE, 0);
		return loginMessage.getString(PbUtils.GGVERSION, "");
	}
	
	static public String getServerIp(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"serverInfo", 0);
		String ip = loginMessage.getString("IP", "");
		return ip;
	}

	public List getJiaoshuiFapiao(JSONObject json) {
		List list = null;
		JSONArray jsonItems;
		try {
			list = new ArrayList();
			jsonItems = json.getJSONArray("items");

			for (int i = 0; i < jsonItems.length(); i++) {
				JSONObject jsonResult = (JSONObject) jsonItems.get(i);
				Map map = new HashMap();
				map.put("money", "��" + (String) jsonResult.get("jsyj"));
				map.put("date", (String) jsonResult.get("rq"));
				list.add(map);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public String formateDate(String date) {
		String result = "";
		String[] a = date.split("-");
		if (a[1].length() == 1) {
			a[1] = "0" + a[1];
		}
		if (a[2].length() == 1) {
			a[2] = "0" + a[2];
		}
		result = a[0] + "-" + a[1] + "-" + a[2];
		return result;
	}

	public static String getTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date(time);
		String t1 = format.format(d1);
		return t1;
	}

	public static String getTime2() {
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy��MM��dd��HHʱmm��ss��");
		Date d1 = new Date(time);
		String t1 = format.format(d1);
		return t1;
	}

	public static Calendar getSkssqStart() {
		Calendar cal_1 = Calendar.getInstance();// ��ȡ��ǰ����
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// ����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ��
		return cal_1;
	}

	public static Calendar getSkssqEnd() {
		Calendar cal_1 = Calendar.getInstance();// ��ȡ��ǰ����
		cal_1.set(Calendar.DAY_OF_MONTH, 1);
		cal_1.add(Calendar.DAY_OF_MONTH, -1);
		return cal_1;
	}

	static public void setVersion(String version, Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"versionInfo", 0);
		SharedPreferences.Editor editor = loginMessage.edit();
		editor.putString("version", version);
		editor.commit();
	}

	static public String getVersion(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				"versionInfo", 0);
		String version = loginMessage.getString("version", "1.5.3");
		return version;
	}

	public List getChechuanshui(JSONObject json) {
		List list = null;
		JSONArray jsonItems;
		try {
			list = new ArrayList();
			jsonItems = json.getJSONArray("YDJCCLIST");

			for (int i = 0; i < jsonItems.length(); i++) {
				JSONObject jsonResult = (JSONObject) jsonItems.get(i);
				Map map = new HashMap();
				map.put("CCPZH", (String) jsonResult.get("CCPZH"));
				map.put("CCLX", (String) jsonResult.get("CCLX"));
				map.put("NYNSE", (String) jsonResult.get("NYNSE"));
				map.put("CHEC_LSH", (String) jsonResult.get("CHEC_LSH"));
				list.add(map);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// public LoginMessage explainLoginMessage(JSONObject json) {
	// LoginMessage loginMessage = new LoginMessage();
	// try {
	// if (json.getString("FLAG") != null) {
	// loginMessage.setFLAG(json.getString("FLAG"));
	// } else {
	// loginMessage.setFLAG("");
	// }
	// if ("success".equals(loginMessage.getFLAG())) {
	// if (json.getString("MESSAGE") != null) {
	// loginMessage.setMESSAGE(json.getString("MESSAGE"));
	// } else {
	// loginMessage.setMESSAGE("");
	// }
	// if (json.getString("NSRMC") != null) {
	// loginMessage.setNSRMC(json.getString("NSRMC"));
	// } else {
	// loginMessage.setNSRMC("");
	// }
	// if (json.getString("SWDM") != null) {
	// loginMessage.setSWDM(json.getString("SWDM"));
	// } else {
	// loginMessage.setSWDM("");
	// }
	// if (json.getString("NSRLSH") != null) {
	// loginMessage.setNSRLSH(json.getString("NSRLSH"));
	// } else {
	// loginMessage.setNSRLSH("");
	// }
	// }
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return loginMessage;
	// }


	// public FlagAndMessage getFlagAndMessage(JSONObject json) {
	//
	// FlagAndMessage fam = new FlagAndMessage();
	// try {
	// if (json.has("flag")) {
	// fam.setFlag(json.getString("flag"));
	// }
	// if (json.has("message")) {
	// fam.setMessage(json.getString("message"));
	// }
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return fam;
	// }

	public boolean checkTelphone(String telphone) {
		boolean result = false;
		String regExp = "^[1]([3,4,5,7,8]{1}[0-9]{1})[0-9]{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(telphone);
		result = m.matches();
		return result;
	}

	public boolean checkCard(String card) {
		boolean result = false;
		String regExp = "^([0-9]{17}([0-9]|X))|([0-9]{15})$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(card);
		result = m.matches();
		return result;
	}

	public boolean checkUsername(String username) {
		boolean result = false;
		String regExp = "^([0-9A-Za-z]|\\w){6,}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(username);
		result = m.matches();
		return result;
	}

	public boolean checkPWD(String pwd) {
		boolean result = false;
		String regExp = "^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(pwd);
		result = m.matches();
		return result;
	}

	public boolean checkPhone(String card) {
		boolean result = false;
		String regExp = "^(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?((\\d{8})|(\\d{7}))$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(card);
		result = m.matches();
		return result;
	}

	public boolean checkFPDM(String num) {
		// ��Ʊ����
		boolean result = false;
		String regExp = "^\\d{12}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(num);
		result = m.matches();
		return result;
	}

	public boolean checkFPHM(String num) {
		// ��Ʊ����
		boolean result = false;
		String regExp = "^\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(num);
		result = m.matches();
		return result;
	}

	public boolean checkFPCXM(String num) {
		// ��Ʊ��ѯ��
		boolean result = false;
		String regExp = "^\\d{20}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(num);
		result = m.matches();
		return result;
	}

	// public QuestionAndAnswer getQuestionAndAnswer(JSONObject json) {
	//
	// QuestionAndAnswer qaa = new QuestionAndAnswer();
	// try {
	// if (json.has("question1")) {
	// qaa.setQuestion1(json.getString("question1"));
	// }
	// if (json.has("question2")) {
	// qaa.setQuestion2(json.getString("question2"));
	// }
	// if (json.has("question3")) {
	// qaa.setQuestion3(json.getString("question3"));
	// }
	// if (json.has("rightAnswer1")) {
	// qaa.setRightAnswer1(json.getString("rightAnswer1"));
	// }
	// if (json.has("rightAnswer2")) {
	// qaa.setRightAnswer2(json.getString("rightAnswer2"));
	// }
	// if (json.has("rightAnswer3")) {
	// qaa.setRightAnswer3(json.getString("rightAnswer3"));
	// }
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return qaa;
	// }

	public boolean checkLogin(Activity activity) {
		boolean result = false;
		if ("true".equals(getLoginMessageFLAG(activity))
				&& !"".equals(getLoginMessageUSERNAME(activity))) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * @name 获取设备信息
	 * @description 相关说明
	 * @time 创建时间:2016-5-10上午10:55:35
	 * @param activity
	 * @return
	 * @author 张铭心
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	static public String getDeviceMessageFLAG(Context activity) {
		SharedPreferences loginMessage = activity.getSharedPreferences(
				PbUtils.PHONEMESSAGE, 0);

		return loginMessage.getString(PbUtils.FLAG, "");
	}

	/**
	 * 
	 * @name 获取手机设备信息
	 * @description 相关说明
	 * @time 创建时间:2016-5-10上午10:51:33
	 * @param context
	 * @author 张铭心
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static Map<String, String> getInfo(Context context) {
		Map<String, String> map = new HashMap<String, String>();
		// 手机imei
		map.put(PbUtils.IMEI, ((TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE)).getDeviceId());
		// 手机号码
		if(((TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE)).getLine1Number().startsWith("+86")){
			Log.e("PbUtils.NUMBE--->", ((TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE)).getLine1Number().substring(3, ((TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE)).getLine1Number().length()));
			map.put(PbUtils.NUMBER, ((TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE)).getLine1Number().substring(3, ((TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE)).getLine1Number().length()));
		}else{
			map.put(PbUtils.NUMBER, ((TelephonyManager) context
					.getSystemService(context.TELEPHONY_SERVICE))
					.getLine1Number());
		}
		// 手机型号
		map.put(PbUtils.MODEL, android.os.Build.MODEL);
		// 手机品牌
		map.put(PbUtils.BRAND, android.os.Build.BRAND);
		return map;
	}
	
	public static List<String> getVersionSomeThing(Context context){
		List<String> list = new ArrayList<String>();
		try {
			//获取应用版本号和版本名
			PackageManager packmanager = context.getPackageManager();
			PackageInfo packinfo = packmanager.getPackageInfo(context.getPackageName(),PackageManager.GET_CONFIGURATIONS);
			//版本号
			list.add(String.valueOf(packinfo.versionCode));
			list.add(String.valueOf(packinfo.versionName));
			return list;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("版本获取情况---->", "失败");
		}
		return null;
	}
	
	/**
	 * 请求地址参数传中文的时候用来转码用
	 * 
	 * @param s
	 *            需要转码的中文字符
	 * @return
	 */
	public String getBase64String(String s) {
		if (s == null) {
			return null;
		} else {
			try {
				String str = Base64.encodeToString(s.getBytes("UTF-8"),
						Base64.DEFAULT);
				str = str.replaceAll("\\+", "-");
				str = str.replaceAll("\\/", "*");
				return str;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

	}
	
	public boolean isRunning(Context context){
		ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

		List<RunningTaskInfo> list = am.getRunningTasks(100);

		boolean isAppRunning = false;

		String MY_PKG_NAME = "com.chinasoft.tax";

		for (RunningTaskInfo info : list) {

			if (info.topActivity.getPackageName().equals(MY_PKG_NAME) || info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {

				isAppRunning = true;

				Log.i(TAG,info.topActivity.getPackageName() + " info.baseActivity.getPackageName()="+info.baseActivity.getPackageName());

				break;

			}

		}
		return isAppRunning;
	}
	
	public String CheckNull(String str){
		if(str==null){
			return ".";
		}else{
			return str;
		}
	}
	
	public boolean isForeground(Context context, String className) {  
	       if (context == null || TextUtils.isEmpty(className)) {  
	           return false;  
	       }  
	  
	       ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);  
	       List<RunningTaskInfo> list = am.getRunningTasks(1);  
	       if (list != null && list.size() > 0) {  
	           ComponentName cpn = list.get(0).topActivity;  
	           if (className.equals(cpn.getClassName())) {  
	               return true;  
	           }  
	       }  
	  
	       return false;  
	   }  
	
	public void equalsVersion(String currversion,String type,Context context){
		if(!currversion.equals("")){
			if(type.equals("msg")){
				if(!getMSGVERSION(context).equals("")){
					if(Integer.parseInt(currversion)>Integer.parseInt(getMSGVERSION(context))){
						setMSGVERSION(currversion, context);
					}
				}else{
					setMSGVERSION(currversion, context);
				}
			}else if(type.equals("gg")){
				if(!getGGVERSION(context).equals("")&&!getGGVERSION(context).equals("null")){
					if(Integer.parseInt(currversion)>Integer.parseInt(getGGVERSION(context))){
						setGGVERSION(currversion, context);
					}
				}else{
					setGGVERSION(currversion, context);
				}
			}			
		}
	}
	
	public String reverseCode(String src){
		try {
			String str = new String(src.getBytes("ISO-8859-1"),"UTF-8");
			return str;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String byteToimg(byte[] data,Context context,String userid) {
//		Bitmap bit = null;
		BufferedOutputStream bou = null;
		FileOutputStream fou = null;
		File file = null;
		if (data.length < 3){
			return null;
		}else {		
			try {
				/**************************************************************************/
				file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),userid);
				if(!file.exists()){
					file.createNewFile();
				}
				fou = new FileOutputStream(file);
				bou = new BufferedOutputStream(fou);
				bou.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					if(bou!=null){
						bou.close();
					}
					if(fou!=null){
						fou.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			return file.getPath();
			}
	}
	public void showAlertiDialog(Activity activity,String title,String content){
		Builder ab = new Builder(activity);
		ab.setTitle(title).setMessage(content).setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}).create().show();
	}
}
