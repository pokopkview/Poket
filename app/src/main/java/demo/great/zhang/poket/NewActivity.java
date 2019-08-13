package demo.great.zhang.poket;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.entity.ClearFlowerBean;
import demo.great.zhang.poket.entity.FlowerBean;
import demo.great.zhang.poket.entity.ResponseBean;
import demo.great.zhang.poket.net.URLConst;
import demo.great.zhang.poket.utils.CustomDialog;
import demo.great.zhang.poket.utils.QRCodeUtil;
import demo.great.zhang.poket.utils.SharePrefrenceUtils;
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
    @BindView(R.id.tv_show_qccode)
    TextView tvQCcode;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.title_top)
    LinearLayout titleTop;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_clear_num)
    TextView tvClearNum;
    @BindView(R.id.tv_lock_state)
    TextView tvLockState;
    @BindView(R.id.ll_flower_layout)
    LinearLayout llFlowerLayout;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.tv_close)
    TextView tvClose;


    String sixword;
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
                        if (responseBean.getCode().equals("0")) {
                            setView(responseBean.getData());
                        } else {
                            showMsg("请求失败！");
                        }

                    }
                });

        OkHttpUtils.post()
                .url(URLConst.GETNEWINFO())
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
                        Type type = new TypeToken<ResponseBean<ClearFlowerBean>>() {
                        }.getType();
                        ResponseBean<ClearFlowerBean> responseBean = new Gson().fromJson(response, type);
                        if (responseBean.getCode().equals("0")) {
                            ClearFlowerBean clearFlowerBean = responseBean.getData();
                            tvCount.setText(String.valueOf(clearFlowerBean.getCynum()));
                            tvClearNum.setText(String.valueOf(clearFlowerBean.getYjssj()));
                            tvLockState.setText(String.valueOf(clearFlowerBean.getState()));
                        } else {
                            showMsg("请求失败！");
                        }

                    }
                });



        final Bitmap bitmap = QRCodeUtil.createQRCode("http://yd.ethereume.io/register?memberId=" + PoketApplication.MEMBERID);
        System.out.println(SharePrefrenceUtils.getParam(mContext,"keywords",String.class)==null);
        if(SharePrefrenceUtils.getParam(mContext,"keywords",String.class)==null||((String)SharePrefrenceUtils.getParam(mContext,"keywords",String.class)).isEmpty()) {
            sixword = getCharAndNumr(6);
            SharePrefrenceUtils.setParam(mContext, "keywords", sixword);
        }else{
            sixword = (String) SharePrefrenceUtils.getParam(mContext,"keywords",String.class);
        }
        tvQCcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(mContext);
                View view = LayoutInflater.from(mContext).inflate(R.layout.show_qc_layout, null, false);
                ImageView imageView = view.findViewById(R.id.iv_show_code);
                TextView textView = view.findViewById(R.id.tv_text);
                textView.setText(sixword);
                imageView.setImageBitmap(bitmap);
                dialog.setContentView(view);
                dialog.show();
            }
        });
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(tvCount.getText().toString())>0) {
                    llFlowerLayout.setVisibility(View.VISIBLE);
                    llMain.setVisibility(View.GONE);
                }
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llFlowerLayout.setVisibility(View.GONE);
                llMain.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setView(FlowerBean flowerBean) {
        tvBroad.setText(String.valueOf(flowerBean.getRedData().getTuigz()));
        tvCurrentStep.setText(String.format(tvCurrentStep.getText().toString(), flowerBean.getRedData().getDjJieduan()));
        tvSuccessCount.setText(String.format(tvSuccessCount.getText().toString(), flowerBean.getCountm()));
        tvSuccessGet.setText(String.format(tvSuccessGet.getText().toString(), flowerBean.getSyall()));
        tvFolow.setText(String.valueOf(flowerBean.getYduihUSDT()));
        nasAll.setText(String.valueOf(flowerBean.getNmustYe()));
        tvLucy.setText(String.valueOf(flowerBean.getYeUsdt()));
        tvWaketAd.setText(flowerBean.getShoubiAddress());
//        llDetail.setVisibility(View.GONE);
//        llTips.setVisibility(View.GONE);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llDetail.getVisibility() == View.VISIBLE) {
                    llDetail.setVisibility(View.GONE);
                    llTips.setVisibility(View.GONE);
                } else {
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
                        showProgress();

                        if(dialog.getCode().length()>4) {
                            OkHttpUtils.post()
                                    .url(URLConst.GETNMS())
                                    .addParams("memberId", PoketApplication.MEMBERID)
                                    .addParams("usdtNum", dialog.getCount())
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            System.out.println(e.getMessage());
                                            showMsg("未知错误");
                                            dismissProgress();
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            dismissProgress();
                                            System.out.println(response);
                                            if (response != null) {
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
                        }else{
                            OkHttpUtils.post()
                                    .url(URLConst.GETNMS())
                                    .addParams("memberId", PoketApplication.MEMBERID)
                                    .addParams("usdtNum", dialog.getCount())
                                    .addParams("ggVal", dialog.getCode().substring(0,1))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            System.out.println(e.getMessage());
                                            showMsg("未知错误");
                                            dismissProgress();
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            dismissProgress();
                                            System.out.println(response);
                                            if (response != null) {
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
                    }
                });
                dialog.show();
            }
        });
    }

    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
// 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
