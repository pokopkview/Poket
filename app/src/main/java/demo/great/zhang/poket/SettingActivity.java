package demo.great.zhang.poket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.utils.DataCleanUtils;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.title_top)
    LinearLayout titleTop;
    @BindView(R.id.ll_money_center)
    LinearLayout llMoneyCenter;
    @BindView(R.id.ll_clean)
    LinearLayout llClean;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.tv_quit)
    TextView tvQuit;
    @BindView(R.id.tv_version_name)
    TextView tvVersion;

    @Override
    public String title_text() {
        return "设置";
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_set;
    }

    @Override
    protected void initEvent() {
        tvVersion.setText(AboutUSActivity.getVersionName(this));
        tvQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SplaseActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        String cache = null;
        try {
            cache = DataCleanUtils.getTotalCacheSize(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvCache.setText(cache);

    }


    @OnClick(R.id.ll_clean)
    public void onViewClicked() {
        showDialog("确认清除缓存数据？");
    }


    private void showDialog(String title){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
//        normalDialog.setIcon(R.mipmap.app_new_icon);
        normalDialog.setTitle("提醒！");
        normalDialog.setCancelable(false);
        normalDialog.setMessage(title);
        normalDialog.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        DataCleanUtils.clearAllCache(mContext);
                        String cache = null;
                        try {
                            cache = DataCleanUtils.getTotalCacheSize(mContext);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tvCache.setText(cache);
                    }
                });
        // 显示
        normalDialog.show();
    }

}
