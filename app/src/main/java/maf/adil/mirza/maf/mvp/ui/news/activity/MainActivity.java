package maf.adil.mirza.maf.mvp.ui.news.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import maf.adil.mirza.maf.R;
import maf.adil.mirza.maf.mvp.contract.NewsContract;
import maf.adil.mirza.maf.mvp.presenter.NewsPresenter;
import maf.adil.mirza.maf.mvp.ui.news.adapter.NewsRecyclerViewAdapter;
import maf.adil.mirza.maf.repository.Injection;
import maf.adil.mirza.maf.repository.model.ArticlesItem;
import maf.adil.mirza.maf.repository.model.NewsResponse;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements NewsContract.View {

    private NewsContract.Presenter presenter;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private RecyclerView recyclerViewUsers;
    private TextView textViewErrorMessage;
    private List<ArticlesItem> newsResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing this activity component .
        initView();
    }


    /**
     * In this method we are configuring mainRecycler according to our needs.
     */

    @Override
    public void showNewsResults(NewsResponse popularNewsList) {
        recyclerViewUsers.setVisibility(View.VISIBLE);
        textViewErrorMessage.setVisibility(View.GONE);
        System.out.println(popularNewsList.getTotalResults());

    newsResponses =popularNewsList.getArticles();
        System.out.println(newsResponses);
        newsRecyclerViewAdapter.setItems(newsResponses);


    }


    /**
     * In this method we are configuring mainRecycler according Show Error.
     */

    @Override
    public void showError(String message) {
        textViewErrorMessage.setVisibility(View.VISIBLE);
        recyclerViewUsers.setVisibility(View.GONE);
        textViewErrorMessage.setText(message);
    }


    /**
     * In this method we are configuring mainRecycler according to Show Loading.
     */

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewUsers.setVisibility(View.GONE);
        textViewErrorMessage.setVisibility(View.GONE);
    }


    /**
     * In this method we are configuring mainRecycler according Hide Loading.
     */

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerViewUsers.setVisibility(View.VISIBLE);
        textViewErrorMessage.setVisibility(View.GONE);
    }

    /**
     * In this method destroy Presenter Data.
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    /**
     * In this method Initialize component .
     */

    private void initView() {
        presenter = new NewsPresenter(Injection.provideUserRepo(), Schedulers.io(), AndroidSchedulers.mainThread());
        presenter.attachView(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        newsResponses=new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textViewErrorMessage = (TextView) findViewById(R.id.text_view_error_msg);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recycler_view_users);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, null);
        recyclerViewUsers.setAdapter(newsRecyclerViewAdapter);
        presenter.loadData();


    }
}
