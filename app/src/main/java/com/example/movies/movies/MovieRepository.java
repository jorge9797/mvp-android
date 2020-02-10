package com.example.movies.movies;

import io.reactivex.Observable;

public interface MovieRepository {

    Observable<Result> getResultData();

    Observable<String> getCountryData();

}
