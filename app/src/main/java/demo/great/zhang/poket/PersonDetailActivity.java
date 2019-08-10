package demo.great.zhang.poket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.great.zhang.poket.base.BaseActivity;

public class PersonDetailActivity extends BaseActivity {

    @Override
    public String title_text() {
        return "个人信息";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_person_detail;
    }

    @Override
    protected void initEvent() {

    }
}
