package ir.ha.dummy.ui.fragment

import android.util.Log

class MultiThreadingClass : Thread()  {


    override fun run() {
        super.run()
        Log.e("hsn", "MultiThreadingClass  ->  " + Thread.currentThread().name)
    }


}


 open class MyRunnableThread : Runnable{

    override fun run() {
        Log.e("hsn", "MyRunnableThread  ->  " + Thread.currentThread().name)
    }

}