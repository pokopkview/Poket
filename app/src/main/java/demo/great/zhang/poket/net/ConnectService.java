package demo.great.zhang.poket.net;

import android.app.IntentService;
import android.content.Intent;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class ConnectService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ConnectService(String name) {
        super(name);
    }
    public ConnectService(){
        super("connect");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("------onHandleIntent");
        final int[] result = {-1};
            OkHttpUtils.get().url(URLConst.GETHOT()).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    System.out.println("------error");
                    result[0] = 0;
//                    Intent net = new Intent();
//                    net.setAction("mainactivity.connect");
//                    net.putExtra("connect", result[0]);
//                    EventBus.getDefault().postSticky(new NetMessage(0));
//                    LocalBroadcastManager.getInstance(ConnectService.this).sendBroadcast(net);
                }

                @Override
                public void onResponse(String response, int id) {
                    System.out.println("------onResponse");
                    result[0] = 1;
//                    Intent net = new Intent();
//                    net.setAction("mainactivity.connect");
//                    net.putExtra("connect", result[0]);
//                    EventBus.getDefault().postSticky(new NetMessage(1));
//                    LocalBroadcastManager.getInstance(ConnectService.this).sendBroadcast(net);
                }
            });
//
//
//
//            String ip = URLConst.baseurl().split("//")[1].split(":")[0];// ping 的地址，可以换成任何一种可靠的外网
//            System.out.println("-----"+ip);
//            Process p = Runtime.getRuntime().exec("ping -c 1 -w 100 " + ip);// ping网址3次
//            // 读取ping的内容，可以不加
//            InputStream input = p.getInputStream();
//            BufferedReader in = new BufferedReader(new InputStreamReader(input));
//            StringBuffer stringBuffer = new StringBuffer();
//            String content = "";
//            while ((content = in.readLine()) != null) {
//                stringBuffer.append(content);
//            }
//            Log.d("------ping-----", "result content : " + stringBuffer.toString());
//            // ping的状态
//            int status = p.waitFor();
//            if (status == 0) {
//                result[0] = 1;
//            } else {
//                result[0] = 0;
//            }
    }
}
