package demo.great.zhang.poket.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import demo.great.zhang.poket.R;

public class WordsAdapter extends RecyclerView.Adapter {
    private List<String> wordsList = new ArrayList<>();
    private Context mContext;
    private clickItem listener;

    public WordsAdapter(List<String> list,Context context){
        this.wordsList = list;
        this.mContext = context;
    }

    public void setListenner(clickItem clickItem){
        this.listener = clickItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.words_layout,viewGroup,false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        ((WordViewHolder)viewHolder).tvWord.setText(wordsList.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    public void addWords(String word){
        wordsList.add(word);
        notifyDataSetChanged();
    }

    public interface clickItem{
        void itemClick(int position);
    }

    public void reduceWord(int position){
        wordsList.remove(position);
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder{
        TextView tvWord;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tv_words);
        }
    }
}
