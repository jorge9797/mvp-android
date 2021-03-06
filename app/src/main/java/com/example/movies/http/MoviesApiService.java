package com.example.movies.http;

import com.example.movies.http.apimodel.TopMoviesRated;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApiService {

    @GET("top_rated")
    Observable<TopMoviesRated> getTopMoviesRated( @Query("page") Integer page );




}
