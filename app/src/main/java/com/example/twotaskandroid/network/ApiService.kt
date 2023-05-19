package com.example.twotaskandroid.network

import com.example.twotaskandroid.objects.Cat
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("api/cats?limit=20&skip=0")
    fun loadMovies() : Single<List<Cat>>
}