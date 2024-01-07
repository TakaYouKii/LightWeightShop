package com.example.biometricapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.biometricapp.R
import com.example.biometricapp.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val username = etUsername.text.toString().trim()
            btnSignUp.setOnClickListener{
                if(email.isEmpty() || password.isEmpty() || username.isEmpty()){
                    Toast.makeText(applicationContext, "Пусто, нельзя так ц ц ц", Toast.LENGTH_SHORT).show()
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(){task->
                            if(task.isSuccessful){
                                val userInfo = hashMapOf<String, String>()
                                userInfo["email"] = email
                                userInfo["password"] = password
                                FirebaseDatabase.getInstance().reference.child("Users").child(
                                    FirebaseAuth.getInstance().currentUser!!.uid).setValue(userInfo)
                                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                }
            }

            ibBack.setOnClickListener{
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }

        }
    }
}