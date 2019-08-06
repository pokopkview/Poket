package demo.great.zhang.poket.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {
    public static boolean isEmpty(String... str) {
        for (String ss : str) {
            if (ss == null || ss.replaceAll(" ", "").equals("")) return true;
        }
        return false;
    }
    public static String getText(Activity context, int Id) {
        TextView text = (TextView) context.findViewById(Id);
        if (text == null) return "";
        return text.getText() + "";
    }

    public static String getText(Activity context, int Id, String relace) {
        TextView text = (TextView) context.findViewById(Id);
        if (text == null) return "";
        return (text.getText() + "").replaceAll(relace, "");
    }

    public static String getText(View view, int Id) {
        TextView text = (TextView) view.findViewById(Id);
        if (text == null) return "";
        return text.getText() + "";
    }

    public static String getText(View view, int Id, String relace) {
        TextView text = (TextView) view.findViewById(Id);
        if (text == null) return "";
        return (text.getText() + "").replaceAll(relace, "");
    }


    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    /**
     * 判断邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

}