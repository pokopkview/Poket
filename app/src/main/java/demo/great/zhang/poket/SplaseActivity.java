package demo.great.zhang.poket;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.utils.SharePrefrenceUtils;

public class SplaseActivity extends BaseActivity {
    @BindView(R.id.tv_skip)
    TextView tvSkip;

    @Override
    public String title_text() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_splase_activity;
    }

    private CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
//            String str = tvSkip.getText().toString();
//            str = (millisUntilFinished/1000+1) + str.substring(1);
//            tvSkip.setText(str);

        }

        @Override
        public void onFinish() {
            System.out.println(SharePrefrenceUtils.getParam(mContext,"meberid",""));
            if(((String)SharePrefrenceUtils.getParam(mContext,"meberid","")).isEmpty()||((String)SharePrefrenceUtils.getParam(mContext,"guestkey","")).isEmpty()){
                startActivity(new Intent(mContext, LoginActivity.class));
            }else{
                startActivity(new Intent(mContext, GuestLockActivity.class));
            }
            finish();
        }
    };

    @Override
    protected void initEvent() {
        countDownTimer.start();
    }

    @OnClick(R.id.tv_skip)
    public void onViewClicked() {
        System.out.println("___"+SharePrefrenceUtils.getParam(mContext,"meberid",""));
        System.out.println("___"+SharePrefrenceUtils.getParam(mContext,"guestkey",""));
        if(((String)SharePrefrenceUtils.getParam(mContext,"meberid","")).isEmpty()||((String)SharePrefrenceUtils.getParam(mContext,"guestkey","")).isEmpty()){
            startActivity(new Intent(mContext, LoginActivity.class));
        }else{
            startActivity(new Intent(mContext, GuestLockActivity.class));
        }
        countDownTimer.cancel();
        finish();
    }
}
