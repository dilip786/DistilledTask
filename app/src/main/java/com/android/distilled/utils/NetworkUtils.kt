package com.android.distilled.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.android.distilled.DistilledApplication

class NetworkUtils {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            DistilledApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    }
}