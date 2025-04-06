package com.example.kotlin.dieselflow.framework.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.dieselflow.data.network.models.Usuario
import com.example.kotlin.dieselflow.databinding.ItemUsuarioBinding
import com.example.kotlin.dieselflow.framework.adapters.viewholders.MainViewHolder

class MainView(private var usuarios: List<Usuario>) : RecyclerView.Adapter<MainViewHolder>() {

    // Método para actualizar la lista de usuarios
    fun updateUsuarios(newUsuarios: List<Usuario>) {
        usuarios = newUsuarios
        notifyDataSetChanged() // Notifica al adaptador que la lista ha cambiado
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemUsuarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(usuarios[position]) // Pasa el objeto Usuario en lugar de una URL
    }

    override fun getItemCount(): Int = usuarios.size
}
