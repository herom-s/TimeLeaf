package com.project.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.project.network.TlNetworkExerciseDataSource
import com.project.network.model.NetworkExercise
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface RetrofitTlExerciseNetworkApi {
    @GET("exercise/")
    suspend fun getExercises(): List<NetworkExercise>
}

private const val EXERCISE_BASE_URL = "https://wger.de/api/v1/"

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

@Singleton
internal class RetrofitTlExerciseNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : TlNetworkExerciseDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(EXERCISE_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitTlExerciseNetworkApi::class.java)
    override suspend fun getExercises(): List<NetworkExercise> = networkApi.getExercises()
}