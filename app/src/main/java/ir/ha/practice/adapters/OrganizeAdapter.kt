package ir.ha.practice.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ha.practice.databinding.ItemAdapterOrganizeBinding
import ir.ha.practice.model.entities.OrganizeEntity
import ir.ha.practice.utility.extentions.hide

class OrganizeAdapter(val activity : Activity) : RecyclerView.Adapter<OrganizeAdapter.VH>() {

    private val items = arrayListOf<OrganizeEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemAdapterOrganizeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    fun setNewList(newList : List<OrganizeEntity>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearList(){
        items.clear()
        notifyDataSetChanged()
    }



    inner class VH(val binding: ItemAdapterOrganizeBinding) : RecyclerView.ViewHolder(binding.root){

        val adapter = ProjectAdapter(activity)

        init {
            binding.projectRecyclerView.adapter = adapter
        }

        fun bind(model : OrganizeEntity){
            Glide.with(activity).load(model.organizeLogo).into(binding.circleImageView)
            binding.organizationName.text = model.organizeName
            binding.jobPosition.text = model.getPositionByType()
            binding.positionToPositionDateTime.text = model.getDistanceOfTowPosition()
            adapter.setNewList(model.projectEntities)
            if (model.projectEntities.isEmpty()) binding.t1.hide()
        }

    }
}