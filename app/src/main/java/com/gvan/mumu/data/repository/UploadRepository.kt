package com.gvan.mumu.data.repository

import com.gvan.mumu.data.model.Upload
import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.utils.IOTaskResult
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UploadRepository {

    val mumuApi: MumuApi

    suspend fun uploadImage(file: File): Flow<IOTaskResult<List<Upload>>>

}