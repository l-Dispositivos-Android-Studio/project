package com.forever3.finalproject.ui.users

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.forever3.finalproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class RegisterLectorActivity : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()

    private lateinit var txtRNombreLector: EditText
    private lateinit var txtREmailLector: EditText
    private lateinit var txtRContraLector: EditText
    private lateinit var txtRreContraLector: EditText

    private lateinit var btnRegistrarLector: Button
    private lateinit var btnRegistrarLectorGoogle: Button // aún no se usa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_lector)

        // Encuentra las vistas correctamente
        txtRNombreLector = findViewById(R.id.txtRNombreLector)
        txtREmailLector = findViewById(R.id.txtREmailLector)
        txtRContraLector = findViewById(R.id.txtRContraLector)
        txtRreContraLector = findViewById(R.id.txtRreContraLector)
        btnRegistrarLector = findViewById(R.id.btnRegistrarLector)

        // Configura el botón de registro
        btnRegistrarLector.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val nombre = txtRNombreLector.text.toString()
        val email = txtREmailLector.text.toString()
        val contra = txtRContraLector.text.toString()
        val reContra = txtRreContraLector.text.toString()

        if (nombre.isEmpty() || email.isEmpty() || contra.isEmpty() || reContra.isEmpty()) {
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show()
        } else {
            if (contra == reContra) {
                auth.createUserWithEmailAndPassword(email, contra)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val dt: Date = Date()
                            val user = hashMapOf(
                                "idemp" to task.result?.user?.uid,
                                "usuario" to nombre,
                                "email" to email,
                                "ultAcceso" to dt.toString(),
                            )
                            db.collection("datosUsuarios")
                                .add(user)
                                .addOnSuccessListener { documentReference ->

                                    // Registrar datos en el almacenamiento local
                                    val prefe =
                                        this.getSharedPreferences("appData", Context.MODE_PRIVATE)
                                    val editor = prefe.edit()
                                    editor.putString("email", email)
                                    editor.putString("contra", contra)
                                    editor.commit()

                                    Toast.makeText(
                                        this,
                                        "Usuario registrado correctamente",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    // Regresar a la actividad anterior
                                    Intent().let {
                                        setResult(Activity.RESULT_OK)
                                        finish()
                                    }
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(
                                        this,
                                        "Error al registrar usuario",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        } else {
                            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
