package demo.great.zhang.poket;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.base.BaseActivity;

public class ReceivActivity extends BaseActivity {
    @BindView(R.id.tv_walet_ad)
    TextView tvWaletAd;
    @BindView(R.id.tv_click_copy)
    TextView tvClickCopy;

    @Override
    protected int getLayout() {
        return R.layout.activity_receivables;
    }

    @Override
    protected void initEvent() {
        tvWaletAd.setText(getIntent().getStringExtra("waletaddress"));
    }

    @OnClick(R.id.tv_click_copy)
    public void onViewClicked() {
        if(copy(tvWaletAd.getText().toString())){
            showMsg("已复制到粘贴板");
        }
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
            ClipboardManager cm = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
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
