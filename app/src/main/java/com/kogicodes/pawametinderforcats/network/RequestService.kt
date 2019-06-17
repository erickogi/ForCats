/*
 * *
 *  * Created by Kogi Eric  on 5/17/19 8:29 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 5/17/19 8:24 AM
 *
 */

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author kogi
 */
object RequestService {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrls().LIVE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getClient())
            .build()
    }



    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("x-api-key", "ea1e9969-b459-4d09-be75-3b84dad87db4")

                .build()
            chain.proceed(newRequest)
        }.build()
    }


    fun getService(): EndPoints {
         return getRetrofit().create(EndPoints::class.java)


    }






}
