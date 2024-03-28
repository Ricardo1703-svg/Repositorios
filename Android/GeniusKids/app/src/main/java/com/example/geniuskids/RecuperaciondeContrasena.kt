package com.example.geniuskids

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RecuperaciondeContrasena : AppCompatActivity() {
    private lateinit var CorreoS: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacionde_contrasena)


        val Recuperacion = findViewById<Button>(R.id.btnRegresarRecu)
        Recuperacion.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }


        CorreoS = findViewById(R.id.editTextCorreo)
        val Recuperar = findViewById<Button>(R.id.btnRecuperar)
        Recuperar.setOnClickListener {
            val email = CorreoS.text.toString().trim()
            if (email.isNotEmpty()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,
                                "Se ha enviado un correo electrónico para restablecer la contraseña",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()

                        } else {
                            Toast.makeText(
                                this,
                                "Error al enviar el correo electrónico para restablecer la contraseña",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Por favor, ingresa tu dirección de correo electrónico", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}