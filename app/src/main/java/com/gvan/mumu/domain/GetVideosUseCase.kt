package com.gvan.mumu.domain

import com.gvan.mumu.data.model.MediaData
import com.gvan.mumu.data.repository.VideoRepository
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow

interface GetVideosUseCase {

    val videoRepository: VideoRepository

    suspend fun getVideos(): Flow<IOTaskResult<MediaData>>

}