package com.example.kotlin.dieselflow.framework.adapters.viewholders

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.dieselflow.data.network.models.Usuario
import com.example.kotlin.dieselflow.databinding.ItemUsuarioBinding

class MainViewHolder(private val binding: ItemUsuarioBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(usuario: Usuario) {
        // Mostrar otros datos
        binding.tvUsername.text = usuario.username
        binding.tvLastname.text = usuario.lastName
        binding.tvEmail.text = usuario.email

        Log.d("MainViewHolder", "Usuario: ${usuario.username} - ${usuario.email}")
    }
}
