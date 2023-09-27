package ir.ha.practice.model.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.ha.practice.databinding.ItemAdapterSimpleTagBinding

class DeveloperTagsAdapter : RecyclerView.Adapter<DeveloperTagsAdapter.VH>() {

    private val items = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemAdapterSimpleTagBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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



    inner class VH(val binding: ItemAdapterSimpleTagBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(tag : String){
            binding.tagTv.text = tag
        }

    }
}