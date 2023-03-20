package com.gvan.mumu.data.repository

import android.util.Log
import com.gvan.mumu.data.model.SingleMediaData
import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.data.model.MediaData
import com.gvan.mumu.utils.IOTaskResult
import com.gvan.mumu.utils.performSafeNetworkApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(override val mumuApi: MumuApi) : VideoRepository {

    override suspend fun getVideos(): Flow<IOTaskResult<MediaData>> =
        performSafeNetworkApiCall {
            mumuApi.getVideos()
        }
    override suspend fun getVideo(id: Int): Flow<IOTaskResult<SingleMediaData>> =
        performSafeNetworkApiCall {
            mumuApi.getVideo(id)
        }
}