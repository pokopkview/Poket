package demo.great.zhang.poket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.great.zhang.poket.base.BaseActivity;

public class CommunityActivity extends BaseActivity {

    @Override
    public String title_text() {
        return "联系我们";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_community;
    }

    @Override
    protected void initEvent() {

    }
}
