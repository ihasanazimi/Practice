package ir.ha.practice.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ha.practice.databinding.ItemAdapterProjectsBinding
import ir.ha.practice.data.entities.ProjectEntity
import ir.ha.practice.utility.util.IntentActionsUtil

class ProjectAdapter(val activity : Activity) : RecyclerView.Adapter<ProjectAdapter.VH>() {

    private val items = arrayListOf<ProjectEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemAdapterProjectsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    fun setNewList(newList : List<ProjectEntity>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearList(){
        items.clear()
        notifyDataSetChanged()
    }



    inner class VH(val binding: ItemAdapterProjectsBinding) : RecyclerView.ViewHolder(binding.root){

        val context : Context = binding.projectLogo.context

        fun bind(model : ProjectEntity){
            binding.projectName.text = model.name
            binding.projectLink.text = model.link
            Glide.with(context).load(model.projectIcon).into(binding.projectLogo)
            if (model.link == "") binding.projectLink.text = "this project not have website."
            if (model.link.startsWith("http")) binding.projectLink.setOnClickListener {
                IntentActionsUtil(activity).openWebSite(model.link)
            }
        }

    }
}