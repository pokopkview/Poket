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
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.utils.SharePrefrenceUtils;

/**
 * 解锁界面
 */
public class GuestLockActivity extends BaseActivity {
    @BindView(R.id.l_display_view)
    GestureLockView lDisplayView;
    @BindView(R.id.iv_head_icon)
    ImageView ivHeadIcon;
    @BindView(R.id.tv_setting_hint)
    TextView tvSettingHint;

    @Override
    public String title_text() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.guest_locak_layout;
    }

    @Override
    protected void initEvent() {
        lDisplayView.setPainter(new System360Painter());
//        Glide.with(mActivity).load(SharePrefrenceUtils.getParam(mActivity,"headicon",null))
//                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                .into(ivHeadIcon);
        lDisplayView.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {//手势划完时候得调用
                if(SharePrefrenceUtils.getParam(mContext,"guestkey","").equals(result)){
                    showMsg("解锁成功！");
                    startActivity(new Intent(mContext,MainActivity.class));
                }else{
                    showMsg("解锁失败！");
                    lDisplayView.clearView();
                }
            }
        });
    }

}
