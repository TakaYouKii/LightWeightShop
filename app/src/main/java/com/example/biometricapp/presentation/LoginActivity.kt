package com.example.biometricapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.biometricapp.R
import com.example.biometricapp.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnLogin.setOnClickListener{
                if(etEmail.text.toString().isEmpty() || etPassword.text.toString().isEmpty()){
                    Toast.makeText(applicationContext, "Пусто, нельзя так ц ц ц", Toast.LENGTH_SHORT).show()
                }else{
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmail.text.toString().trim(), etPassword.text.toString().trim())
                        .addOnCompleteListener {task ->
                            if(task.isSuccessful){
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                        .addOnFailureListener{
                            Toast.makeText(applicationContext, "не правильно", Toast.LENGTH_SHORT).show()
                        }
                }
            }

            tvSignUp.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}