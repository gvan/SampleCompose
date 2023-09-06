package com.gvan.mumu.data.remote.api

import com.gvan.mumu.data.model.Channel
import com.gvan.mumu.data.model.Channels
import com.gvan.mumu.data.model.SingleMediaData
import com.gvan.mumu.data.model.MediaData
import com.gvan.mumu.data.model.Upload
import com.gvan.mumu.data.model.post_params.ChannelParams
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface MumuApi {

    @GET("/api/videos?populate=*")
    suspend fun getVideos(): Response<MediaData>

    @GET("/api/videos/{id}?populate=*")
    suspend fun getVideo(@Path("id") id: Int): Response<SingleMediaData>

    @GET("/api/channels?populate=*")
    suspend fun getChannels(): Response<Channels>

    @POST("/api/channels")
    suspend fun createChannel(@Body params: ChannelParams): Response<Channel>

    @DELETE("/api/channels/{id}")
    suspend fun deleteChannel(@Path("id") id: Int): Response<Channel>

    @Multipart
    @POST("/api/upload")
    suspend fun uploadImage(@Part files: MultipartBody.Part): Response<List<Upload>>

}