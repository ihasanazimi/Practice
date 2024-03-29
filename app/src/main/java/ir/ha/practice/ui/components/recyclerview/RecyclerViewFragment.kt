package ir.ha.practice.ui.components.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ha.practice.FakeDataGenerator
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentRvBinding
import ir.ha.practice.data.entities.FakeEntity
import ir.ha.practice.adapters.FakeDataAdapter
import ir.ha.practice.utility.base.BaseFragment

class RecyclerViewFragment : BaseFragment<FragmentRvBinding>(){
    override val layoutId: Int
        get() = R.layout.fragment_rv

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = FakeDataAdapter(object : FakeDataAdapter.FakeDataEventListener {
            override fun onFakeDataItemClickListener(fakedata: FakeEntity) {
                Toast.makeText(requireContext(), fakedata.fileName, Toast.LENGTH_SHORT).show()
            }
        })

        adapter.setNewList(FakeDataGenerator.getDataFakeGenerator().toList())
        binding.rv.adapter = adapter
    }



}