package demo.great.zhang.poket;

import android.widget.EditText;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import demo.great.zhang.poket.R;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.net.URLConst;
import okhttp3.Call;

public class NewActivity extends BaseActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_transfer_accounts;
    }

    @Override
    protected void initEvent() {

    }
}
