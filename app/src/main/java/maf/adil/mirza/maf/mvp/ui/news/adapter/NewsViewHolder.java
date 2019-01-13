package maf.adil.mirza.maf.mvp.ui.news.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import maf.adil.mirza.maf.R;


public class NewsViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewAbstract;
    public ImageView imageView_user;
    public TextView textView_ByLine;
    public TextView textView_Soruce;
    public TextView textView_Date;
    public LinearLayout linearLayoutContainer;


    NewsViewHolder(View itemView) {
        super(itemView);

        textViewAbstract = itemView.findViewById(R.id.textview_abstract);
        imageView_user = itemView.findViewById(R.id.imageView_user);
        textView_ByLine = itemView.findViewById(R.id.textviewByLine);
        textView_Soruce = itemView.findViewById(R.id.textviewSource);
        textView_Date = itemView.findViewById(R.id.textviewDate);
        linearLayoutContainer = itemView.findViewById(R.id.ll_container);

    }

    public void ImageView(String url,NewsViewHolder holder) {


            if (!TextUtils.isEmpty(url)) {
                Uri imageUri = Uri.parse(url);
                SimpleDraweeView draweeView = (SimpleDraweeView) holder.imageView_user;
                draweeView.setImageURI(imageUri);
                int color = Color.GRAY;
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                roundingParams.setBorder(color, 1.0f);
                roundingParams.setRoundAsCircle(true);
                draweeView.getHierarchy().setRoundingParams(roundingParams);



        }
    }

}


