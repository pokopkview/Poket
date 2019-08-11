package demo.great.zhang.poket;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.utils.SharePrefrenceUtils;

public class PropertyPassActivity extends BaseActivity {

    @BindView(R.id.tv_get)
    TextView tvGet;
    @BindView(R.id.tv_send)
    TextView tvSend;

    @BindView(R.id.tv_top)
    TextView tvTop;

    @Override
    public String title_text() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.propertiy_pass_layout;
    }

    @Override
    protected void initEvent() {
        tvTop.setText(getIntent().getStringExtra("Biname"));


        tvGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,ReceivActivity.class));
            }
        });
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,ChargeActivity.class));
            }
        });
    }
}
