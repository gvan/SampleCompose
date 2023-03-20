package com.gvan.mumu.data.remote.api

import com.gvan.mumu.data.model.SingleMediaData
import com.gvan.mumu.data.model.MediaData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface MumuApi {

    @GET("/api/videos?populate=*")
    suspend fun getVideos(): Response<MediaData>

    @GET("/api/videos/{id}?populate=*")
    suspend fun getVideo(@Path("id") id: Int): Response<SingleMediaData>

}