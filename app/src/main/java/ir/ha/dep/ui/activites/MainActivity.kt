package ir.ha.dep.ui.activites

import android.os.Bundle
import android.view.View
import ir.ha.dep.R
import ir.ha.dep.databinding.ActivityMainBinding
import ir.ha.dep.feacher.BaseActivity
import ir.ha.dep.ui.fragment.LoadImagesFragment

class MainActivity : BaseActivity(), View.OnClickListener {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_main)
        setContentView(binding.root)
        binding.btnLoadImages.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLoadImages -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainFrame,LoadImagesFragment(),"loadImagesTag")
                    .addToBackStack("loadImagesTag").commit()
            }





            else -> {}
        }
    }
}