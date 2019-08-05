package demo.great.zhang.poket.fragment;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import demo.great.zhang.poket.R;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseFragment;
import demo.great.zhang.poket.net.URLConst;
import okhttp3.Call;

public class FragmentNew extends BaseFragment {
    @Override
    protected Object getContentLayout() {
        return R.layout.setting_fragment_layout;
    }

    @Override
    protected void initNet() {
        OkHttpUtils.post()
                .url(URLConst.GETREDFLOWER())
                .addParams("memberId", PoketApplication.MEMBERID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.getMessage());
//                        showMsg("未知错误");
//                        dismissProgress();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);

                    }
                });
    }
}
