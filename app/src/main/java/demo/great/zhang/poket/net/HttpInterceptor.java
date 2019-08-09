package demo.great.zhang.poket.net;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;



import java.io.IOException;
import java.net.HttpURLConnection;

import demo.great.zhang.poket.base.BaseActivity;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    private static Context mContext;

    public static void setmContext(Context context){
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request uest = chain.request();
        Response response;
        response = chain.proceed(uest);
        System.out.println("ResponseCode:"+response.code());
        System.out.println("Response:"+response.toString());
        if(response.code()!= HttpURLConnection.HTTP_OK){
            ((Activity)mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((BaseActivity)mContext).dismissProgress();
                    System.out.println(mContext.getClass());
                    Toast.makeText(mContext,"网络错误",Toast.LENGTH_LONG).show();
//                    ((BaseActivity)mContext).showNormalDialog("请先连接并登陆到Wi-Fi名为\"Railwaywifi\"的Wi-Fi下！");
                }
            });
        }
        return response;
    }

}
