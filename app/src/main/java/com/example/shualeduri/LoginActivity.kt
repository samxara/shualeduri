package com.example.shualeduri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        btnDontHaveAccount.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
        btnLogIn.setOnClickListener {
            loginIn()
        }
    }

    private fun loginIn() {
        if (emailEditTextMain.text.toString().isEmpty()){
            emailEditTextMain.error = "Please enter Email"
            emailEditTextMain.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditTextMain.text.toString()).matches()){
            emailEditTextMain.error = "Please enter Email"
            emailEditTextMain.requestFocus()
            return
        }
        if (passwordEditTextMain.text.toString().isEmpty()){
            passwordEditTextMain.error = "Please enter Password"
            passwordEditTextMain.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(emailEditTextMain.text.toString(), passwordEditTextMain.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else {

                        updateUI(null)
                    }
                }
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser?) {
        if (currentUser != null){
            startActivity(Intent(this, DashboardActivity::class.java))
        }else{
            Toast.makeText(
                    baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }
}
