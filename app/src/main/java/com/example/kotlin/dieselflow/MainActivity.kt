package com.example.kotlin.dieselflow

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.dieselflow.databinding.ActivityMainBinding
import com.example.kotlin.dieselflow.framework.view.MainView
import com.example.kotlin.dieselflow.framework.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainView

    private lateinit var imageUri: Uri

    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                // La imagen se ha capturado con éxito
                val intent = Intent(this, UploadActivity::class.java)
                intent.putExtra("imageUri", imageUri.toString())  // Pasa la URI de la foto
                startActivity(intent)
            } else {
                // Error al capturar la imagen
                Log.e("MainActivity", "Error al tomar la foto")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa el adapter con una lista vacía de imágenes al principio
        adapter = MainView(emptyList())

        // Verificar permisos para la cámara
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Solicitar permiso si no está concedido
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        }

        // Configura el RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observa los cambios en la lista de imágenes
        lifecycleScope.launchWhenStarted {
            viewModel.images.collectLatest { imageList ->
                adapter.updateImages(imageList)  // Actualiza la lista de imágenes en el adapter
            }
        }

        // Llama a la función para cargar las imágenes
        viewModel.fetchImages()

        // Abre la cámara cuando se presiona el botón
        binding.btnGoToUpload.setOnClickListener {
            // Prepara la URI para almacenar la imagen tomada
            val values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, "Nueva Foto")
            imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
            // Lanza la cámara para tomar la foto
            takePictureLauncher.launch(imageUri)
        }
    }

    // Verificar si el permiso ha sido concedido
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes continuar
                Log.d("MainActivity", "Permiso para la cámara concedido")
            } else {
                // Permiso denegado
                Log.e("MainActivity", "Permiso para la cámara denegado")
            }
        }
    }

    companion object {
        const val CAMERA_PERMISSION_REQUEST_CODE = 100
    }
}

