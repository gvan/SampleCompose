package com.gvan.mumu.domain

import com.gvan.mumu.data.model.MediaData
import com.gvan.mumu.data.repository.VideoRepository
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideosUseCaseImpl @Inject constructor(override val videoRepository: VideoRepository) : GetVideosUseCase {

    override suspend fun getVideos(): Flow<IOTaskResult<MediaData>> {
        return videoRepository.getVideos()
    }


}