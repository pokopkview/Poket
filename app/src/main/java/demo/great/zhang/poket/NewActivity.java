package demo.great.zhang.poket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.entity.FlowerBean;
import demo.great.zhang.poket.entity.ResponseBean;
import demo.great.zhang.poket.net.URLConst;
import demo.great.zhang.poket.utils.CustomDialog;
import okhttp3.Call;

public class NewActivity extends BaseActivity {


    @BindView(R.id.nas_all)
    TextView nasAll;
    @BindView(R.id.tv_current_step)
    TextView tvCurrentStep;
    @BindView(R.id.tv_folow)
    TextView tvFolow;
    @BindView(R.id.tv_broad)
    TextView tvBroad;
    @BindView(R.id.tv_lucy)
    TextView tvLucy;
    @BindView(R.id.tv_waket_ad)
    TextView tvWaketAd;
    @BindView(R.id.tv_success_count)
    TextView tvSuccessCount;
    @BindView(R.id.tv_success_get)
    TextView tvSuccessGet;
    @BindView(R.id.ll_tips)
    LinearLayout llTips;
    @BindView(R.id.ll_detail)
    LinearLayout llDetail;
    @BindView(R.id.tv_check)
    TextView tvCheck;
    @BindView(R.id.tv_exchange)
    TextView tvExchange;

    @Override
    public String title_text() {
        return "兑换中心";
    }

    @Override
    protected int getLayout() {
        return R.layout.safflower_layout;
    }

    @Override
    protected void initEvent() {


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
                        Type type = new TypeToken<ResponseBean<FlowerBean>>() {
                        }.getType();
                        ResponseBean<FlowerBean> responseBean = new Gson().fromJson(response, type);
                        setView(responseBean.getData());

                    }
                });

    }

    private void setView(FlowerBean flowerBean) {
        tvBroad.setText(String.valueOf(flowerBean.getRedData().getTuigz()));
        tvCurrentStep.setText(String.format(tvCurrentStep.getText().toString(), flowerBean.getRedData().getDjJieduan()));
        tvSuccessCount.setText(String.format(tvSuccessCount.getText().toString(), flowerBean.getCountm()));
        tvSuccessGet.setText(String.format(tvSuccessGet.getText().toString(), flowerBean.getSyall()));
        tvFolow.setText(String.valueOf(flowerBean.getRedData().getYinlingzhe()));
        nasAll.setText(String.valueOf(flowerBean.getRedData().getGzAllNum()));
        tvLucy.setText(String.valueOf(flowerBean.getRedData().getXxjinli()));
        tvWaketAd.setText(flowerBean.getShoubiAddress());
        llDetail.setVisibility(View.GONE);
        llTips.setVisibility(View.GONE);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(llDetail.getVisibility() == View.VISIBLE){
                    llDetail.setVisibility(View.GONE);
                    llTips.setVisibility(View.GONE);
                }else{
                    llDetail.setVisibility(View.VISIBLE);
                    llTips.setVisibility(View.VISIBLE);
                }
            }
        });
        tvExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog dialog = new CustomDialog(mContext);
                dialog.setResID(R.layout.duihuan_layout);
                dialog.setListenner(new CustomDialog.DialogItf() {
                    @Override
                    public void cancle() {
                        dialog.cancel();
                    }

                    @Override
                    public void confirm() {
                        OkHttpUtils.post()
                                .url(URLConst.GETNMS())
                                .addParams("memberId",PoketApplication.MEMBERID)
                                .addParams("usdtNum",dialog.getCount())
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        System.out.println(e.getMessage());
                        showMsg("未知错误");
//                        dismissProgress();
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        System.out.println(response);
                                        if(response!=null) {
                                            Type type = new TypeToken<ResponseBean<String>>() {
                                            }.getType();
                                            ResponseBean<String> imgBack = new Gson().fromJson(response, type);
//                                            if (imgBack.getCode().equals("0")) {
//                                                showMsg(imgBack.getText());
//                                            } else {
                                                showMsg(imgBack.getText());
//                                            }
                                        }
                                        dialog.dismiss();
                                    }
                                });

                    }
                });
                dialog.show();
            }
        });
    }
}
