package com.example.movies.http;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class MovieExtraInfoApiModule {

    public String BASE_URL="http://www.omdbapi.com/";


    @Provides
    public OkHttpClient provideClient(){
        return null;
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl , OkHttpClient client){
        return null;
    }

    @Provides
    public MoviesExtraInfoApiService provideApiService(){
        return provideRetrofit(BASE_URL , provideClient()).create(MoviesExtraInfoApiService.class);
    }

}
