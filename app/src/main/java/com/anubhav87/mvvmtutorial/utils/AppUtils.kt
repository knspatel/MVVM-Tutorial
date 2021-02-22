package com.anubhav87.mvvmtutorial.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.gson.Gson


/**
 * Created By: dev1618
 * Created Date: 6/19/2019
 * Purpose: This object will contains all the utility methods needed to the specific app.
 */
object AppUtils {

    /**
     * Created By: dev1618
     * Created Date: 6/19/2019
     * Purpose: Function to check network connection.
     */

    fun hasNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return (activeNetwork != null && activeNetwork.isConnected)
    }

    /**
     * Create a Notification that is shown as a heads-up notification if possible.
     *
     * @param message Message shown on the notification
     * @param context Context needed to create Toast
     */
//    fun makeStatusNotification(message: String, context: Context) {
//
//        // Create the notification
//        val builder =
//            NotificationCompat.Builder(context, Constants.NotificationConstants.CHANNEL_ID)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(Constants.NotificationConstants.NOTIFICATION_TITLE)
//                .setContentText(message)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setVibrate(LongArray(0))
//
//        // Show the notification
//        NotificationManagerCompat.from(context)
//            .notify(Constants.NotificationConstants.NOTIFICATION_ID, builder.build())
//    }
}

//inline fun <reified T : Any> T.toJson(indentSpaces: Int? = 4): String? =
//    Gson().toJson(this, T::class.java).formatJson(indentSpaces)