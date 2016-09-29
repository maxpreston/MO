package mo.oa.io.mo.Utils;

import android.text.TextUtils;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by max-code on 2016/9/20.
 */
public class CheckUtils {
    public static CheckUtils checkUtils;

    public static CheckUtils getCheckUtils() {
        if(checkUtils==null){
            checkUtils = new CheckUtils();
        }
        return checkUtils;
    }
    //非空检查
   public static boolean CheckEmpty(String text){
        if(text.equals("")||text==null){
            return false;
        }else{
            return true;
        }
   }
    static void LoadError(){

    }
}
