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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.great.zhang.poket.application.PoketApplication;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.entity.ResponseBean;
import demo.great.zhang.poket.entity.UploadImgBack;
import demo.great.zhang.poket.net.URLConst;
import demo.great.zhang.poket.utils.FileUtils;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DePositMoneyActivity extends BaseActivity {

    public static final int RC_CHOOSE_PHOTO = 2;

    private static final int ALBUM_REQUEST_CODE = 1;
    @BindView(R.id.iv_upload)
    ImageView ivUpload;
    @BindView(R.id.et_count)
    EditText etCount;
    @BindView(R.id.bt_confirm)
    Button btConfirm;

    private String imgurl;


    @Override
    protected int getLayout() {
        return R.layout.dipost_layout;
    }

    @Override
    protected void initEvent() {
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post()
                        .url(URLConst.GETMONEY())
                        .addParams("usdtpz",imgurl)//image url
                        .addParams("usdtnum",etCount.getText().toString())//数量 这里是edittext 获取数据
                        .addParams("memberId", PoketApplication.MEMBERID)//meberid
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                System.out.println(e.getMessage());
                        showMsg("充值失败！");
//                        dismissProgress();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                System.out.println(response);
                                Type type = new TypeToken<ResponseBean<String>>() {
                                }.getType();
                                ResponseBean<String> imgBack = new Gson().fromJson(response, type);
                                if(imgBack.getCode().equals("0")){
                                    showMsg("充值成功！");
                                }else{
                                    showMsg(imgBack.getText());
                                }
                            }
                        });
            }
        });
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
                    try {
                        final File file = FileUtils.uriToFile(uri, DePositMoneyActivity.this);
                        System.out.println(file.getAbsolutePath());
                        Bitmap bitmap = BitmapFactory.decodeStream(DePositMoneyActivity.this.getContentResolver().openInputStream(uri));
                        ivUpload.setImageBitmap(bitmap);
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                final Response response = uploadImage(file);
                                if (response == null) {
                                    showMsg("网络错误");
                                } else {
                                    mActivity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                takenMoney(response.body().string());

                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }
                        }.start();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void takenMoney(String response) {
        Type type = new TypeToken<ResponseBean<UploadImgBack>>() {
        }.getType();
        ResponseBean<UploadImgBack> imgBack = new Gson().fromJson(response, type);
        imgurl = imgBack.getData().getImg();

    }


    @OnClick(R.id.iv_upload)
    public void onViewClicked() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //未授权，申请授权(从相册选择图片需要读取存储卡的权限)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_CHOOSE_PHOTO);
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

    public Response uploadImage(File file) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse("multipart/form-data");
        OkHttpClient client = new OkHttpClient();
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_PNG, file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("headimg", file.getName(), fileBody)
                .build();
        Request request = new Request.Builder()
                .url(URLConst.GETUPLOADIMG())
                .post(requestBody)
                .build();
        try {

            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
