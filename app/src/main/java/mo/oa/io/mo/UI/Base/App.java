package mo.oa.io.mo.UI.Base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by max-code on 2016/9/19.
 */
public class App extends Application {
    public static App appinstance ;
    protected static Context context;
    public static Context getAppinstance() {
        if(context==null){
            context = new App().getApplicationContext();
        }
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

}
