package com.gvan.mumu.domain

import com.gvan.mumu.data.model.SingleMediaData
import com.gvan.mumu.data.repository.VideoRepository
import com.gvan.mumu.utils.Const
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetVideoUseCaseImpl @Inject constructor (override val videoRepository: VideoRepository) : GetVideoUseCase {

    override suspend fun getVideo(id: Int): Flow<IOTaskResult<SingleMediaData>> {
        return videoRepository.getVideo(id)
            .onEach { response ->
                if(response is IOTaskResult.OnSuccess) {
                    response.data.data.attributes.video.data.attributes.apply {
                        this.url = Const.BASE_URL.dropLast(1) + this.url
                    }
                }
            }
    }


}