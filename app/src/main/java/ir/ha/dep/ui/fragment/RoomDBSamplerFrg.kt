package ir.ha.dep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentRoomDbSamplerBinding
import ir.ha.dep.repo.Contact
import ir.ha.dep.repo.RoomDB
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.showToast
import org.koin.android.ext.android.inject

class RoomDBSamplerFrg : BaseFragment() {

    private lateinit var binding  : FragmentRoomDbSamplerBinding
//    private val db  by inject<RoomDB>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_room_db_sampler,container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // insert
        binding.saveBtn.setOnClickListener{
            val con = Contact(binding.etFirstName.text.toString() , binding.etLastName.text.toString())
            RoomDB.database!!.contactDao().insertContact(con)
            showToast(requireContext(),Contact.fullName(con))
        }


        binding.readBtn.setOnClickListener{
            val contacts = RoomDB.database!!.contactDao().allContacts()
            if (contacts.isNotEmpty()){
                binding.etFirstName.setText(contacts[0].fName)
                binding.etLastName.setText(contacts[0].lName)
                showToast(requireContext(),Contact.fullName(contacts[0]))
            }else showToast(requireContext(),"آیتمی در دیتابیس یافت نشد")

        }
    }
}