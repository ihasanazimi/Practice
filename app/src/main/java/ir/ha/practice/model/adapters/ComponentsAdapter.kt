package ir.ha.practice.model.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.ha.practice.databinding.ItemSimpleTagBinding

class ComponentsAdapter : RecyclerView.Adapter<ComponentsAdapter.VH>() {

    private val items = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemSimpleTagBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    fun setNewList(newList : List<String>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearList(){
        items.clear()
        notifyDataSetChanged()
    }



    inner class VH(val binding: ItemSimpleTagBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(tag : String){
            binding.tagTv.text = tag
        }

    }
}