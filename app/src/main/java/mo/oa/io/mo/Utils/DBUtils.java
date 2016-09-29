package mo.oa.io.mo.Utils;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import mo.oa.io.mo.UI.Base.App;

/**
 * Created by max-code on 2016/9/25.
 */

public class DBUtils {
    private static final String DBNAME = "MO.db";
//    private static final String DBPATH = Environment.getDataDirectory().getAbsolutePath()+"/"+DBNAME;
    private static DBUtils dbUtils;
    private File datafile = new File(App.getAppinstance().getFilesDir(),DBNAME);
    private Gson gson = new Gson();
    public static DBUtils getDBUtils(){
        if(dbUtils==null){
            dbUtils = new DBUtils();
        }
        return dbUtils;
    }

    public DBUtils() {
    }
    //write方法
    public void WriteToDb(List<String> list){
        String json = gson.toJson(list);
        try {
            if(!datafile.exists()){
                try {
                    datafile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    LogUtils.E("createNewFile失败."+e.getMessage());
                }
            }
            Writer writer = new FileWriter(datafile);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //read方法
    public List<String> getDataFromDB(){
        //take your time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Reader reader = new FileReader(datafile);
            return gson.fromJson(reader,new TypeToken<List<String>>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    //删除数据库文件
    public void deleteDataFile(){
        datafile.delete();
        LogUtils.I("数据库文件删除成功");
    }
}
