package demo.great.zhang.poket;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.entity.LoginBean;
import demo.great.zhang.poket.entity.ResponseBean;
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
    public String title_text() {
        return "转账";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_transfer_accounts;
    }

    @Override
    protected void initEvent() {

    }


    @OnClick(R.id.tv_confirm_t)
    public void onViewClicked() {
        if (tvWaletAddress.getText().toString().trim() == null || "".equals(tvWaletAddress.getText().toString().trim())){
            toast.setText("请填写钱包地址！");
            toast.show();
            return;
        }
        if (tvCount.getText().toString().trim() == null || "".equals(tvCount.getText().toString().trim())){
            toast.setText("请输入钱币数量！");
            toast.show();
            return;
        }


        showProgress();
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
                        showMsg("未知错误");
                        dismissProgress();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dismissProgress();
                        Type type = new TypeToken<ResponseBean<String>>(){}.getType();
                        ResponseBean<String> responseBean = new Gson().fromJson(response,type);
                        showMsg(responseBean.getText());
                    }
                });
    }
}
