package com.example.recetas_susanapineros

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.recetas_susanapineros.databinding.ActivityRecetaBinding
import org.json.JSONObject

class Receta : AppCompatActivity() {

    private lateinit var binding: ActivityRecetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecetaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val nombreReceta = intent.getStringExtra("nombreReceta")
        binding.tvNombreReceta.text = nombreReceta
    }




            //val plato = intent.getStringExtra("nombreReceta")



    fun onClickAtras(vista : View) {
        intent.setClass(this, Progreso::class.java)
        intent.putExtra("origen", "receta")
        startActivity(intent)
    }
    fun onClickInicio(vista : View) {
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
    }
}