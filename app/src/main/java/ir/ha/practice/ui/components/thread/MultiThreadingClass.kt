package ir.ha.practice.ui.components.thread

import android.util.Log

class MultiThreadingClass : Thread()  {
    override fun run() {
        super.run()
        Log.e("multi_threading", "MultiThreadingClass  ->  " + Thread.currentThread().name)
    }
}

 open class MyRunnableThread : Runnable{
    override fun run() { Log.e("multi_threading", "MyRunnableThread  ->  " + Thread.currentThread().name) }
}