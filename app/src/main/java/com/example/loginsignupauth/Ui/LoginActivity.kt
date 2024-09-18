package com.example.loginsignupauth.Ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsignupauth.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.LoginPassword.text.toString()
//            val cnfpassword = binding.logincnfpassword.text.toString()

            // Check if any of the fields are empty
            if (email.isEmpty() || password.isEmpty() ) {
                if (email.isEmpty()) {
                    binding.loginEmail.setError("Email can't be empty")
                }
                if (password.isEmpty()) {
                    binding.LoginPassword.error = "Password cannot be empty"
                }
//                if (cnfpassword.isEmpty()) {
//                    binding.logincnfpassword.error = "Confirm password cannot be empty"
//                }
                return@setOnClickListener
            }

            // Check if password and confirm password match
//            if (password != cnfpassword) {
//                Toast.makeText(this, "Confirm Password doesn't match", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            // Proceed with login if everything is valid
            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
//                            Toast.makeText(this, "Wrong email or password", Toast.LENGTH_SHORT).show()
                            Toast.makeText(
                                this,
                                "  Enter valid email or password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(this, " please enter  email and password", Toast.LENGTH_SHORT).show()

            }

        }
        binding.signupText.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }
}
