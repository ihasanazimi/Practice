package ir.ha.dummy.ui.fragment.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import ir.ha.dummy.R
import ir.ha.dummy.utility.extentions.isOreoPlus
import ir.ha.dummy.utility.extentions.showToast

class ForegroundService : Service()  {

    override fun onCreate() {
        super.onCreate()
        Log.i("hsn", "onCreate: " )

    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showToast(this,intent?.extras?.getString("key")+"")
        Log.i("hsn", "onStartCommand: " )
        startForeground(1001,notification())

        Thread.sleep(2000)
        stopForeground(true)

        return START_NOT_STICKY
    }


    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    fun notification () :  Notification {
        val notificationChannelID = "notificationChannelID"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (isOreoPlus()){
            val notificationChannel = NotificationChannel("notificationChannelID", getString(R.string.app_name),NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notification = NotificationCompat.Builder(this,notificationChannelID)
            .setContentTitle("Notification Title")
            .setContentText("this is notification description and text")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.baner))
            .build()

        return notification
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.i("hsn", "onDestroy: " )
        stopForeground(true)

    }
}