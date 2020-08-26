package com.anubhav87.mvvmtutorial.retrofit

import com.anubhav87.mvvmtutorial.utils.AppConstant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRequest {

    companion object {
        private var retrofit: Retrofit? = null

        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(httpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
            }
            return retrofit
        }
    }
}