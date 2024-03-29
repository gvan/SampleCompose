package com.gvan.mumu.di

import com.gvan.mumu.data.remote.api.MumuApi
import com.gvan.mumu.data.repository.ChannelsRepository
import com.gvan.mumu.data.repository.ChannelsRepositoryImpl
import com.gvan.mumu.data.repository.UploadRepository
import com.gvan.mumu.data.repository.UploadRepositoryImpl
import com.gvan.mumu.data.repository.VideoRepository
import com.gvan.mumu.data.repository.VideoRepositoryImpl
import com.gvan.mumu.domain.GetVideoUseCase
import com.gvan.mumu.domain.GetVideoUseCaseImpl
import com.gvan.mumu.domain.GetVideosUseCase
import com.gvan.mumu.domain.GetVideosUseCaseImpl
import com.gvan.mumu.utils.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ) : Retrofit = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()

    @Provides
    fun provideMumuApi(retrofit: Retrofit) : MumuApi = retrofit.create(MumuApi::class.java)

    @Provides
    fun provideVideoRepository(mumuApi: MumuApi) : VideoRepository = VideoRepositoryImpl(mumuApi)

    @Provides
    fun provideUploadRepository(mumuApi: MumuApi) : UploadRepository = UploadRepositoryImpl(mumuApi)

    @Provides
    fun provideChannelsRepository(mumuApi: MumuApi) : ChannelsRepository = ChannelsRepositoryImpl(mumuApi)

    @Provides
    fun provideGetVideoUseCase(repository: VideoRepository) : GetVideoUseCase = GetVideoUseCaseImpl(repository)

    @Provides
    fun provideGetVideosUseCase(repository: VideoRepository) : GetVideosUseCase = GetVideosUseCaseImpl(repository)

}