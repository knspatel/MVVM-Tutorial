package com.anubhav87.mvvmtutorial.di

import com.anubhav87.mvvmtutorial.utils.AppConstant.BASE_URL
import com.google.gson.Gson
import com.myabilities.pitchai.data.datasources.api.ArticleService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By: dev1618
 * Created Date: 6/19/2019
 * Purpose: KOIN Module provides retrofit related services which can
 * provides the various required API Interfaces.
 */

val retrofitModule = module {

    single { provideRetrofit(get(), get()) }
  //  single { providePitchRecordingService(get()) }
    single { providePitcherService(get()) }
}

/**
 * Created By: dev1618
 * Created Date: 6/19/2019
 * Purpose: Function to provide retrofit instance.
 */

fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}


// GET THE INSTANCE OF THE WALLET SERVICE
//fun provideWalletService(retrofit: Retrofit): WalletService = retrofit.create(WalletService::class.java)

// GET THE INSTANCE OF PITCH RECORDING SERVICE
//fun providePitchRecordingService(retrofit: Retrofit): PitchRecordingService =
//    retrofit.create(PitchRecordingService::class.java)

fun providePitcherService(retrofit: Retrofit): ArticleService =
    retrofit.create(ArticleService::class.java)
