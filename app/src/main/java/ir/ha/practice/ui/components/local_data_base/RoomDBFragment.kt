package ir.ha.practice.ui.components.local_data_base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentRoomBinding
import ir.ha.practice.adapters.UserAdapter
import ir.ha.practice.ui.components.local_data_base.room.core.RoomDB
import ir.ha.practice.data.entities.PersonEntity
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast
import org.koin.android.ext.android.inject


class RoomDBFragment : BaseFragment<FragmentRoomBinding>(),
    UserAdapter.ContactEventListener {

    override val layoutId: Int get() = R.layout.fragment_room

    private lateinit var userAdapter : UserAdapter
    private val db  by inject<RoomDB>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showContacts()
    }


    override fun registerListeners() {
        super.registerListeners()

        // insert
        binding.saveBtn.setOnClickListener{
            val personEntity = PersonEntity(binding.etFirstName.text.toString() , binding.etLastName.text.toString())
            db.userDao().insertContact(personEntity)
            showToast(requireContext(), PersonEntity.fullName(personEntity))
            showContacts()
        }


        binding.readBtn.setOnClickListener{
            val contacts = db.userDao().getAllUser()
            if (contacts.isNotEmpty()){
                binding.etFirstName.setText(contacts[0].fName)
                binding.etLastName.setText(contacts[0].lName)
                showToast(requireContext(), PersonEntity.fullName(contacts[0]))
                showContacts()
            }else showToast(requireContext(),"not found item of DataBase")
        }
    }

    private fun showContacts() {
        arrayListOf<PersonEntity>().apply {

            clear()
            addAll(db.userDao().getAllUser())

            userAdapter = UserAdapter(this@RoomDBFragment)
            binding.rv.apply {
                adapter = userAdapter
                // set vertical orientation for recyclerview
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                // set divider for items
                addItemDecoration(DividerItemDecoration(requireContext(), (layoutManager as LinearLayoutManager).orientation ))
            }
            userAdapter.setNewList(this)
        }
    }

    override fun onContactClickListener(contact: PersonEntity, position : Int) {
        showToast(requireContext(),contact.fName.toString() + "  item deleted")
        db.userDao().deleteContact(contact)
        userAdapter.removeItem(contact)
    }
}