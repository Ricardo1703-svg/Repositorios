package com.example.geniuskids

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setupGoogleSignIn()
        setupAnonymousSignIn()
        Correo()
        setupRegistration()
    }

    private fun setupGoogleSignIn() {
        val googleSignInButton = findViewById<SignInButton>(R.id.Google)
        googleSignInButton.setOnClickListener {
            signInWithGoogle()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun setupAnonymousSignIn() {
        val anonymousSignInButton = findViewById<ImageButton>(R.id.Anonimo)
        anonymousSignInButton.setOnClickListener {
            signInAnonymously()
        }
    }

    private fun signInAnonymously() {
        FirebaseAuth.getInstance().signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("AnonymousLoginActivity", "Inicio de sesión anónimo exitoso")
                    // Iniciar la actividad deseada después del inicio de sesión anónimo
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("AnonymousLoginActivity", "Error en el inicio de sesión anónimo", task.exception)
                    Toast.makeText(this, "Error en el inicio de sesión anónimo", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun Correo() {
        val signInButton = findViewById<Button>(R.id.btnIngresar)
        signInButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.txtCorreo).text.toString()
            val password = findViewById<EditText>(R.id.txtContraseña).text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d("AuthActivity", "Inicio de sesión exitoso")
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("AuthActivity", "Error en el inicio de sesión", task.exception)
                            Toast.makeText(this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRegistration() {
        val registerButton = findViewById<Button>(R.id.btnRegistro)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("GoogleLoginActivity", "signInResult:failed code=${e.statusCode}")
                Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("GoogleLoginActivity", "signInWithCredential:success")
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    // Error al iniciar sesión con Firebase usando las credenciales de Google
                    Log.w("GoogleLoginActivity", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Error al iniciar sesión con Firebase usando las credenciales de Google", Toast.LENGTH_SHORT).show()
                }
            }
    }
}