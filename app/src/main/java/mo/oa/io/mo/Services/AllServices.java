package mo.oa.io.mo.Services;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import mo.oa.io.mo.API.HomeMenuAPI;
import mo.oa.io.mo.API.LoginAPI;
import mo.oa.io.mo.R;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by max-code on 2016/9/19.
 */
public class AllServices {
    //声明services
   public static LoginAPI loginAPI;
    //okhttp(设置默认读取以及写入事件为十秒)
    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder().readTimeout(10000,TimeUnit.MICROSECONDS).writeTimeout(10000,TimeUnit.MICROSECONDS).connectTimeout(10000,TimeUnit.MICROSECONDS).build();
    //String类型
    private static Converter.Factory cf = ScalarsConverterFactory.create();
    //转换类型
    private static Converter.Factory gcf = GsonConverterFactory.create();
    private static CallAdapter.Factory callf = RxJavaCallAdapterFactory.create();
    private static HomeMenuAPI homeMenuAPI;
    private static Context context;
    private static AllServices allServices;

    public static AllServices getAllServices() {
        if(allServices==null){
            allServices = new AllServices();
        }
        return allServices;
    }

    public static LoginAPI getLoginAPI(Context context){
        if(loginAPI==null){
            Retrofit retrofit = new Retrofit.Builder().
                    client(okHttpClient).
                    baseUrl(context.getString(R.string.rooturl)).
                    addConverterFactory(cf).
                    addCallAdapterFactory(callf).
                    build();
            loginAPI = retrofit.create(LoginAPI.class);
        }
        return loginAPI;
    }
    //获取消息列表
    public static HomeMenuAPI getMsgList(Context context){
        if(homeMenuAPI==null){
            Retrofit retrofit = new Retrofit.Builder().
                    client(okHttpClient).
                    baseUrl(context.getString(R.string.rooturl)).
                    addConverterFactory(gcf).
                    addCallAdapterFactory(callf).
                    build();
            homeMenuAPI = retrofit.create(HomeMenuAPI.class);
        }
        return homeMenuAPI;
    }
}
