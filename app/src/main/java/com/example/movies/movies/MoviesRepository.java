package com.example.movies.movies;

import com.example.movies.http.MoviesApiService;
import com.example.movies.http.MoviesExtraInfoApiService;
import com.example.movies.http.apimodel.Result;

import io.reactivex.Observable;

public class MoviesRepository implements Repository {

    private MoviesApiService moviesApiService;
    private MoviesExtraInfoApiService extraInfoApiService;

    public MoviesRepository(MoviesApiService  mService , MoviesExtraInfoApiService eService){
        this.moviesApiService = mService;
        this.extraInfoApiService = eService;
    }

    @Override
    public Observable<Result> getResultData() {
        return null;
    }

    @Override
    public Observable<String> getCountryData() {
        return null;
    }
}

