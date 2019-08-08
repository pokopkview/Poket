package demo.great.zhang.poket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import demo.great.zhang.poket.entity.ResponseBean;
import demo.great.zhang.poket.net.URLConst;
import okhttp3.Call;

public class DealRecodeActivity extends BaseActivity {
    @BindView(R.id.rl_recode)
    RecyclerView rlRecode;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.title_top)
    LinearLayout titleTop;
    @BindView(R.id.tv_get)
    TextView tvGet;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.iv_none)
    ImageView ivNone;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

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
        showProgress();
        OkHttpUtils.post()
                .url(URLConst.GETDEALDETAIL())
                .addParams("memberId", PoketApplication.MEMBERID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.getMessage());
                        dismissProgress();
                        showMsg("失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        dismissProgress();
                        Type type = new TypeToken<ResponseBean<List<DealRecode>>>() {
                        }.getType();
                        ResponseBean<List<DealRecode>> responseBean = new Gson().fromJson(response, type);
                        if (responseBean.getData().size() < 1) {
                            ivNone.setVisibility(View.VISIBLE);
                            rlRecode.setVisibility(View.GONE);
                            llBottom.setVisibility(View.GONE);
                        } else {
                            ivNone.setVisibility(View.GONE);
                            rlRecode.setVisibility(View.VISIBLE);
                            llBottom.setVisibility(View.VISIBLE);
                            System.out.println(responseBean.getData().size());
                            DealRecodeAdapter dealRecodeAdapter = new DealRecodeAdapter(responseBean.getData(), mContext);
                            rlRecode.setAdapter(dealRecodeAdapter);
                            rlRecode.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
                            rlRecode.setLayoutManager(new LinearLayoutManager(mContext));
                        }

                    }
                });
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ChargeActivity.class));
            }
        });
        tvGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ReceivActivity.class));
            }
        });
    }

}
