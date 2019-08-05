package demo.great.zhang.poket.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.great.zhang.poket.ChargeActivity;
import demo.great.zhang.poket.DePositMoneyActivity;
import demo.great.zhang.poket.R;
import demo.great.zhang.poket.ReceivActivity;
import demo.great.zhang.poket.adapter.DBAdapter;
import demo.great.zhang.poket.base.BaseFragment;
import demo.great.zhang.poket.entity.MeberDetail;
import demo.great.zhang.poket.entity.ResponseBean;
import demo.great.zhang.poket.net.URLConst;
import demo.great.zhang.poket.utils.GlideImageLoader;
import okhttp3.Call;

public class PoketFragment extends BaseFragment {
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    Unbinder unbinder;
    @BindView(R.id.my_poket)
    LinearLayout myPoket;
    @BindView(R.id.tv_my_money)
    TextView tvMyMoney;
    @BindView(R.id.tv_my_own)
    TextView tvMyOwn;
    @BindView(R.id.tv_my_own_mid)
    TextView tvMyOwnMid;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.iv_eqcode)
    ImageView ivEqcode;
    @BindView(R.id.tv_click_copy)
    TextView tvClickCopy;
    @BindView(R.id.ll_take_money)
    LinearLayout llTakeMoney;
    @BindView(R.id.ll_getmoney)
    LinearLayout llGetmoney;
    @BindView(R.id.ll_share_money)
    LinearLayout llShareMoney;
    Unbinder unbinder1;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rl_md_list)
    RecyclerView rlMdList;
    Unbinder unbinder2;

    private String mID;

    @Override
    protected Object getContentLayout() {
        return R.layout.assets_fragment_layout;
    }

    @Override
    protected void initNet() {
        mID = getArguments().getString("meberid");
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
                        Type type = new TypeToken<ResponseBean<MeberDetail>>() {
                        }.getType();
                        ResponseBean<MeberDetail> responseBean = new Gson().fromJson(response, type);
                        setView(responseBean);
                    }
                });
    }

    private void setView(final ResponseBean<MeberDetail> responseBean) {
        tvMyMoney.setText(String.valueOf(responseBean.getData().getMember().getUsdtfee()));
        tvMyOwnMid.setText(String.valueOf(responseBean.getData().getMember().getNoUSDT()));
        tvPass.setText(responseBean.getData().getMember().getShoubiAddress());
        tvMyOwn.setText(String.format(tvMyOwn.getText().toString(), responseBean.getData().getMember().getWalletName()));
        List<Object> urlList = new ArrayList<>();
        urlList.add(R.drawable.zichan01);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(urlList);
        banner.start();
        DBAdapter dbAdapter = new DBAdapter(responseBean.getData().getDb(),getAppActivity());
        dbAdapter.setClickListenner(new DBAdapter.itemClick() {
            @Override
            public void itemclick(int position) {

            }
        });
        rlMdList.setLayoutManager(new LinearLayoutManager(getAppActivity()));
        rlMdList.setAdapter(dbAdapter);
        rlMdList.addItemDecoration(new DividerItemDecoration(getAppActivity(),DividerItemDecoration.VERTICAL));
        tvClickCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = tvPass.getText().toString();
                if(copy(address)){
                    getAppActivity().showMsg("已复制到粘贴板");
                }
            }
        });
        llTakeMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getAppActivity(), DePositMoneyActivity.class);
                intent.putExtra("address",tvPass.getText().toString());
                intent.putExtra("meberid",mID);
                startActivity(intent);
            }
        });
        llShareMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getAppActivity(), ChargeActivity.class);
                intent.putExtra("meberid",mID);
                startActivity(intent);
            }
        });
        llGetmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getAppActivity(), ReceivActivity.class);
                intent.putExtra("waletaddress",tvPass.getText().toString());
                startActivity(intent);
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    /**
     * 复制内容到剪切板
     *
     * @param copyStr
     * @return
     */
    private boolean copy(String copyStr) {
        try {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) getAppActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
