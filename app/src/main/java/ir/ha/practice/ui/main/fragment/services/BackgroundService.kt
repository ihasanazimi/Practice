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
        Log.i(this@BackgroundService::class.java.simpleName, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(this@BackgroundService::class.java.simpleName, "onStartCommand")
        showToast(this,intent?.extras?.getString("key").toString())
        /* stopSelf() */ /* when process is finished com in stop service */
        return START_STICKY
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

    override fun onDestroy() {
        super.onDestroy()
        Log.i(this@BackgroundService::class.java.simpleName, "onDestroy")
        stopSelf()
    }
}