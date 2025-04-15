package com.example.kotlin.dieselflow

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.dieselflow.framework.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa el ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Referencias a los campos de entrada
        val user = findViewById<EditText>(R.id.etUser)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        // Configura el botón de login
        loginButton.setOnClickListener {
            val userText = user.text.toString()
            val passwordText = password.text.toString()
            val type = "Conductor"

            // Manejar el caso de que falten datos
            if (userText.isBlank() || passwordText.isBlank()) {
                Toast.makeText(this,
                    "Por favor, ingresa tus credenciales",
                    Toast.LENGTH_SHORT).show()
                Log.e("Login", "Email o contraseña vacíos")
                return@setOnClickListener
            }

            // Llama al método de login del ViewModel
            viewModel.login(userText, passwordText, type)
        }

        // Observa condiciones del login
        viewModel.loginSuccess.observe(this) { success ->
            if (success) {
                Toast.makeText(this,
                    "Login exitoso!",
                    Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this,
                    "Error en la solicitud, por favor intenta de nuevo",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
}



