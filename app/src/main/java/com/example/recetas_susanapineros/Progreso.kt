package com.example.recetas_susanapineros

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recetas_susanapineros.databinding.ActivityProgresoBinding

class Progreso : AppCompatActivity() {

    private lateinit var binding: ActivityProgresoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgresoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}