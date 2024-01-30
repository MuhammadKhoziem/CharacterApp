package com.example.characterapp.di

import com.example.characterapp.data.repository.CharactersRepository
import com.example.characterapp.data.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // region Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)
   // endregion

    @Singleton
    @Provides
    fun provideCharacterRepository(apiInterface: ApiInterface): CharactersRepository =
        CharactersRepository(apiInterface)
}