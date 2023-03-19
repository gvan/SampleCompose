package com.gvan.mumu.data.repository

import com.gvan.mumu.data.model.SingleVideoData
import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.data.model.VideoData
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    val mumuApi: MumuApi

    suspend fun getVideos(): Flow<VideoData>

    suspend fun getVideo(id: Int): Flow<SingleVideoData>

}