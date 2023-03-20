package com.gvan.mumu.domain

import com.gvan.mumu.data.model.SingleVideoData
import com.gvan.mumu.data.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

interface GetVideoUseCase {

    val videoRepository: VideoRepository

    suspend fun getVideo(id: Int): Flow<SingleVideoData>

}