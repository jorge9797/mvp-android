package com.example.movies.movies;

import com.example.movies.http.apimodel.Result;

import io.reactivex.Observable;

public interface Repository {

    Observable<Result> getResultFromNetwork();
    Observable<Result>  getResultFromCache();
    Observable<Result> getResultData();

    Observable<String> getCountryFromNetwork();
    Observable<String> getCountryFromCache();
    Observable<String> getCountryData();


}
