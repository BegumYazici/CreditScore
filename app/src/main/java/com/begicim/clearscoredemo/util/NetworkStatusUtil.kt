package com.begicim.clearscoredemo.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.begicim.clearscoredemo.R
import com.begicim.clearscoredemo.ui.ApiStatus
import javax.inject.Inject

class NetworkStatusUtil @Inject constructor() {
    fun bindApiStatusImage(
        progressBarGroup: View,
        loadingStatusGroup: View,
        statusImage: ImageView,
        apiStatus: ApiStatus?
    ) {
        apiStatus?.let { apiStatus ->
            when (apiStatus) {
                ApiStatus.ERROR -> {
                    progressBarGroup.visibility = View.GONE
                    loadingStatusGroup.visibility = View.GONE
                    statusImage.visibility = View.VISIBLE
                    statusImage.setImageResource(R.drawable.ic_connection_error)
                }
                ApiStatus.SUCCESS -> {
                    progressBarGroup.visibility = View.VISIBLE
                    loadingStatusGroup.visibility = View.GONE
                    statusImage.visibility = View.GONE
                }
                ApiStatus.LOADING -> {
                    progressBarGroup.visibility = View.GONE
                    loadingStatusGroup.visibility = View.VISIBLE
                    statusImage.visibility = View.VISIBLE
                    statusImage.setImageResource(R.drawable.loading_animation)
                }
            }
        }
    }
}