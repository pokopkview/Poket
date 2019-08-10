package demo.great.zhang.poket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.great.zhang.poket.base.BaseActivity;

public class NewComerActivity extends BaseActivity {

    @Override
    public String title_text() {
        return "新人手册";
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_new_comer;
    }

    @Override
    protected void initEvent() {

    }
}
