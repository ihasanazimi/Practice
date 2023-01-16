package ir.ha.practice.ui.main.fragment.db

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import ir.ha.practice.R
import ir.ha.practice.databinding.ItemFakeDataBinding
import ir.ha.practice.ui.main.fragment.db.room.entity.User

class UserAdapter(val callback : ContactEventListener) : RecyclerView.Adapter<UserAdapter.VH>() {

    val list = arrayListOf<User>()

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


    fun setNewList(newList : List<User>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeItem(contact: User){
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
        fun bind(model : User){
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
        fun onContactClickListener(contact : User, position: Int)
    }

}