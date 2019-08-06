package demo.great.zhang.poket.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import demo.great.zhang.poket.R;
import demo.great.zhang.poket.entity.DealRecode;

public class DealRecodeAdapter extends RecyclerView.Adapter {

    private List<DealRecode> dealRecodeList;
    private Context mContext;

    public DealRecodeAdapter(List<DealRecode> list,Context context){
        this.dealRecodeList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.deal_recode_layout,viewGroup,false);
        return new RecodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        DealRecode dealRecode = dealRecodeList.get(i);
        System.out.println(dealRecode.getRemark());
        ((RecodeViewHolder) viewHolder).tvdetail.setText(dealRecode.getRemark());
        String times = ((RecodeViewHolder) viewHolder).tvTime.getText().toString();
//        if(dealRecode!=null||dealRecode.getAddTime()!=null||!dealRecode.getAddTime().isEmpty()) {
//            ((RecodeViewHolder) viewHolder).tvTime.setText(String.format(times, dealRecode.getAddTime().split(" ")[0], dealRecode.getAddTime().split(" ")[1]));
//        }else{
//            ((RecodeViewHolder) viewHolder).tvTime.setText("null");
//        }
    }

    @Override
    public int getItemCount() {
        return dealRecodeList.size();
    }

    class RecodeViewHolder extends RecyclerView.ViewHolder{
        TextView tvdetail,tvTime;
        View colorView;

        public RecodeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvdetail = itemView.findViewById(R.id.tv_detail);
            tvTime = itemView.findViewById(R.id.tv_time);
            colorView = itemView.findViewById(R.id.v_color);
        }
    }
}
