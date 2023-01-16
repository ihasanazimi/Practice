package ir.ha.practice.ui.main.fragment.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import ir.ha.practice.utility.extentions.showToast

/* IntentService -> creating a worker thread for process in background */
/* Service -> use main thread for process in background */
class BackgroundService : Service()  {

    override fun onCreate() {
        super.onCreate()
        Log.i("services_lifecycle_tag", "onCreate: " )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showToast(this,intent?.extras?.getString("key")+"")
        Log.i("services_lifecycle_tag", "onStartCommand: " )
        stopSelf()
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("services_lifecycle_tag", "onDestroy: " )

    }
}