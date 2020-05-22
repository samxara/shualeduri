package com.example.shualeduri

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        btn_sign_up.setOnClickListener {
            signUp()

        }
    }

    private fun signUp(){
        if (emailEditText.text.toString().isEmpty()){
            emailEditText.error = "Please enter Email"
            emailEditText.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString()).matches()){
            emailEditText.error = "Please enter Email"
            emailEditText.requestFocus()
            return
        }
        if (passwordEditText.text.toString().isEmpty()){
            passwordEditText.error = "Please enter Password"
            passwordEditText.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                } else {

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}