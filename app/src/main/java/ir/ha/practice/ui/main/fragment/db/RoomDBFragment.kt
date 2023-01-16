package ir.ha.practice.ui.main.fragment.db

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentRoomDbSamplerBinding
import ir.ha.practice.ui.main.fragment.db.room.entity.User
import ir.ha.practice.ui.main.fragment.db.room.RoomDB
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast
import org.koin.android.ext.android.inject


class RoomDBFragment : BaseFragment<FragmentRoomDbSamplerBinding>(), UserAdapter.ContactEventListener {

    override val layoutId: Int get() = R.layout.fragment_room_db_sampler

    private lateinit var userAdapter : UserAdapter
    private val db  by inject<RoomDB>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showContacts()

        // insert
        binding.saveBtn.setOnClickListener{
            val user = User(binding.etFirstName.text.toString() , binding.etLastName.text.toString())
            db.userDao().insertContact(user)
            showToast(requireContext(), User.fullName(user))
            showContacts()
        }


        binding.readBtn.setOnClickListener{
            val contacts = db.userDao().getAllUser()
            if (contacts.isNotEmpty()){
                binding.etFirstName.setText(contacts[0].fName)
                binding.etLastName.setText(contacts[0].lName)
                showToast(requireContext(), User.fullName(contacts[0]))
                showContacts()
            }else showToast(requireContext(),"not found item of DataBase")
        }
    }

    private fun showContacts() {
        arrayListOf<User>().apply {

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

    override fun onContactClickListener(contact: User, position : Int) {
        showToast(requireContext(),contact.fName.toString() + "  item deleted")
        db.userDao().deleteContact(contact)
        userAdapter.removeItem(contact)
    }
}