package com.karunesh.quizzypw.data.remote

import com.karunesh.quizzypw.data.model.HomeResponse
import retrofit2.http.GET

interface HomeApi {

    @GET("student_dashboard.json?alt=media&token=0091b4c2-2ee2-4326-99cd-96d5312b34bd")
    suspend fun getHomeData(): HomeResponse
}