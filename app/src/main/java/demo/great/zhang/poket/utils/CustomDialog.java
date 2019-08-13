package demo.great.zhang.poket.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import demo.great.zhang.poket.R;

public class CustomDialog extends Dialog {
    private EditText etCount,etCode;
    private TextView tvCancle,tvConfirm;
    private Spinner spinner;

    private DialogItf lisenter;

    public CustomDialog(Context context) {
        this(context,0);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public interface DialogItf{
        void cancle();
        void confirm();
    }

    public void setListenner(DialogItf dialogItf){
        this.lisenter = dialogItf;
    }

    public String getCount(){
        return etCount.getText().toString();
    }
    public String getCode(){
        return spinner.getSelectedItem().toString();
    }

    public void setResID(int resid){
        setContentView(resid);
        etCount = this.findViewById(R.id.et_count);
        etCode = this.findViewById(R.id.et_code);
        tvCancle = this.findViewById(R.id.tv_cancle);
        tvConfirm = this.findViewById(R.id.tv_confirm);
        spinner = this.findViewById(R.id.sp_level);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisenter.confirm();
            }
        });
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisenter.cancle();
            }
        });

    }


}
