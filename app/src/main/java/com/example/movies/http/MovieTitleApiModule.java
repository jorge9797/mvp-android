package com.example.movies.http;


import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class MovieTitleApiModule {

    private final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private final String api_key ="c19b71e519287727eda9ff98eb1804ab";

    @Provides
    public OkHttpClient provideClient(){
        return null;
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl , OkHttpClient client){
        return null;
    }

    @Provides
    public MoviesApiService provideApiService(){
        return provideRetrofit(BASE_URL , provideClient() ).create(MoviesApiService.class);
    }

}
