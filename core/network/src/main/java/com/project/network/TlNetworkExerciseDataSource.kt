package com.project.network

import com.project.network.model.NetworkExercise

interface TlNetworkExerciseDataSource {
    suspend fun getExercises(): List<NetworkExercise>
}