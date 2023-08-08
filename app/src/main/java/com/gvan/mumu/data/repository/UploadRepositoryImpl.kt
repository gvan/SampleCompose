package com.gvan.mumu.data.repository

import com.gvan.mumu.data.model.Upload
import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.utils.IOTaskResult
import com.gvan.mumu.utils.performSafeNetworkApiCall
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(override val mumuApi: MumuApi) : UploadRepository {

    override suspend fun uploadImage(file: File): Flow<IOTaskResult<List<Upload>>> =
        performSafeNetworkApiCall {
            mumuApi.uploadImage(
                MultipartBody.Part.createFormData(
                    "files",
                    "cat7.jpg",
                    file.asRequestBody()
                )
            )
        }

}