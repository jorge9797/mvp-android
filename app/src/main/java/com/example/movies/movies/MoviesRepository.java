package com.example.movies.movies;

import com.example.movies.http.MoviesApiService;
import com.example.movies.http.MoviesExtraInfoApiService;
import com.example.movies.http.apimodel.OmdbApi;
import com.example.movies.http.apimodel.Result;
import com.example.movies.http.apimodel.TopMoviesRated;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MoviesRepository implements Repository {

    private MoviesApiService moviesApiService;
    private MoviesExtraInfoApiService extraInfoApiService;

    private List<String> countries;
    private List<Result> results;
    private long lastTimestamp;


    private static final long CACHE_LIFETIME = (20 * 1000); //Cache que durara 20 segundos

    public MoviesRepository(MoviesApiService  mService , MoviesExtraInfoApiService eService){
        this.moviesApiService = mService;
        this.extraInfoApiService = eService;

        this.lastTimestamp  = System.currentTimeMillis();

        this.countries =  new ArrayList<>();
        this.results  = new ArrayList<>();
    }

    private boolean isUpdated(){
        return   (System.currentTimeMillis() - lastTimestamp) < CACHE_LIFETIME;
    }

    @Override
    public Observable<Result> getResultFromNetwork() {

        Observable<TopMoviesRated> topMoviesRatedObservable = moviesApiService.getTopMoviesRated(1)
        .concatWith(moviesApiService.getTopMoviesRated(2))
        .concatWith(moviesApiService.getTopMoviesRated(3));

        return topMoviesRatedObservable.concatMap(new Function<TopMoviesRated, Observable<Result>>() {
            @Override
            public Observable<Result> apply(TopMoviesRated topMoviesRated)  {
                return Observable.fromIterable(topMoviesRated.getResults());
            }
        }).doOnNext(new Consumer<Result>() {
            @Override
            public void accept(Result result){
                results.add(result);
            }
        });
    }

    @Override
    public Observable<Result> getResultFromCache() {
        if(isUpdated()){
            return Observable.fromIterable(results);
        }else{
            lastTimestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultData() {
        return null;
    }

    @Override
    public Observable<String> getCountryFromNetwork() {
        return getResultFromNetwork().concatMap(new Function<Result, Observable<OmdbApi>>() {
            @Override
            public Observable<OmdbApi> apply(Result result)  {
                return extraInfoApiService.getExtraInfoMovie(result.getTitle());
            }
        }).concatMap(new Function<OmdbApi, Observable<String>>() {
            @Override
            public Observable<String> apply(OmdbApi omdbApi){
                return Observable.just(omdbApi.getCountry().toString());
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String country) throws Exception {
                countries.add(country);
            }
        });
    }

    @Override
    public Observable<String> getCountryFromCache() {
        if(isUpdated()){
            return Observable.fromIterable(countries);
        }else{
            lastTimestamp =  System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountryData() {
        return null;
    }
}

