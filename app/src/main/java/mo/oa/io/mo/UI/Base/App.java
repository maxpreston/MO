package mo.oa.io.mo.UI.Base;

import android.app.Application;

/**
 * Created by max-code on 2016/9/19.
 */
public class App extends Application {
    public static App appinstance ;

    public static App getAppinstance() {
        if(appinstance==null){
            appinstance = new App();
        }
        return appinstance;
    }

}
