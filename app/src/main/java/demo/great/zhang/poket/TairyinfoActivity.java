package demo.great.zhang.poket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.great.zhang.poket.base.BaseActivity;

public class TairyinfoActivity extends BaseActivity {

    @Override
    public String title_text() {
        return "隐私政策";
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_tairyinfo;
    }

    @Override
    protected void initEvent() {

    }
}
