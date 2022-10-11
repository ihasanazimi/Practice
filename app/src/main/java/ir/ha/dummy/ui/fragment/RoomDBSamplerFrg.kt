package ir.ha.dummy.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentRoomDbSamplerBinding
import ir.ha.dummy.model.ContactModel
import ir.ha.dummy.model.adapters.ContactAdapter
import ir.ha.dummy.repo.RoomDB
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.showToast


class RoomDBSamplerFrg : BaseFragment<FragmentRoomDbSamplerBinding>(), ContactAdapter.ContactEventListener {

    override val layoutId: Int get() = R.layout.fragment_room_db_sampler

    private lateinit var contactAdapter : ContactAdapter
//    private val db  by inject<RoomDB>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showContacts()


        // insert
        binding.saveBtn.setOnClickListener{
            val contactModel = ContactModel(binding.etFirstName.text.toString() , binding.etLastName.text.toString())
            RoomDB.database!!.contactDao().insertContact(contactModel)
            showToast(requireContext(), ContactModel.fullName(contactModel))
            showContacts()
        }


        binding.readBtn.setOnClickListener{
            val contacts = RoomDB.database!!.contactDao().allContacts()
            if (contacts.isNotEmpty()){
                binding.etFirstName.setText(contacts[0].fName)
                binding.etLastName.setText(contacts[0].lName)
                showToast(requireContext(), ContactModel.fullName(contacts[0]))
                showContacts()
            }else showToast(requireContext(),"آیتمی در دیتابیس یافت نشد")
        }
    }

    private fun showContacts() {
        arrayListOf<ContactModel>().apply {

            clear()
            addAll(RoomDB.database!!.contactDao().allContacts())

            contactAdapter = ContactAdapter(this@RoomDBSamplerFrg)
            binding.rv.apply {
                adapter = contactAdapter
                // set vertical orientation for recyclerview
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                // set divider for items
                addItemDecoration(DividerItemDecoration(requireContext(), (layoutManager as LinearLayoutManager).orientation ))
            }
            contactAdapter.setNewList(this)
        }
    }

    override fun onContactClickListener(contact: ContactModel, position : Int) {
        showToast(requireContext(),contact.fName.toString() + "  item deleted")
        RoomDB.database!!.contactDao().deleteContact(contact)
        contactAdapter.removeItem(contact)
    }
}