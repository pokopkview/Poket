package demo.great.zhang.poket;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.net.URLConst;
import demo.great.zhang.poket.utils.FileUtils;
import okhttp3.Call;

public class DePositMoneyActivity extends BaseActivity {

    public static final int RC_CHOOSE_PHOTO = 2;

    private static final int ALBUM_REQUEST_CODE = 1;
    @BindView(R.id.iv_upload)
    ImageView ivUpload;


    @Override
    protected int getLayout() {
        return R.layout.dipost_layout;
    }

    @Override
    protected void initEvent() {

    }

    /**
     * 从相册获取图片
     */
    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    System.out.println("RESULT_OK");
                    try {
                        File file = FileUtils.uriToFile(uri, DePositMoneyActivity.this);
                        System.out.println(file.getAbsolutePath());
                        Bitmap bitmap = BitmapFactory.decodeStream(DePositMoneyActivity.this.getContentResolver().openInputStream(uri));
                        ivUpload.setImageBitmap(bitmap);
                        OkHttpUtils.post()
                                .url(URLConst.GETUPLOADIMG())
                                .addFile("file", file.getName(), file)
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                System.out.println(response);
                            }
                        });


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }


    @OnClick(R.id.iv_upload)
    public void onViewClicked() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //未授权，申请授权(从相册选择图片需要读取存储卡的权限)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_CHOOSE_PHOTO);
        } else {
            //已授权，获取照片
            getPicFromAlbm();
        }
    }

    /**
     * 权限申请结果回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RC_CHOOSE_PHOTO:   //相册选择照片权限申请返回
                getPicFromAlbm();
                break;
        }
    }
}
