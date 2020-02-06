package com.example.movies.movies;

import io.reactivex.Observable;

public interface MoviesMVP {

    interface View{
        void updateData(ViewModel viewModel);
        void showSnackBar(String s);
    }

    interface Presenter{
        void loadData();
        void rxJavaUnsuscribe();

        void setView(MoviesMVP.View view);
    }

    interface  Model{
        Observable<ViewModel> result();

    }
}
