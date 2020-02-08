package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import com.example.movies.movies.ListAdapter;
import com.example.movies.movies.MoviesMVP;
import com.example.movies.movies.ViewModel;
import com.example.movies.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesMVP.View {

    private final String TAG =  MainActivity.class.getName();

    @BindView(R.id.activity_root_view)
    ViewGroup rootView;

    @BindView(R.id.recycler_view_movies)
    RecyclerView recyclerView;

    @Inject
    MoviesMVP.Presenter presenter;


    private ListAdapter listAdapter;
    private List<ViewModel> resultList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        (  (App) getApplication()  ).getComponent().inject(this);

        listAdapter = new ListAdapter(resultList);

        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void updateData(ViewModel viewModel){

    }

    @Override
    public void  showSnackBar(String s){

    }

}
