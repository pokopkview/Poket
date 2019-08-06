package demo.great.zhang.poket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.great.zhang.poket.DealRecodeActivity;
import demo.great.zhang.poket.R;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseFragment;
import demo.great.zhang.poket.utils.StringUtil;

public class FragmentPersonal extends BaseFragment {
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.iv_head_icon)
    ImageView ivHeadIcon;
    @BindView(R.id.tv_walet_name)
    TextView tvWaletName;
    @BindView(R.id.tv_current_walet)
    TextView tvCurrentWalet;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.ll_money_center)
    LinearLayout llMoneyCenter;
    @BindView(R.id.ll_mywalet)
    LinearLayout llMywalet;
    @BindView(R.id.ll_recod)
    LinearLayout llRecod;
    @BindView(R.id.ll_newcomer)
    LinearLayout llNewcomer;
    @BindView(R.id.ll_about_as)
    LinearLayout llAboutAs;

    @Override
    protected Object getContentLayout() {
        return R.layout.my_fragment_layout;
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        if (!StringUtil.isEmpty(PoketApplication.currentBean.getWalletName())&&PoketApplication.currentBean.getWalletName()!=null){
            tvWaletName.setText(PoketApplication.currentBean.getWalletName());
        }


    }

    @OnClick({R.id.rl_header, R.id.iv_head_icon, R.id.tv_walet_name, R.id.tv_current_walet, R.id.iv_more, R.id.ll_money_center, R.id.ll_mywalet, R.id.ll_recod, R.id.ll_newcomer, R.id.ll_about_as})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_header:
                break;
            case R.id.iv_head_icon:
                break;
            case R.id.tv_walet_name:
                break;
            case R.id.tv_current_walet:
                break;
            case R.id.iv_more:
                break;
            case R.id.ll_money_center:
                break;
            case R.id.ll_mywalet:
                break;
            case R.id.ll_recod:
                startActivity(new Intent(getAppActivity(), DealRecodeActivity.class));
                break;
            case R.id.ll_newcomer:
                break;
            case R.id.ll_about_as:
                break;
        }
    }
}
