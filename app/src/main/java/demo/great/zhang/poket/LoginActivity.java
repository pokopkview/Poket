package demo.great.zhang.poket;

import android.content.Intent;
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
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.entity.LoginBean;
import demo.great.zhang.poket.entity.ResponseBean;
import demo.great.zhang.poket.net.URLConst;
import demo.great.zhang.poket.utils.SharePrefrenceUtils;
import okhttp3.Call;

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
        tvLogin.setClickable(false);
        OkHttpUtils.post()
                .url(URLConst.GETLOGIN())
                    .addParams("mobile",name)
                    .addParams("password",pwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.getMessage());
                        tvLogin.setClickable(true);
//                        showMsg("未知错误");
//                        dismissProgress();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tvLogin.setClickable(true);
                        System.out.println(response);
                        getCallBack(response, 1);
                    }
                });
    }

    @Override
    protected void getCallBack(String response, int flag) {
        System.out.println(response);
        switch (flag){
            case 1:
                if(response!=null){
                    Type type = new TypeToken<ResponseBean<LoginBean>>(){}.getType();
                    ResponseBean<LoginBean> responseBean = new Gson().fromJson(response,type);
                    if(responseBean.getCode().equals("0")){
                        PoketApplication.MEMBERID = responseBean.getData().getMemberId();
                        SharePrefrenceUtils.setParam(mContext,"meberid",PoketApplication.MEMBERID);
                        if(((String)SharePrefrenceUtils.getParam(mContext,"guestkey","")).isEmpty()) {
                            Intent intent = new Intent(mContext, KeywordActivity.class);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(mContext, GuestLockActivity.class);
                            startActivity(intent);
                        }
                        finish();
                    }else{
                        showMsg(responseBean.getText());
                    }
                }
                break;
        }

    }
}
