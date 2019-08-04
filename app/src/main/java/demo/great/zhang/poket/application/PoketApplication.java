package demo.great.zhang.poket.application;

import android.app.Activity;
import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import demo.great.zhang.poket.net.HttpInterceptor;
import okhttp3.OkHttpClient;

public class PoketApplication extends Application {


    private static PoketApplication instance = null;
    List<Activity> activityList = new LinkedList();//Activity 容器
    @Override
    public void onCreate() {
        super.onCreate();
        HttpInterceptor interceptor = new HttpInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(10000l,TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(interceptor)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }



    public static PoketApplication getInstance(){
        if(instance==null){
            instance = new PoketApplication();
        }
        return instance;
    }


    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    public void exit(){
        for(Activity activity : activityList){
            activity.finish();
        }
        activityList.clear();
        System.exit(0);
    }
}
