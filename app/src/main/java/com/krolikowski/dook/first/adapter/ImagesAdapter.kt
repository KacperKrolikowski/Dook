package com.krolikowski.dook.first.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krolikowski.dook.databinding.RecyclerViewItemBinding
import com.krolikowski.dook.networking.entities.ImageEntity

class ImagesAdapter(private val images: List<ImageEntity>) :
    RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = images[position]

        with(holder){
            binding.apply {
                nasaTitle.text = item.title
                Glide.with(holder.itemView)
                    .load(item.imageUrl)
                    .into(nasaImage)
            }
        }
    }

    override fun getItemCount() = images.size

    inner class ImageViewHolder(val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}