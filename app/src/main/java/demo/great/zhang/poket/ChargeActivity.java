package demo.great.zhang.poket;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.net.URLConst;
import okhttp3.Call;

public class ChargeActivity extends BaseActivity {
    @BindView(R.id.tv_m_type)
    TextView tvMType;
    @BindView(R.id.tv_walet_address)
    EditText tvWaletAddress;
    @BindView(R.id.tv_count)
    EditText tvCount;
    @BindView(R.id.tv_m_type_out)
    TextView tvMTypeOut;
    @BindView(R.id.tv_m_steal)
    TextView tvMSteal;
    @BindView(R.id.tv_steal_type)
    TextView tvStealType;
    @BindView(R.id.tv_confirm_t)
    TextView tvConfirmT;

    @Override
    protected int getLayout() {
        return R.layout.activity_transfer_accounts;
    }

    @Override
    protected void initEvent() {

    }


    @OnClick(R.id.tv_confirm_t)
    public void onViewClicked() {
        OkHttpUtils.post()
                .url(URLConst.GETSHAREMONEY())
                .addParams("memberId",getIntent().getStringExtra("meberid"))
                .addParams("walletName",tvWaletAddress.getText().toString())
                .addParams("zcnum",tvCount.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.getMessage());
//                        showMsg("未知错误");
//                        dismissProgress();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        getCallBack(response, 1);
                    }
                });
    }
}
