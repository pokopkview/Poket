package demo.great.zhang.poket.application;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class PoketApplication extends Application {


    private static PoketApplication instance = null;
    List<Activity> activityList = new LinkedList();//Activity 容器
    @Override
    public void onCreate() {
        super.onCreate();
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
