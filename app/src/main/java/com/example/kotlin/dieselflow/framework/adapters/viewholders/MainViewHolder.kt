package com.example.kotlin.dieselflow.framework.adapters.viewholders

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.dieselflow.databinding.ItemImageBinding

class MainViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(imageUrl: String) {
        Glide.with(binding.imageView.context)
            .load(imageUrl)
            .into(binding.imageView)

        // Log para verificar que la URL se está cargando correctamente
        Log.d("MainViewHolder", "Image URL: $imageUrl")
    }
}
