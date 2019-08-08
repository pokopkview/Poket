package demo.great.zhang.poket;

import demo.great.zhang.poket.base.BaseActivity;

public class AboutUSActivity extends BaseActivity {
    @Override
    public String title_text() {
        return "关于我们";
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_about_us;
    }

    @Override
    protected void initEvent() {

    }
}
