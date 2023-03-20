package com.gvan.mumu.data.repository

import com.gvan.mumu.data.model.SingleVideoData
import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.data.model.VideoData
import com.gvan.mumu.utils.IOTaskResult
import com.gvan.mumu.utils.performSafeNetworkApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(override val mumuApi: MumuApi) : VideoRepository {

    override suspend fun getVideos(): Flow<IOTaskResult<VideoData>> =
        performSafeNetworkApiCall {
            mumuApi.getVideos()
        }
    override suspend fun getVideo(id: Int): Flow<IOTaskResult<SingleVideoData>> =
        performSafeNetworkApiCall {
            mumuApi.getVideo(id)
        }
}