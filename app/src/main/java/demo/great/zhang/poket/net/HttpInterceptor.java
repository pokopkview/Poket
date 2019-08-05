package demo.great.zhang.poket.net;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.zhy.http.okhttp.request.CountingRequestBody;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Set;

import demo.great.zhang.poket.base.BaseActivity;
import okhttp3.FormBody;
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
        System.out.println(uest.toString());
        System.out.println(uest.body().getClass());
        Set<String> paramKeys = uest.url().queryParameterNames();
        for (String key : paramKeys) {
            String value = uest.url().queryParameter(key);
            // 这里就是GET接口传的参数key和value
            System.out.println("value:"+value);
        }
        if (uest.body() instanceof CountingRequestBody) {
            CountingRequestBody formBody = (CountingRequestBody) uest.body();
            System.out.println(formBody.contentLength());
            System.out.println(formBody.contentType());
        }
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
