package ir.ha.practice.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import ir.ha.practice.R
import ir.ha.practice.databinding.ItemAdapterFakeDataBinding
import ir.ha.practice.data.entities.FakeEntity

class FakeDataAdapter(private val callback : FakeDataEventListener) : RecyclerView.Adapter<FakeDataAdapter.VH>() {

    val list = arrayListOf<FakeEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemAdapterFakeDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener{
            callback.onFakeDataItemClickListener(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun setNewList(newList : List<FakeEntity>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearList(){
        list.clear()
        notifyDataSetChanged()
    }


    inner class VH(binding: ItemAdapterFakeDataBinding) : RecyclerView.ViewHolder(binding.root){
        private var image : ImageView = binding.iv
        private var name : TextView = binding.tv
        fun bind(model : FakeEntity){

            name.text = model.fileName
            image.load(model.imageUrl){
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                error(R.drawable.ic_baseline_error_24)
                transformations(CircleCropTransformation()) // circle image
            }
        }
    }

    interface FakeDataEventListener{
        fun onFakeDataItemClickListener(fakedata : FakeEntity)
    }

}