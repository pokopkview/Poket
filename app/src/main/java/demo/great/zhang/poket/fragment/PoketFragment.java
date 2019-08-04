package demo.great.zhang.poket.fragment;

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
import butterknife.Unbinder;
import demo.great.zhang.poket.R;
import demo.great.zhang.poket.base.BaseFragment;

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

    @Override
    protected Object getContentLayout() {
        return R.layout.assets_fragment_layout;
    }

    @Override
    protected void initNet() {


    }
}
