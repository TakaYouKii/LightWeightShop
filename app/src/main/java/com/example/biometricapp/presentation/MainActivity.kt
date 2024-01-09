package com.example.biometricapp.presentation


import android.content.Intent


import androidx.navigation.findNavController

import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.biometricapp.R
import com.example.biometricapp.databinding.ActivityMainBinding

import com.google.firebase.auth.FirebaseAuth

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.FirebaseApp

import androidx.appcompat.widget.Toolbar



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        if (FirebaseAuth.getInstance().currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navView.setupWithNavController(navController)

    }

}