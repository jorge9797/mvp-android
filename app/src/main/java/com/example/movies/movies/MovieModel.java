package com.example.movies.movies;

import com.example.movies.http.apimodel.Result;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class MovieModel implements MoviesMVP.Model {

    private MovieRepository  repository;


    public MovieModel(MovieRepository  repository){
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {
        return Observable.zip(repository.getResultData(), repository.getCountryData(), new BiFunction<Result, String, ViewModel>() {
            @Override
            public ViewModel apply(Result result, String s) throws Exception {
                //TODO: cambiar result.toString cuando se tenga el POJO de datos
                return new ViewModel(  result.toString() ,  s  );
            }
        });
    }
}
