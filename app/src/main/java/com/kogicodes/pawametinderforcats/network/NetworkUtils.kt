
/*
 * Copyright 2019 Eric Kogi. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.sql.DriverManager.println

class NetworkUtils {
    companion object {

        private fun getNetworkInfo(context: Context): NetworkInfo? {
            return try {
                val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                cm.activeNetworkInfo
            } catch (e: Exception) {
                println("CheckConnectivity Exception: " + e.message)
                null
            }
        }

        fun isConnectedToWifi(context: Context): Boolean {
            val info = getNetworkInfo(context)
            return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
        }

        fun isConnectedToMobile(context: Context): Boolean {
            val info = getNetworkInfo(context)
            return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
        }

        fun isConnected(context: Context, connectionType: Int): Boolean {
            when (connectionType) {
                ConnectivityManager.TYPE_WIFI -> return isConnectedToWifi(context)
                ConnectivityManager.TYPE_MOBILE -> return isConnectedToMobile(context)
                else -> return isConnected(context)
            }
        }

        @Deprecated("")
        fun isOnline(context: Context): Boolean {
            return isConnected(context)
        }

        fun isConnected(context: Context): Boolean {
            val info = getNetworkInfo(context)
            return info != null && info.isConnectedOrConnecting
        }

        private val TAG = NetworkUtils::class.java.name
        fun hasInternetConnection(context: Context): Boolean {
            if (isConnected(context)) {
                try {
                    val urlc = URL("http://clients3.google.com/generate_204").openConnection() as HttpURLConnection
                    urlc.setRequestProperty("User-Agent", "Android")
                    urlc.setRequestProperty("Connection", "close")
                    urlc.connectTimeout = 1500
                    urlc.connect()
                    return urlc.responseCode === 204
                } catch (e: IOException) {
                    Log.e(TAG, "Error checking internet connection", e)
                }
            } else {
                Log.d(TAG, "No network available!")
            }
            return false
        }
    }
}
