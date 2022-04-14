package ir.ha.myapplication.feacher.main

import android.os.Bundle
import ir.ha.myapplication.feacher.BaseActivity
import ir.ha.myapplication.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.frameee, SampleFragment() , null).commit()
    }
}