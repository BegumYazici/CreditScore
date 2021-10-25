package com.begicim.clearscoredemo.network

import androidx.annotation.Keep
import com.begicim.clearscoredemo.ui.model.CreditUIModel
import com.squareup.moshi.Json

@Keep
data class CreditModel(
    @field:Json( name="creditReportInfo")
    val creditReportInfo: CreditReport,
    @field:Json(name="dashboardStatus")
    val dashboardStatus: String
)

@Keep
data class CreditReport(
    @field:Json(name="score")
    val score : Int,
    @field:Json(name="maxScoreValue")
    val maxScoreValue : Int,
    @field:Json(name="scoreBand")
    val scoreBand : Int
)

fun CreditModel.toUIModel() : CreditUIModel{
    return CreditUIModel(creditReportInfo.score,creditReportInfo.maxScoreValue)
}