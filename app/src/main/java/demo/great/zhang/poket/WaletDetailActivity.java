package demo.great.zhang.poket;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseActivity;

public class WaletDetailActivity extends BaseActivity {
    @BindView(R.id.iv_head_icon)
    ImageView ivHeadIcon;
    @BindView(R.id.tv_walet_name)
    TextView tvWaletName;
    @BindView(R.id.tv_walet_ad)
    TextView tvWaletAd;
    @BindView(R.id.tv_create_walet)
    TextView tvCreateWalet;
    @BindView(R.id.tv_import_walet)
    TextView tvImportWalet;
    @BindView(R.id.tv_addtime)
    TextView tvAddtime;

    @Override
    public String title_text() {
        return "我的钱包";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initEvent() {
        tvWaletName.setText(PoketApplication.currentBean.getWalletName());
        String addtime = tvAddtime.getText().toString();
        String time = PoketApplication.currentBean.getAddTime();
        addtime = String.format(addtime,time.split(" ")[0],time.split(" ")[1]);
        tvAddtime.setText(addtime);
        tvWaletAd.setText(PoketApplication.currentBean.getShoubiAddress());
    }

    @OnClick({R.id.tv_create_walet, R.id.tv_import_walet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_create_walet:
                break;
            case R.id.tv_import_walet:
                break;
        }
    }
}
