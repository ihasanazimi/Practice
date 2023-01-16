package ir.ha.practice.ui.main.fragment.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import ir.ha.practice.utility.extentions.showToast

class BoundService : Service()  {
    val myBinder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.i("services_lifecycle_tag", "onCreate: " )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showToast(this,intent?.extras?.getString("key")+"")
        Log.i("services_lifecycle_tag", "onStartCommand: " )
        Thread.sleep(1000)
        stopSelf()
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return myBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("services_lifecycle_tag", "onDestroy: " )
    }

    companion object {
        class MyBinder : Binder() {
            fun getServiceInstance(): MyBinder {
                return MyBinder()
            }
            fun getTestMessage() : String {
                return "Hi Hasan Azimi"
            }
        }
    }
}