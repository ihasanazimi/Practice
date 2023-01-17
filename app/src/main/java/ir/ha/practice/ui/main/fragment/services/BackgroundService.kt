package ir.ha.practice.ui.main.fragment.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import ir.ha.practice.utility.extentions.showToast
import timber.log.Timber

/* IntentService -> creating a worker thread for process in background */
/* Service -> use main thread for process in background */
class BackgroundService : Service()  {

    override fun onCreate() {
        super.onCreate()
        Timber.e("onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.tag("onStartCommand: ")
        showToast(this,intent?.extras?.getString("key").toString())
        stopSelf()
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy: ")


    }
}