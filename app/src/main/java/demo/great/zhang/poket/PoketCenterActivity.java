package demo.great.zhang.poket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.great.zhang.poket.base.BaseActivity;

public class PoketCenterActivity extends BaseActivity {

    @Override
    public String title_text() {
        return "资产中心";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_poket_center;
    }

    @Override
    protected void initEvent() {

    }
}
