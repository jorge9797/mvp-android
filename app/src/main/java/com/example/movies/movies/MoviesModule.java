package com.example.movies.movies;

import com.example.movies.http.MoviesApiService;
import com.example.movies.http.MoviesExtraInfoApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    public MoviesMVP.Presenter provideMoviesPresenter(MoviesMVP.Model moviesModel){
        return new MoviesPresenter(moviesModel);
    }

    @Provides
    public MoviesMVP.Model provideMovieModel(Repository repository){
        return new MovieModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideMovieRepository(MoviesApiService moviesApiService , MoviesExtraInfoApiService moviesExtraInfoApiService){
        return new MoviesRepository(moviesApiService , moviesExtraInfoApiService);
    }



}
