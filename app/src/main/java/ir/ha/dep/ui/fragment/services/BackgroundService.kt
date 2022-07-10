package ir.ha.dep.ui.fragment.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import ir.ha.dep.utility.extentions.showToast

// intent service -> in karesh ine k khodesh vasat ye thread misaze
// service : az mian thread estefade mikone

class BackgroundService : Service()  {


    override fun onCreate() {
        super.onCreate()
        Log.i("hsn", "onCreate: " )

    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showToast(this,intent?.extras?.getString("key")+"")
        Log.i("hsn", "onStartCommand: " )
        stopSelf()
        return START_STICKY
    }


    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.i("hsn", "onDestroy: " )

    }
}