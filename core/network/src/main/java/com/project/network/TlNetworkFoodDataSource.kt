package com.project.network

import com.project.network.model.NetworkFood

interface TlNetworkFoodDataSource {
    suspend fun getFoods(): List<NetworkFood>
}