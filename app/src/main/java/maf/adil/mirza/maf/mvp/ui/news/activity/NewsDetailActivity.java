package maf.adil.mirza.maf.mvp.ui.news.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import maf.adil.mirza.maf.R;
import maf.adil.mirza.maf.repository.model.ArticlesItem;

public class NewsDetailActivity extends AppCompatActivity {

    private SimpleDraweeView simpleDraweeView;
    private TextView textView_title;
    private TextView textView_date;
    private TextView textView_contant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();

        simpleDraweeView = findViewById(R.id.img_news_events_details);
        textView_title = findViewById(R.id.tv_title_news_events_details);
        textView_date = findViewById(R.id.tv_date_news_events_details);
        textView_contant = findViewById(R.id.tv_content_news_events_details);
        if (bundle!=null){
            simpleDraweeView.setImageURI(bundle.getString("News_Image"));
            textView_title.setText(bundle.getString("News_title"));
            textView_date.setText(bundle.getString("News_date"));
            textView_contant.setText(bundle.getString("News_content"));
        }

    }
}
