package demo.great.zhang.poket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.great.zhang.poket.NewActivity;
import demo.great.zhang.poket.R;
import demo.great.zhang.poket.base.BaseFragment;

public class FragmentNew extends BaseFragment {
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.ll_community)
    LinearLayout llCommunity;
    @BindView(R.id.ll_manage_money)
    LinearLayout llManageMoney;
    @BindView(R.id.ll_new)
    LinearLayout llNew;
    private LinearLayout ll_new;

    @Override
    protected Object getContentLayout() {
        return R.layout.setting_fragment_layout;
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        ll_new = (LinearLayout) contentView.findViewById(R.id.ll_new);
        ll_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewActivity.class);
                startActivity(intent);
            }
        });
        llCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAppActivity().showMsg("正在开发中，敬请关注！");
            }
        });
        llManageMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAppActivity().showMsg("正在开发中，敬请关注！");
            }
        });
    }

    @Override
    protected void initNet() {


    }

}
