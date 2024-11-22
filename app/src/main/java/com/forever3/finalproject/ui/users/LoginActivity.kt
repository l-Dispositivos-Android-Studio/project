package com.forever3.finalproject.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.forever3.finalproject.MainActivity
import com.forever3.finalproject.R

class LoginActivity : AppCompatActivity() {

    private lateinit var btnAutenticar: Button
    private lateinit var txtRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        btnAutenticar = findViewById(R.id.btnAutenticar)
        txtRegister = findViewById(R.id.txtRegister)

        // Acción de registro (redirigir a seleccionar rol)
        txtRegister.setOnClickListener {
            goToRoleSelectionActivity()
        }

    }

    // Redirigir a la actividad de selección de rol
    private fun goToRoleSelectionActivity() {
        val intent = Intent(this, RoleSelectionActivity::class.java)
        startActivity(intent)
    }


}
