package com.gvan.mumu.domain

import com.gvan.mumu.data.model.VideoData
import com.gvan.mumu.data.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

interface GetVideosUseCase {

    val videoRepository: VideoRepository

    suspend fun getVideos(): Flow<VideoData>

}