package com.example.movies.movies;

import com.example.movies.http.apimodel.Result;

import io.reactivex.Observable;

public interface MovieRepository {

    Observable<Result> getResultData();

    Observable<String> getCountryData();

}
