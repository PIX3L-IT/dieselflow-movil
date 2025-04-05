package com.example.kotlin.dieselflow.framework.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.dieselflow.domain.DieselFlowRequirement
import com.example.kotlin.dieselflow.domain.UploadImageRequirement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class MainViewModel(
    private val getImagesRequirement: DieselFlowRequirement = DieselFlowRequirement(),
    private val uploadImageRequirement: UploadImageRequirement = UploadImageRequirement()
) : ViewModel() {

    private val _images = MutableStateFlow<List<String>>(emptyList())
    val images: StateFlow<List<String>> get() = _images

    fun fetchImages() {
        viewModelScope.launch {
            try {
                val imageUrls = getImagesRequirement()
                Log.d("MainViewModel", "Fetched Images: $imageUrls")
                _images.value = imageUrls
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching images: ${e.message}")
                _images.value = emptyList()
            }
        }
    }

    private val _uploadState = MutableStateFlow<Result<Response<ResponseBody>>?>(null)
    val uploadState: StateFlow<Result<Response<ResponseBody>>?> = _uploadState

    fun uploadImage(file: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                val response = uploadImageRequirement(file)
                _uploadState.value = Result.success(response)
            } catch (e: Exception) {
                _uploadState.value = Result.failure(e)
            }
        }
    }
}

