package com.gvan.mumu.data.repository

import com.gvan.mumu.data.model.Channel
import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.utils.IOTaskResult
import com.gvan.mumu.utils.performSafeNetworkApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(override val mumuApi: MumuApi): ChannelsRepository {

    override suspend fun getChannels(): Flow<IOTaskResult<Channel>> =
        performSafeNetworkApiCall {
            mumuApi.getChannels()
        }

}