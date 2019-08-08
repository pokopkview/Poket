package demo.great.zhang.poket;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.utils.QRCodeUtil;

public class InviterActivity extends BaseActivity {
    @BindView(R.id.iv_top_pic)
    ImageView ivTopPic;
    @BindView(R.id.iv_code_qc)
    ImageView ivCodeQc;

    @Override
    public String title_text() {
        return "邀请好友";
    }

    @Override
    protected int getLayout() {
        return R.layout.invit_activity_layout;
    }

    @Override
    protected void initEvent() {
        final Bitmap bitmap = QRCodeUtil.createQRCode("http://yd.ethereume.io/register?memberId=" + PoketApplication.MEMBERID, 400);
        ivCodeQc.setImageBitmap(bitmap);

    }

}
