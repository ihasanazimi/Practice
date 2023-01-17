package ir.ha.practice.ui.main.fragment.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import ir.ha.practice.R
import ir.ha.practice.utility.extentions.isOreoPlus
import ir.ha.practice.utility.extentions.showToast

class ForegroundService : Service()  {

    private val channelID = "PRACTICE_PRJ"
    private val notificationID = 13760702

    override fun onCreate() {
        super.onCreate()
        Log.i(this@ForegroundService::class.java.simpleName, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(this@ForegroundService::class.java.simpleName, "onStartCommand")
        startForeground(notificationID,notification())
        showToast(this,intent?.extras?.getString("key").toString())
        return START_NOT_STICKY
        /* START_STICKY :   */
        /* START_NOT_STICKY :   */
        /* START_REDELIVER_INTENT :   */
        /* START_STICKY_COMPATIBILITY :   */
        /* START_TASK_REMOVED_COMPLETE :   */
        /* START_FLAG_REDELIVERY :   */
        /* START_FLAG_RETRY :   */
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun notification(): Notification {

        val nManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // first create notification channel
        if (isOreoPlus()) {
            val nChannel = NotificationChannel(channelID, getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH)
            nManager.createNotificationChannel(nChannel)
        }

        // create notification
        return NotificationCompat.Builder(this, channelID)
            .setContentTitle("Notification Title")
            .setContentText("this is notification description")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.baner))
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(this@ForegroundService::class.java.simpleName, "onDestroy")
        stopForeground(true)
    }
}