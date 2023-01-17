package ir.ha.practice.ui.main.fragment.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import ir.ha.practice.utility.extentions.showToast

class BoundService : Service()  {

    private val myBinder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.i(this@BoundService::class.java.simpleName, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(this@BoundService::class.java.simpleName, "onStartCommand")
        showToast(this,intent?.extras?.getString("key").toString())
        Thread.sleep(1000)
        stopSelf()
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return myBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(this@BoundService::class.java.simpleName, "onDestroy")
    }

    companion object {
        class MyBinder : Binder() {
            fun getServiceInstance(): MyBinder {
                return MyBinder()
            }
            fun getTestMessage() : String {
                return "Hi Hasan Azimi (Bound Service)"
            }
        }
    }
}