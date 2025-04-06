package com.example.kotlin.dieselflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.dieselflow.databinding.ActivityMainBinding
import com.example.kotlin.dieselflow.framework.view.MainView
import com.example.kotlin.dieselflow.framework.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa el adapter con una lista vacía de usuarios al principio
        adapter = MainView(emptyList())

        // Configura el RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observa los cambios en la lista de usuarios
        lifecycleScope.launchWhenStarted {
            viewModel.usuarios.collectLatest { usuarios ->
                // Actualiza la lista de usuarios en el adaptador
                adapter.updateUsuarios(usuarios)
            }
        }

        // Llama a la función para cargar los usuarios
        viewModel.fetchUsuarios()
    }
}


