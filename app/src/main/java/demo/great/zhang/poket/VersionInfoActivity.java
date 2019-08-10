package demo.great.zhang.poket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.great.zhang.poket.base.BaseActivity;

public class VersionInfoActivity extends BaseActivity {

    @Override
    public String title_text() {
        return "版本日志";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_version_info;
    }

    @Override
    protected void initEvent() {

    }
}
