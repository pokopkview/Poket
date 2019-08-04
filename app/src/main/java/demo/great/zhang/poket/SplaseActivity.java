package demo.great.zhang.poket;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.base.BaseActivity;

public class SplaseActivity extends BaseActivity {
    @BindView(R.id.tv_skip)
    TextView tvSkip;

    @Override
    protected int getLayout() {
        return R.layout.layout_splase_activity;
    }

    private CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String str = tvSkip.getText().toString();
            str = (millisUntilFinished/1000+1) + str.substring(1);
            tvSkip.setText(str);

        }

        @Override
        public void onFinish() {
            startActivity(new Intent(mContext, LoginActivity.class));
            finish();
        }
    };

    @Override
    protected void initEvent() {
        countDownTimer.start();
    }

    @OnClick(R.id.tv_skip)
    public void onViewClicked() {
        startActivity(new Intent(mContext, LoginActivity.class));
        countDownTimer.cancel();
        finish();
    }
}
