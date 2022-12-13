package com.krolikowski.dook.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krolikowski.dook.databinding.RecyclerViewItemBinding
import com.krolikowski.dook.extensions.loadFromUrl
import com.krolikowski.dook.networking.entities.ImageEntity

class ImagesAdapter(
    var images: List<ImageEntity> = listOf(),
    val onItemClick: ((ImageEntity) -> Unit)
) :
    RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = images[position]

        with(holder) {
            binding.apply {
                nasaTitle.text = item.title
                nasaImage.loadFromUrl(item.imageUrl)
            }
        }
    }

    override fun getItemCount() = images.size

    inner class ImageViewHolder(val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(images[adapterPosition])
            }
        }
    }
}