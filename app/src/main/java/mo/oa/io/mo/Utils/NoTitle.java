package mo.oa.io.mo.Utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

@SuppressLint("ResourceAsColor")
public class NoTitle{
	private static NoTitle noTitle;
	public static NoTitle getTitle(){
		if(noTitle==null){
			noTitle = new NoTitle();
		}
		return noTitle;
	}
//	public void initWindow(Activity activity) {
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			setTranslucentStatus(true,activity);
//		}
//
//		SystemBarTintManager tintManager = new SystemBarTintManager(activity);
//		tintManager.setStatusBarTintEnabled(true);
//		tintManager.setStatusBarTintResource(R.color.login_btn);// 通知栏所需颜色
//
//	}
//
//	@TargetApi(19)
//	private void setTranslucentStatus(boolean on,Activity activity) {
//		Window win = activity.getWindow();
//		WindowManager.LayoutParams winParams = win.getAttributes();
//		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//		if (on) {
//			winParams.flags |= bits;
//		} else {
//			winParams.flags &= ~bits;
//		}
//		win.setAttributes(winParams);
//	}

	public static void initTitle(Activity activity){
	     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
	    	 activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//	    	 activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);   
	  }
	}
	
	//判断当前手机系统版本
	public static boolean Is4UpVersion(){
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
			return true;
		}else{
			return false;
		}
	}
	

   public static int px2dip(Context context, float pxValue) {  
       final float scale = context.getResources().getDisplayMetrics().density;  
       return (int) (pxValue / scale + 0.5f);  
   }
	//获取状态栏高度
   public static int getStatusBar(Activity activity){
	  int result = 0;
 	  int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
 	  if (resourceId > 0) {
 	      result = activity.getResources().getDimensionPixelSize(resourceId);
 	  }
	   return result;
   }
	//创建状态栏view
	public static View createStatusView(Activity activity,int color){
		View view = new View(activity);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,getStatusBar(activity));
		view.setLayoutParams(layoutParams);
		view.setBackgroundResource(color);
		return view;
	}
	//设置状态栏view
	public static void setStatusView(Activity activity,int colot){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			ViewGroup vg = (ViewGroup) activity.getWindow().getDecorView();
			vg.addView(createStatusView(activity,colot));
			ViewGroup rootview = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
			rootview.setFitsSystemWindows(true);
		}
	}

   public static int dip2px(Context context, float dpValue) { 
       final float scale = context.getResources().getDisplayMetrics().density; 
       return (int) (dpValue * scale + 0.5f);
    }   
}
