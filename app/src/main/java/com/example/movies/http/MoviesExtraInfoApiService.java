package com.example.movies.http;

import com.example.movies.http.apimodel.OmdbApi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesExtraInfoApiService {

    @GET("/")
    Observable<OmdbApi> getExtraInfoMovie(@Query("t") String title);

}
