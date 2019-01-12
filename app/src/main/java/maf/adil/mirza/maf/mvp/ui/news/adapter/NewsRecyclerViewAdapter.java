package maf.adil.mirza.maf.mvp.ui.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import maf.adil.mirza.maf.R;
import maf.adil.mirza.maf.repository.model.ArticlesItem;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private Context context;
    private List<ArticlesItem> data;
    private NewsRecyclerViewAdapter.ClickListener clickListener;


    public NewsRecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    public NewsRecyclerViewAdapter(Context context, List<ArticlesItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.populer_news_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.textViewAbstract.setText(data.get(position).getSource().getName());
        holder.textView_ByLine.setText(data.get(position).getTitle());
        holder.textView_Soruce.setText(data.get(position).getPublishedAt());
        holder.textView_Date.setText(data.get(position).getContent());
        holder.ImageView(data.get(position).getUrlToImage(), position, holder);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ClickListener {
        void launchIntent(String name);
    }

    public void setItems(List<ArticlesItem> popularNews) {
        this.data = popularNews;
        notifyDataSetChanged();
    }
}
