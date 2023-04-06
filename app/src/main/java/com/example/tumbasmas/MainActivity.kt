package com.example.tumbasmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnLogin: Button
    private lateinit var edtEmail: EditText
    private lateinit var edtPasswrd: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        edtEmail = findViewById(R.id.edtEmail)
        edtPasswrd = findViewById(R.id.edtPasswrd)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener(this)

        val btnSignIn = findViewById<Button>(R.id.btnsignin)
        btnSignIn.setOnClickListener{
            val Intent = Intent(this,SignIn::class.java)
            startActivity(Intent)

        }

    }

    override fun onClick(v: View?) {
        var emptyEmail: Boolean = false
        var emptyPasswrd: Boolean = false


        if (edtEmail.text.toString().trim().length == 0) {
            edtEmail.error = "Email Harus Diisi"
            emptyEmail = true
        }
        if (edtPasswrd.text.toString().trim().length == 0) {
            edtPasswrd.error = "Password harus diisi"
            emptyPasswrd = true
        }

        if (!emptyEmail && !emptyPasswrd) {
            val dataRecieved = Intent(this@MainActivity, PageMas::class.java)
            val email = edtEmail.text.toString()
            dataRecieved.putExtra("String", email)
            startActivity(dataRecieved)
        }
    }
}