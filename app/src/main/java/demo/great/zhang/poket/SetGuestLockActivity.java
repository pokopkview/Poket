package demo.great.zhang.poket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;
import com.wangnan.library.painter.System360Painter;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.utils.SharePrefrenceUtils;

/**
 * 设置解锁界面
 */
public class SetGuestLockActivity extends BaseActivity {
    @BindView(R.id.l_display_view)
    GestureLockView lDisplayView;
    @BindView(R.id.iv_head_icon)
    ImageView ivHeadIcon;
    @BindView(R.id.tv_setting_hint)
    TextView tvSettingHint;
    private String key;
    private boolean isFirst = true;

    @Override
    protected int getLayout() {
        return R.layout.set_lock_layout;
    }

    @Override
    protected void initEvent() {
//        Glide.with(mActivity).load(SharePrefrenceUtils.getParam(mActivity,"headicon",null))
//                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                .into(ivHeadIcon);
        lDisplayView.setPainter(new System360Painter());
        lDisplayView.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                if(isFirst){
                    key = result;
                    isFirst = false;
                    lDisplayView.clearView();
                    tvSettingHint.setText("请再次输入解锁图案");
                }else{
                    if(key.equals(result)){
                        //
                        showMsg("设置完成");
                        SharePrefrenceUtils.setParam(mContext,"guestkey",key);
                        startActivity(new Intent(mContext,MainActivity.class));
                    }else{
                        showMsg("重新输入");
                        tvSettingHint.setText("请输入解锁图案");
                        isFirst = true;
                    }
                }
            }
        });
    }

}
