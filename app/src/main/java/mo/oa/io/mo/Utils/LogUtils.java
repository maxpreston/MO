package mo.oa.io.mo.Utils;

import android.os.Environment;
import android.text.format.Time;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Log记录类
 * Created by max-code on 2016/9/20.
 */
public enum  LogUtils {

    instance;

    LogUtils() {
    }

    //file
    private static File logfile = null;
    //filename
    static final String LogFileName = "log.txt";
    //logfilepath
    static final String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/css/"+LogFileName;
    //error
    public static void E(String TAG,String msg){
        Log.e(TAG,getLineNumber(msg));
        WriteLog("Error:",msg,TAG);
    }
    //worning
    public static void W(String msg){
        W(getClassName(),msg);
    }
    //verbose
    public static void V(String msg){
        V(getClassName(),msg);
    }
    //info
    public static void I(String msg){
        I(getClassName(),msg);
    }
    public static void E(String msg){
        E(getClassName(),msg);
    }
    //worning
    public static void W(String TAG,String msg){
        Log.e(TAG,getLineNumber(msg));
        WriteLog("Warn:",msg,TAG);
    }
    //verbose
    public static void V(String TAG,String msg){
        Log.e(TAG,getLineNumber(msg));
        WriteLog("Verbose:",msg,TAG);
    }
    //info
    public static void I(String TAG,String msg){
        Log.e(TAG,getLineNumber(msg));
        WriteLog("Info:",msg,TAG);
    }
    //WriteLogToFile
    // 写入日志信息信息
    public static void WriteLog(String type, String errormsg, String TAG) {
        FileIsExist(path);
        String logmsg = "/r/n" + Time.getCurrentTimezone() + "/r/n" + type
                + "  " + TAG + "/r/n" + errormsg;
        File file = new File(path);
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(logmsg);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("io异常-->",e.getMessage());
        }
    }

    //PrintLineNumber
    public static String getLineNumber(String msg){
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        String classname = stes[5].getClassName();
        classname = classname.substring(classname.lastIndexOf(".")+1)+".java";
        int lineNumber = stes[5].getLineNumber();
        if(lineNumber<0){
            lineNumber = 0;
        }
        return "("+classname+":"+lineNumber+")"+msg;
    }
    //getClassName
    public static String getClassName(){
        String className;
        //getStackTrace
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        className = ste.getClassName();
        int lastindex = className.lastIndexOf(".");
        className = className.substring(lastindex+1);
        //去除匿名内部类
        int i = className.indexOf("$");

        return i==-1?className:className.substring(0,i);
    }
    //判断文件是否存在
    public static void FileIsExist(String path){
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("无法创建文件-->",e.getMessage());
            }
        }
    }

    //删除日志文件
    public static void DeleteLogFile(){
        if(logfile.exists()){
            logfile.delete();
        }
    }
}
