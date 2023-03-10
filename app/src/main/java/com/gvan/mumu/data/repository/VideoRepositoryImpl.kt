package com.gvan.mumu.data.repository

import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.data.model.VideoData
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(override val mumuApi: MumuApi) : VideoRepository {

    override suspend fun getVideos(): Flow<VideoData> = flow {
        emit(mumuApi.getVideos())
    }.flowOn(Dispatchers.IO)
}