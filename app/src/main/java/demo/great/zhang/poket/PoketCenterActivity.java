package demo.great.zhang.poket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import demo.great.zhang.poket.adapter.DBAdapter;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.entity.MeberDetail;
import demo.great.zhang.poket.entity.ResponseBean;
import demo.great.zhang.poket.net.URLConst;
import demo.great.zhang.poket.utils.SharePrefrenceUtils;
import okhttp3.Call;

public class PoketCenterActivity extends BaseActivity {
    @BindView(R.id.rl_md_list)
    RecyclerView rlMdList;


    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.tv02)
    TextView tv02;


    private String mID;
    @Override
    public String title_text() {
        return "资产中心";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_poket_center;
    }

    @Override
    protected void initEvent() {
        mID = SharePrefrenceUtils.getParam(PoketCenterActivity.this,"meberid","").toString();
        tv01.setText(String.valueOf(SharePrefrenceUtils.tvMyMoney));
        tv02.setText(String.valueOf(SharePrefrenceUtils.tvMyOwnMid));



        OkHttpUtils.post()
                .url(URLConst.GETMYMONEY())
                .addParams("memberId", mID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Type type = new TypeToken<ResponseBean<MeberDetail>>() {}.getType();
                        ResponseBean<MeberDetail> responseBean = new Gson().fromJson(response, type);
                        PoketApplication.currentBean = responseBean.getData().getMember();
                        PoketApplication.INFO = responseBean.getData().getMessageInfo();

                        setView(responseBean);
                    }
                });

    }

    private void setView(final ResponseBean<MeberDetail> responseBean) {
        DBAdapter dbAdapter = new DBAdapter(responseBean.getData().getDb(),PoketCenterActivity.this);
        dbAdapter.setClickListenner(new DBAdapter.itemClick() {
            @Override
            public void itemclick(int position) {

                Intent intent = new Intent(PoketCenterActivity.this, PropertyPassActivity.class);
                intent.putExtra("Biname",responseBean.getData().getDb().get(position).getBiname());
                startActivity(intent);


//                startActivity(new Intent(PoketCenterActivity.this, PropertyPassActivity.class));
            }
        });
        rlMdList.setLayoutManager(new LinearLayoutManager(PoketCenterActivity.this));
        rlMdList.setAdapter(dbAdapter);
        rlMdList.addItemDecoration(new DividerItemDecoration(PoketCenterActivity.this,DividerItemDecoration.VERTICAL));
    }
}
