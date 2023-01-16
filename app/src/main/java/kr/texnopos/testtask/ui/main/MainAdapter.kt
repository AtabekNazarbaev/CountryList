package kr.texnopos.testtask.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.texnopos.testtask.R
import kr.texnopos.testtask.data.model.GenericResponse
import kr.texnopos.testtask.databinding.MainItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var onItemClick: (id: String) -> Unit = {}
    var model: List<GenericResponse> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceType")
        fun setPopulateModel(data: GenericResponse) {
            binding.apply {
                Glide.with(root.context)
                    .load(data.flags.png)
                    .into(ivFlag)
                tvCountryName.text = data.name
                constraint.setOnClickListener {
                    onItemClick.invoke(data.name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return ViewHolder(MainItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.setPopulateModel(model[position])
    }

    override fun getItemCount() = model.size
}
