package com.example.recetas_susanapineros

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recetas_susanapineros.databinding.ActivityProgresoBinding
import com.example.recetas_susanapineros.databinding.ActivityRecetaBinding

class Receta : AppCompatActivity() {

    private lateinit var binding: ActivityRecetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecetaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}