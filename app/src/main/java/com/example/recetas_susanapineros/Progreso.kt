package com.example.recetas_susanapineros

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.example.recetas_susanapineros.databinding.ActivityProgresoBinding
import org.json.JSONObject

class Progreso : AppCompatActivity() {

    private lateinit var binding: ActivityProgresoBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgresoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mostrarPorcentaje()
        botonesClicables()
    }

    fun onClickAtras(vista: View) {
        val origen = intent.getStringExtra("origen")

        if (origen.equals("main")) {
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            intent.setClass(this, Ingredientes::class.java)
            startActivity(intent)
        }
    }

    fun onClickInicio(vista: View) {
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun mostrarPorcentaje() {
        val origen = intent.getStringExtra("origen")

        if (origen.equals("main") || origen.equals("receta")) {
            binding.progressBar1.visibility = View.INVISIBLE
            binding.progressBar2.visibility = View.INVISIBLE
            binding.progressBar3.visibility = View.INVISIBLE
            binding.progressBar4.visibility = View.INVISIBLE

            binding.btnAtrasIngredientes.visibility = View.INVISIBLE

            colocarComponentes()
        }
    }

    private fun colocarComponentes() {
        val cs = ConstraintSet()
        cs.clone(binding.main)

        cs.connect(
            binding.tvNombreReceta1.id,
            ConstraintSet.TOP,
            binding.TituloRecetas2.id,
            ConstraintSet.BOTTOM,
            180
        )

        cs.applyTo(binding.main)
    }

    private fun botonesClicables() {
        val textViews = listOf(
            binding.tvNombreReceta1,
            binding.tvNombreReceta2,
            binding.tvNombreReceta3,
            binding.tvNombreReceta4
        )

        textViews.forEach { tv ->
            tv.isClickable = true
            tv.setOnClickListener {
                val intent = Intent(this, Receta::class.java)
                intent.putExtra("titulo", tv.text.toString())
                startActivity(intent)
            }
        }
    }

    fun leerJson(context: Context) {
        val jsonString = context.assets.open("recetas.json")
            .bufferedReader()
            .use { it.readText() }

        val jsonObject = JSONObject(jsonString)
        val recetasArray = jsonObject.getJSONArray("recetas")

        for (i in 0 until recetasArray.length()) {
            val receta = recetasArray.getJSONObject(i)
            val titulo = receta.getString("titulo")
            val descripcion = receta.getString("descripcion")
            val ingredientes = receta.getJSONArray("ingredientes")


        }
    }

}
