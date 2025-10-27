package com.example.recetas_susanapineros

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.recetas_susanapineros.databinding.ActivityRecetaBinding
import org.json.JSONObject

class Receta : AppCompatActivity() {

    private lateinit var binding: ActivityRecetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mostrarReceta()
    }

   private fun mostrarReceta() {

       val tituloReceta = intent.getStringExtra("nombreReceta")
       val ingredientes = intent.getStringExtra("ingredientes")
       val descripcion = intent.getStringExtra("descripcion")

       binding.tvNombreReceta.text = tituloReceta
       binding.tvDescripcion.text = descripcion
       binding.tvIngredientes.text = ingredientes

    }


    fun onClickAtras(vista: View) {
        finish()
        /*intent.setClass(this, Progreso::class.java)
        intent.putExtra("origen", "receta")
        startActivity(intent)*/
    }

    fun onClickInicio(vista: View) {
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
    }
}
