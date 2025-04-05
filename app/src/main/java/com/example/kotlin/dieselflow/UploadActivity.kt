package com.example.kotlin.dieselflow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.kotlin.dieselflow.framework.viewmodels.UploadViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UploadActivity : ComponentActivity() {

    private lateinit var imageView: ImageView
    private lateinit var uploadButton: Button
    private var imageFile: File? = null
    private lateinit var imageUri: Uri

    // Usamos el ViewModel para manejar la lógica de la subida de la imagen
    private val viewModel: UploadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        imageView = findViewById(R.id.imagePreview)
        uploadButton = findViewById(R.id.uploadButton)

        // Recibe la URI de la imagen tomada (desde la cámara)
        imageUri = Uri.parse(intent.getStringExtra("imageUri"))

        // Muestra la imagen tomada en el ImageView
        Glide.with(this).load(imageUri).into(imageView)

        // Subir imagen
        uploadButton.setOnClickListener {
            imageFile = getFileFromUri(imageUri)  // Método para obtener el archivo de la URI
            imageFile?.let { file ->
                uploadImage(file)
            } ?: Toast.makeText(this, "Selecciona una imagen primero", Toast.LENGTH_SHORT).show()
        }

        observeUploadState()
    }

    private fun getFileFromUri(uri: Uri): File? {
        val filePathColumn = arrayOf(android.provider.MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, filePathColumn, null, null, null)
        cursor?.let {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(filePathColumn[0])
            val filePath = it.getString(columnIndex)
            it.close()
            return File(filePath)
        }
        return null
    }

    private fun uploadImage(file: File) {
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        // Usamos el ViewModel para manejar la subida
        viewModel.uploadImage(body)
    }

    private fun observeUploadState() {
        lifecycleScope.launch {
            viewModel.uploadState.collectLatest { result ->
                result?.onSuccess {
                    if (it.isSuccessful) {
                        Toast.makeText(this@UploadActivity, "Imagen subida correctamente", Toast.LENGTH_SHORT).show()

                        // Volver a iniciar MainActivity para que se recargue con la nueva imagen
                        val intent = Intent(this@UploadActivity, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@UploadActivity, "Error al subir", Toast.LENGTH_SHORT).show()
                    }
                }?.onFailure { e ->
                    Toast.makeText(this@UploadActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e("UploadActivity", "Error: ${e.message}")
                }
            }
        }
    }
}







