package com.gvan.mumu.data.remote.api

import com.gvan.mumu.data.model.SingleVideoData
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.data.model.VideoData
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MumuApi {

    @GET("/api/videos?populate=*")
    suspend fun getVideos(): Response<VideoData>

    @GET("/api/videos/{id}?populate=*")
    suspend fun getVideo(@Path("id") id: Int): Response<SingleVideoData>

}