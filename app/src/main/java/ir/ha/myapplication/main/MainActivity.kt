package ir.ha.myapplication.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.ha.myapplication.BaseActivity
import ir.ha.myapplication.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.frame, SampleFragment() , null).commit()
    }
}