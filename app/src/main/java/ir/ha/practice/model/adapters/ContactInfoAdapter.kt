package ir.ha.practice.model.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ir.ha.practice.databinding.ItemSimpleTagBinding
import ir.ha.practice.model.ContactInfoByIcon
import ir.ha.practice.model.ContactInfoEnum
import ir.ha.practice.utility.util.IntentActionsUtil

class ContactInfoAdapter(val activity : Activity) : RecyclerView.Adapter<ContactInfoAdapter.VH>() {

    private val items = arrayListOf<ContactInfoByIcon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemSimpleTagBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    fun setNewList(newList : List<ContactInfoByIcon>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearList(){
        items.clear()
        notifyDataSetChanged()
    }



    inner class VH(val binding: ItemSimpleTagBinding) : RecyclerView.ViewHolder(binding.root){

        val context : Context = binding.tagTv.context
        fun bind(m : ContactInfoByIcon){

            binding.tagTv.text = m.addressId
            binding.tagTv.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(activity, m.addressIcon), null,null, null)

            when(m.typeEnum){
                ContactInfoEnum.call -> binding.tagTv.setOnClickListener { IntentActionsUtil(activity).callPhoneNumber(m.addressId) }
                ContactInfoEnum.email -> binding.tagTv.setOnClickListener { IntentActionsUtil(activity).sendEmail(m.addressId) }
                ContactInfoEnum.linkedin -> binding.tagTv.setOnClickListener { IntentActionsUtil(activity).openLinkedInPage(m.addressId) }
                ContactInfoEnum.telegram -> binding.tagTv.setOnClickListener { IntentActionsUtil(activity).shareMessageToTelegram(m.addressId) }
                else->{}
            }
        }

    }
}