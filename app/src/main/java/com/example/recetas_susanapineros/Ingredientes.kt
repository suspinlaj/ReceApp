package com.example.recetas_susanapineros

import android.os.Bundle
import android.view.View
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

    fun onClickAtras(vista : View) {
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickSiguiente(vista : View) {
        intent.setClass(this, Progreso::class.java)
        intent.putExtra("origen", "ingredientes")
        startActivity(intent)
    }
}