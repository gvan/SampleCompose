package com.gvan.mumu.domain

import com.gvan.mumu.data.model.SingleVideoData
import com.gvan.mumu.data.repository.VideoRepository
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideoUseCaseImpl @Inject constructor (override val videoRepository: VideoRepository) : GetVideoUseCase {

    override suspend fun getVideo(id: Int): Flow<IOTaskResult<SingleVideoData>> {
        return videoRepository.getVideo(id)
    }


}