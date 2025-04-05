package com.example.kotlin.dieselflow.framework.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.dieselflow.databinding.ItemImageBinding
import com.example.kotlin.dieselflow.framework.adapters.viewholders.MainViewHolder

class MainView(private var images: List<String>) : RecyclerView.Adapter<MainViewHolder>() {

    // Método para actualizar la lista de imágenes
    fun updateImages(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged() // Notifica al adaptador que la lista ha cambiado
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}
