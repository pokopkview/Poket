package demo.great.zhang.poket;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.great.zhang.poket.adapter.DealRecodeAdapter;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.entity.DealRecode;
import demo.great.zhang.poket.entity.LoginBean;
import demo.great.zhang.poket.entity.ResponseBean;
import demo.great.zhang.poket.net.URLConst;
import okhttp3.Call;

public class DealRecodeActivity extends BaseActivity {
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.rl_recode)
    RecyclerView rlRecode;

    @Override
    public String title_text() {
        return "交易记录";
    }

    @Override
    protected int getLayout() {
        return R.layout.deal_recode_activity_layout;
    }

    @Override
    protected void initEvent() {

        OkHttpUtils.post()
                .url(URLConst.GETDEALDETAIL())
                .addParams("memberId", PoketApplication.MEMBERID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Type type = new TypeToken<ResponseBean<List<DealRecode>>>(){}.getType();
                        ResponseBean<List<DealRecode>> responseBean = new Gson().fromJson(response,type);
                        System.out.println(responseBean.getData().size());
                        DealRecodeAdapter dealRecodeAdapter = new DealRecodeAdapter(responseBean.getData(),mContext);
                        rlRecode.setAdapter(dealRecodeAdapter);
                        rlRecode.setLayoutManager(new LinearLayoutManager(mContext));

                    }
                });

    }
}
