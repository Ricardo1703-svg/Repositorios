package com.example.geniuskids

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val correoEditText = findViewById<EditText>(R.id.txtCorreo)
        val contraEditText = findViewById<EditText>(R.id.txtContraseña)
        val registrarseButton = findViewById<Button>(R.id.btnRegistro)

        registrarseButton.setOnClickListener {
            val email = correoEditText.text.toString()
            val password = contraEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d("RegistroActivity", "Registro exitoso")
                            val intent = Intent(this, AuthActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("RegistroActivity", "Error en el registro", task.exception)
                            Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
