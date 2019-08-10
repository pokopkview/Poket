package demo.great.zhang.poket;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.base.BaseActivity;

public class AboutUSActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.title_top)
    LinearLayout titleTop;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_taily)
    LinearLayout llTaily;
    @BindView(R.id.ll_version_log)
    LinearLayout llVersionLog;
    @BindView(R.id.ll_community)
    LinearLayout llCommunity;
    @BindView(R.id.ll_money_center)
    LinearLayout llMoneyCenter;

    @Override
    public String title_text() {
        return "关于我们";
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_about_us;
    }

    @Override
    protected void initEvent() {
        tvVersion.setText(String.format(tvVersion.getText().toString(),getVersionName(this)));
    }

    @OnClick({R.id.tv_version, R.id.ll_service, R.id.ll_taily, R.id.ll_version_log, R.id.ll_community})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_version:
                break;
            case R.id.ll_service:
                startActivity(new Intent(mContext,ServicesinfoActivity.class));
                break;
            case R.id.ll_taily:
                startActivity(new Intent(mContext,TairyinfoActivity.class));
                break;
            case R.id.ll_version_log:
                startActivity(new Intent(mContext,VersionInfoActivity.class));
                break;
            case R.id.ll_community:
                startActivity(new Intent(mContext,CommunityActivity.class));
                break;
        }
    }

    public static String getVersionName(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }
}
