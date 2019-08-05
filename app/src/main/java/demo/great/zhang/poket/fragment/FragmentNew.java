package demo.great.zhang.poket.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.time.Instant;

import butterknife.BindView;
import demo.great.zhang.poket.NewActivity;
import demo.great.zhang.poket.R;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseFragment;
import demo.great.zhang.poket.net.URLConst;
import okhttp3.Call;

public class FragmentNew extends BaseFragment {
    private LinearLayout ll_new;
    @Override
    protected Object getContentLayout() {
        return R.layout.setting_fragment_layout;
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        ll_new =(LinearLayout)contentView.findViewById(R.id.ll_new);
        ll_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(), NewActivity.class);
                startActivity(intent);
            }
        });
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
