package com.gvan.mumu.data.remote.api

import com.gvan.mumu.data.model.VideoData
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface MumuApi {

    @GET("/api/videos?populate=*")
    suspend fun getVideos(): VideoData

}