package com.example.kotlin.dieselflow.framework.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.dieselflow.data.network.models.Usuario
import com.example.kotlin.dieselflow.data.repositories.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository = MainRepository() // Aquí se inyecta el repositorio
) : ViewModel() {

    // Lista de usuarios que se observa desde la UI
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> get() = _usuarios

    // Función para obtener los usuarios
    fun fetchUsuarios() {
        viewModelScope.launch {
            try {
                val usuarioList = mainRepository.getUsers() // Llama al repositorio para obtener los usuarios
                Log.d("MainViewModel", "Fetched Usuarios: $usuarioList")
                _usuarios.value = usuarioList // Actualiza el estado con la lista de usuarios
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching usuarios: ${e.message}")
                _usuarios.value = emptyList() // En caso de error, se limpia la lista
            }
        }
    }
}


