package com.gvan.mumu.domain

import com.gvan.mumu.data.model.SingleVideoData
import com.gvan.mumu.data.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideoUseCaseImpl @Inject constructor (override val videoRepository: VideoRepository) : GetVideoUseCase {

    override suspend fun getVideo(id: Int): Flow<SingleVideoData> {
        return videoRepository.getVideo(id)
    }


}