package demo.great.zhang.poket.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import demo.great.zhang.poket.R;
import demo.great.zhang.poket.entity.MeberDetail;

public class DBAdapter extends RecyclerView.Adapter {

    private List<MeberDetail.DbBean> data = new ArrayList<>();
    private Context mContext;
    private itemClick itemClick;

    public DBAdapter(List<MeberDetail.DbBean> value, Context context){
        this.data = value;
        this.mContext = context;
    }

    public void setClickListenner(itemClick listenner){
        itemClick = listenner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.db_layout,viewGroup,false);
        return new dbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        System.out.println("onBindViewHolder");
        MeberDetail.DbBean dbBean = data.get(i);
        ((dbViewHolder)viewHolder).price.setText(String.format(((dbViewHolder) viewHolder).price.getText().toString(),dbBean.getBiprice()));
        ((dbViewHolder) viewHolder).tvcount.setText(String.valueOf(dbBean.getGerennum()));
        Glide.with(mContext).load(dbBean.getLogo()).apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(((dbViewHolder) viewHolder).ivIcon);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemclick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class dbViewHolder extends RecyclerView.ViewHolder{
        TextView tvcount;
        TextView price;
        ImageView ivIcon;

        public dbViewHolder(@NonNull View itemView) {
            super(itemView);
            tvcount = itemView.findViewById(R.id.tv_cn);
            price = itemView.findViewById(R.id.tv_usd);
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }

    public interface itemClick{
        void itemclick(int position);
    }
}
