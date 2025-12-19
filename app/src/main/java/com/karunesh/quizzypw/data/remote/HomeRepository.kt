package com.karunesh.quizzypw.data.remote

import com.karunesh.quizzypw.data.model.HomeResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: HomeApi
) {
    suspend fun getHomeData(): HomeResponse = api.getHomeData()
}