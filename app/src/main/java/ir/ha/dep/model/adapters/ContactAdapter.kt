package ir.ha.dep.model.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import ir.ha.dep.R
import ir.ha.dep.databinding.ItemFakeDataBinding
import ir.ha.dep.model.FakeDataModel
import ir.ha.dep.repo.ContactModel

class ContactAdapter(val callback : ContactEventListener) : RecyclerView.Adapter<ContactAdapter.VH>() {

    val list = arrayListOf<ContactModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemFakeDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener{
            callback.onContactClickListener(list[position] , position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun setNewList(newList : List<ContactModel>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeItem(contact: ContactModel){
        list.remove(list.find{ it == contact })
        notifyDataSetChanged()
    }

    fun clearList(){
        list.clear()
        notifyDataSetChanged()
    }


    inner class VH(binding: ItemFakeDataBinding) : RecyclerView.ViewHolder(binding.root){
        private var image : ImageView = binding.iv
        private var name : TextView = binding.tv
        fun bind(model : ContactModel){
            name.text = model.fName
            image.load(R.drawable.ic_online){
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                error(R.drawable.ic_baseline_error_24)
                transformations(CircleCropTransformation()) // circle image
            }
        }
    }

    interface ContactEventListener{
        fun onContactClickListener(contact : ContactModel , position: Int)
    }

}