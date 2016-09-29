package mo.oa.io.mo.Utils;

import android.content.Context;

import mo.oa.io.mo.UI.Base.App;

/**
 * Created by max-code on 2016/9/19.
 */
public class FragmentUtils {
    public static FragmentUtils fragmentUtils;
    public static Context context;

    public synchronized static Context getContext() {
        if(context==null){
            context = App.getAppinstance().getApplicationContext();
        }
        return context;
    }

    public synchronized static FragmentUtils getFragmentUtils() {
        if(fragmentUtils==null){
            fragmentUtils = new FragmentUtils();
        }

        return fragmentUtils;
    }

}
