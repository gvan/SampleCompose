package com.gvan.mumu.data.repository

import com.gvan.mumu.data.model.Channel
import com.gvan.mumu.data.model.SingleMediaData
import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.data.model.MediaData
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    val mumuApi: MumuApi

    suspend fun getVideos(): Flow<IOTaskResult<MediaData>>

    suspend fun getVideo(id: Int): Flow<IOTaskResult<SingleMediaData>>

}