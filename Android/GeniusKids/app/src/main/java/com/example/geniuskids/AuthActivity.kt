package com.example.geniuskids

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthActivity : AppCompatActivity() {
    private val GOOGLE_SIGN_IN =100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Analytics
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de Firebase Completa")
        analytics.logEvent("InitScreen", bundle)

        //------------------------------------------Correo-------------------------------------------------------------------------
        //Campos
        val correo = findViewById<EditText>(R.id.txtCorreo)
        val contra = findViewById<EditText>(R.id.txtContrasena)

        //------------------Ingresar-----------------------------------------
        val ingresar = findViewById<Button>(R.id.btnIngresar)
        ingresar.setOnClickListener {
            val email = correo.text.toString()
            val password = contra.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d("AuthActivity", "Inicio de sesión exitoso")
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            // Datos guardados
                            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
                            val editor = prefs.edit()
                            editor.putString("correo", email)
                            editor.putString("contra", password)
                            editor.apply()

                        } else {
                            Log.w("AuthActivity", "Error en el inicio de sesión", task.exception)
                            Toast.makeText(this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
        //------------------Registro-----------------------------------------
        val registrarse = findViewById<Button>(R.id.btnRegistro)
        registrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        //--------------------------------------------------------------------

        //------------------Restablecer------------------------------------------
        val Restablecer1 = findViewById<Button>(R.id.btnRestablecer1)
        Restablecer1.setOnClickListener{
            val intent = Intent(this, RecuperaciondeContrasena::class.java)
            startActivity(intent)
        }
        //------------------------------------------------------------------------

        //------------------------------------------------Anonimo--------------------------------------------------------------------------
        val Anonimo = findViewById<ImageButton>(R.id.Anonimo)
        Anonimo.setOnClickListener {
            FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("AnonymousLoginActivity", "Inicio de sesión anónimo exitoso")
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.w("AnonymousLoginActivity", "Error en el inicio de sesión anónimo", task.exception)
                        Toast.makeText(this, "Error en el inicio de sesión anónimo", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        //---------------------------------------------------------------------------------------------------------------------------------
        //------------------Google-----------------------------------------
        val Google = findViewById<SignInButton>(R.id.Google)
        Google.setOnClickListener {
            val googleConf= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this,googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent,GOOGLE_SIGN_IN)
        }
        //-----------------------------------------------------------------
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val cuenta = task.getResult(ApiException::class.java)

                if (cuenta != null){
                    val credenciales = GoogleAuthProvider.getCredential(cuenta.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credenciales)
                        .addOnCompleteListener(this) { authTask ->
                            if (authTask.isSuccessful) {
                                Log.d("AuthActivity", "Inicio de sesión con Google exitoso")
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Log.w("AuthActivity", "Error en el inicio de sesión con Google", authTask.exception)
                                Toast.makeText(this, "Error en el inicio de sesión con Google", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            } catch (e: ApiException){
                Log.e("AuthActivity", "Error al obtener el resultado del inicio de sesión con Google", e)
                Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show()
            }
        }
    }
}