package com.begicim.clearscoredemo.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CreditApiService {
    @GET(NetworkConstants.CLEAR_SCORE_PATH_URL)
    fun getCreditInfo() : Deferred<CreditModel>
}