package com.gvan.mumu.domain

import com.gvan.mumu.data.model.SingleMediaData
import com.gvan.mumu.data.repository.VideoRepository
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow

interface GetVideoUseCase {

    val videoRepository: VideoRepository

    suspend fun getVideo(id: Int): Flow<IOTaskResult<SingleMediaData>>

}