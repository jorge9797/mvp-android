package com.example.movies.root;

import android.app.Application;

import com.example.movies.http.MovieExtraInfoApiModule;
import com.example.movies.http.MovieTitleApiModule;
import com.example.movies.movies.MoviesModule;

public class App  extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate(){
        super.onCreate();

        component =  DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .moviesModule(new MoviesModule())
                .movieTitleApiModule(new MovieTitleApiModule())
                .movieExtraInfoApiModule(new MovieExtraInfoApiModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return this.component;
    }
}
