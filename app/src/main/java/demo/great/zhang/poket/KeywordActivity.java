package demo.great.zhang.poket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.utils.SharePrefrenceUtils;

public class KeywordActivity extends BaseActivity {
    @BindView(R.id.rl_select_words)
    TagFlowLayout rlSelectWords;
    @BindView(R.id.bt_frush)
    LinearLayout btFrush;
    @BindView(R.id.rl_words)
    TagFlowLayout rlWords;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    @BindView(R.id.ll_confrim_words)
    LinearLayout confrimWords;

    private  boolean confrim = false;

    private List<String> tenwords = new ArrayList<>();
    private List<String> selectwords = new ArrayList<>();
    private List<String> basewords = new ArrayList<>();
    List<String> wordsList;
            String [] words = {"death","agree","arm","mother","across","quite","anything","town","past","view","society","manage","answer","break","organize","half","fire","lose","money","stop","actual","already","effort","wait","department","able","political","learn","air","together","shall","cover","common","subject","draw","short","wife","treat","limit","road","letter","color","behind","produce","send","ter","total","university","rise","century","success","minute","remember","purpose","test","fight","watch","situation","south","ago","difference","stage","father","table","rest","bear","entire","market","prepare","explain","offer","plant","charge","ground","west","picture","hard","front","lie","modern","dark","surface","rule","regard","dance","peace","observe","future","wall","farm","claim","firm","operation","further","pressure","property","morning","amount","top","outside","piece","sometimes","beauty","trade","fear","demand","wonder","list","accept","judge","paint","mile","soon","responsible","allow","secretary","heart","union","slow","island","enter","drink","story","experiment","stay","paper","space","apply","decide","share","desire","spend","sign","therefore","various","visit","supply","officer","doubt","private","immediate","wish","contain","feed","raise","describe","ready","horse","son","exist","north","suggest","station","effective","food","deep","wide","alone","character","English","happy","critic","unit","product","respect","drop","nor","fill","cold","represent","sudden","basic","kill","fine","trouble","mark","single","press","heavy","attempt","origin","standard","everything","committee","moral","black","red","bad","earth","accord","else","mere","die","remark","basis","except","equal","east","event","center","let","side","try","provide","continue","name","certain","power","pay","result","question","study","woman","member","until","far","night","always","service","away","report","something","company","week","church","toward","start","social","room","figure","nature","though","young","less","enough","almost","read","include","president","nothing","yet","better","big","boy","cost","business","value","second","why","clear","expect","family","complete","act","sense","mind","experience","art","next","near","direct","car","law","industry","important","girl","several","matter","usual","rather","per","often","kind","among","white","reason","action","return","foot","care","simple","within","love","human","along","appear","doctor","believe","speak","active","student","month","drive","concern","best","door","hope","example","inform","body","ever","least","probable","understand","reach","effect","different","idea","whole","control","condition","field","pass","fall","note","special","talk","particular","today","measure","walk","teach","low","hour","type","carry","rate","remain","full","street","easy","although","record","sit","determine","level","local","sure","receive","thus","moment","spirit","train","college","religion","perhaps","music","grow","free","cause","serve","age","book","board","recent","sound","office","cut","step","class","true","history","position","above","strong","friend","necessary","add","court","deal","tax","support","party","whether","either","land","material","happen","education","country","bring"};//随机得几百个单词


    @Override
    public String title_text() {
        return "助记词";
    }

    @Override
    protected int getLayout() {
        return R.layout.key_words_layout;
    }

    @Override
    protected void initEvent() {
        wordsList = Arrays.asList(words);
        for(int i =0;i<10;i++){
            tenwords.add(wordsList.get(new Random().nextInt(380)));
        }
        final TagAdapter tagAdapter = new TagAdapter<String>(selectwords) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.words_layout_blue,parent,false);
                TextView tvWord = view.findViewById(R.id.tv_words);
                System.out.println("position:"+position);
                tvWord.setText(selectwords.get(position));
                return view;
            }
        };
        rlSelectWords.setAdapter(tagAdapter);
        final TagAdapter tagAdapter2 = new TagAdapter<String>(tenwords) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.words_layout,parent,false);
                TextView tvWord = view.findViewById(R.id.tv_words);
                System.out.println(position+"---"+(tenwords.size()-1));
                if(position==tenwords.size()-1){
                    System.out.println(position+"__"+(tenwords.size()-1));
                }
                tvWord.setText(tenwords.get(position));
                return view;
            }
        };

        rlWords.setAdapter(tagAdapter2);
        rlWords.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if(selectwords.size()>9){
                    showMsg("最多10个词汇！");
                    return false;
                }
                selectwords.add(tenwords.get(position));
                tagAdapter.notifyDataChanged();
                tenwords.remove(position);
                tagAdapter2.notifyDataChanged();
                return false;
            }
        });
        rlSelectWords.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                tenwords.add(selectwords.get(position));
                tagAdapter2.notifyDataChanged();
                selectwords.remove(position);
                tagAdapter.notifyDataChanged();
                return false;
            }
        });
        btFrush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenwords.clear();
                for(int i =0;i<10;i++){
                    tenwords.add(wordsList.get(new Random().nextInt(380)));
                }
                tagAdapter2.notifyDataChanged();
            }
        });
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!confrim) {
                    SharePrefrenceUtils.setParam(mContext, selectwords.toString(), String.class);
                    confrim = true;
                    btFrush.setVisibility(View.GONE);
                    confrimWords.setVisibility(View.VISIBLE);
                    tenwords.clear();
                    basewords = deepCopyList(selectwords);
                    Collections.shuffle(selectwords);
                    tenwords.addAll(selectwords);
                    tagAdapter2.notifyDataChanged();
                    selectwords.clear();
                    tagAdapter.notifyDataChanged();
                }else {
                    if(compareList(basewords,selectwords)) {
                        startActivity(new Intent(mContext, SetGuestLockActivity.class));
                    }else {
                        showMsg("顺序错误请重新输入");
                        tenwords.clear();
                        tenwords.addAll(deepCopyList(basewords));
                        selectwords.clear();
                        tagAdapter.notifyDataChanged();
                        tagAdapter2.notifyDataChanged();
                    }
                }
            }
        });

//        WordsAdapter wordsAdapter = new WordsAdapter(tenwords,mContext);
//        final WordsAdapter wordsAdapter2 = new WordsAdapter(selectwords,mContext);
//        wordsAdapter.setListenner(new WordsAdapter.clickItem() {
//            @Override
//            public void itemClick(int position) {
//                wordsAdapter2.addWords(tenwords.get(position));
//            }
//        });
//        rlWords.setAdapter(wordsAdapter);
//        rlSelectWords.setLayoutManager(new GridLayoutManager(mContext,4));
//        rlWords.setLayoutManager(new GridLayoutManager(mContext,4));
//        rlSelectWords.setAdapter(wordsAdapter2);
    }

    public static <T> List<T> deepCopyList(List<T> src)
    {
        List<T> dest = null;
        try
        {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();
        }
        catch (IOException e)
        {

        }
        catch (ClassNotFoundException e)
        {

        }
        return dest;
    }


    private boolean compareList(List list1,List list2){
        if(list1.size()==list2.size()){
            for(int i =0;i<list1.size();i++){
                if(!list1.get(i).equals(list2.get(i))){
                    return false;
                }
            }
            return true;

        }else{
            return false;
        }
    }
}
