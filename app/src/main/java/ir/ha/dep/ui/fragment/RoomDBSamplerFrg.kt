package ir.ha.dep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentRoomDbSamplerBinding
import ir.ha.dep.model.adapters.ContactAdapter
import ir.ha.dep.repo.ContactModel
import ir.ha.dep.repo.RoomDB
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.showToast
import retrofit2.http.POST


class RoomDBSamplerFrg : BaseFragment(), ContactAdapter.ContactEventListener {

    private lateinit var binding  : FragmentRoomDbSamplerBinding
    private lateinit var contactAdapter : ContactAdapter
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

        showContacts()


        // insert
        binding.saveBtn.setOnClickListener{
            val contactModel = ContactModel(binding.etFirstName.text.toString() , binding.etLastName.text.toString())
            RoomDB.database!!.contactDao().insertContact(contactModel)
            showToast(requireContext(),ContactModel.fullName(contactModel))
            showContacts()
        }


        binding.readBtn.setOnClickListener{
            val contacts = RoomDB.database!!.contactDao().allContacts()
            if (contacts.isNotEmpty()){
                binding.etFirstName.setText(contacts[0].fName)
                binding.etLastName.setText(contacts[0].lName)
                showToast(requireContext(),ContactModel.fullName(contacts[0]))
                showContacts()
            }else showToast(requireContext(),"آیتمی در دیتابیس یافت نشد")
        }
    }

    private fun showContacts() {
        val contactList = arrayListOf<ContactModel>()
        contactList.clear()
        contactList.addAll(RoomDB.database!!.contactDao().allContacts())
        contactAdapter = ContactAdapter(this)
        binding.rv.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            contactAdapter.setNewList(contactList)
        }

        // set divider for items
        binding.rv.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
    }

    override fun onContactClickListener(contact: ContactModel , position : Int) {
        showToast(requireContext(),contact.fName.toString() + "  item deleted")
        RoomDB.database!!.contactDao().deleteContact(contact)
        contactAdapter.removeItem(contact)
    }
}