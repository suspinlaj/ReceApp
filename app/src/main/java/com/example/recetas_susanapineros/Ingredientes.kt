package com.example.recetas_susanapineros

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recetas_susanapineros.databinding.ActivityIngredientesBinding

class Ingredientes : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}