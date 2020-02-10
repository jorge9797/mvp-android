package com.example.movies.root;

import com.example.movies.MainActivity;
import com.example.movies.http.MovieExtraInfoApiModule;
import com.example.movies.http.MovieTitleApiModule;
import com.example.movies.http.MoviesExtraInfoApiService;
import com.example.movies.movies.MoviesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class ,
        MovieTitleApiModule.class ,
        MoviesModule.class ,
        MovieExtraInfoApiModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);

}
