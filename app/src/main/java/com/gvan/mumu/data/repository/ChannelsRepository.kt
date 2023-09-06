package com.gvan.mumu.data.repository

import com.gvan.mumu.data.model.Channel
import com.gvan.mumu.data.model.Channels
import com.gvan.mumu.data.model.post_params.ChannelParams
import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {

    val mumuApi: MumuApi

    suspend fun getChannels(): Flow<IOTaskResult<Channels>>

    suspend fun createChannel(params: ChannelParams): Flow<IOTaskResult<Channel>>

    suspend fun removeChannel(channelId: Int): Flow<IOTaskResult<Channel>>

}