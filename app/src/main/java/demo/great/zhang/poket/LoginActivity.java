package demo.great.zhang.poket;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.net.URLConst;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_userpwd)
    EditText etUserpwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected int getLayout() {
        return R.layout.layout_login;
    }

    @Override
    protected void initEvent() {

    }


    @OnClick(R.id.tv_login)
    public void onViewClicked() {
        String name = etUsername.getText().toString();
        String pwd = etUserpwd.getText().toString();
        params.put("mobile","18987467656");
        params.put("password","123456");
        HttpPost(URLConst.GETLOGIN(),params,1);
    }

    @Override
    protected void getCallBack(String response, int flag) {
        switch (flag){
            case 1:
                System.out.println(response);
                break;
        }

    }
}
