package ir.ha.practice.ui.main.fragment.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentRecyclerViewSampleBinding
import ir.ha.practice.model.FakePojo
import ir.ha.practice.repo.FakeDataGenerator
import ir.ha.practice.utility.base.BaseFragment

class RecyclerViewFragment : BaseFragment<FragmentRecyclerViewSampleBinding>(){
    override val layoutId: Int
        get() = R.layout.fragment_recycler_view_sample

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = FakeDataAdapter(object : FakeDataAdapter.FakeDataEventListener{
            override fun onFakeDataItemClickListener(fakedata: FakePojo) {
                Toast.makeText(requireContext(), fakedata.fileName, Toast.LENGTH_SHORT).show()
            }
        })
        adapter.setNewList(FakeDataGenerator.getDataFakeGenerator().toList())
        binding.rv.adapter = adapter
    }



}