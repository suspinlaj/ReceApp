package com.example.recetas_susanapineros

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.example.recetas_susanapineros.databinding.ActivityProgresoBinding
import org.json.JSONArray
import org.json.JSONObject

class Progreso : AppCompatActivity() {

    private lateinit var binding: ActivityProgresoBinding

    private var ingredientesSeleccionados: ArrayList<String>? = null
    var recetasArray : JSONArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgresoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ingredientesSeleccionados = intent.getStringArrayListExtra("ingredientesSeleccionados")

        mostrarPorcentaje()
        botonesClicables()
        leerJsonReceta()
    }

    private fun leerJsonReceta() {
        //Leer Json
        val jsonString = this.assets.open("recetas.json")
            .bufferedReader()
            .use { it.readText() }

        // Todas las recetas
        recetasArray = org.json.JSONArray(jsonString)

        // Asegurarme de que no sea null
        recetasArray?.let {
            // Recorrer el array de las recetas y sacar el titulo
            (0 until it.length()).forEach { i ->
                val receta = it.getJSONObject(i)
                val titulo = receta.getString("titulo")

                // Asignar los titulos del array a mis textView
                when (i) {
                    0 -> binding.tvNombreReceta1.text = titulo
                    1 -> binding.tvNombreReceta2.text = titulo
                    2 -> binding.tvNombreReceta3.text = titulo
                    3 -> binding.tvNombreReceta4.text = titulo
                }
                cambiarPorcentaje(receta, i)
            }
        }

    }

    fun cambiarPorcentaje(receta : JSONObject, numReceta : Int) {
        val ingredientes = receta.getJSONArray("ingredientes")

        var contador : Double = 0.0

        // Recorrer  el array de ingredientes
        (0 until  ingredientes.length()).forEach { i ->
            // Coger cada ingrediente y guardalos en "lineaIngrediente"
            val lineaIngrediente = ingredientes.getString(i).lowercase()

            // Recorrer los ingredientes seleccionados en la otra pantalla
            ingredientesSeleccionados?.forEach { ingrediente ->

                // Comprobar si los ingredientes guardados son los seleccionados por el usuario
                if(lineaIngrediente.contains(ingrediente.lowercase())) {
                    contador++
                }
            }
        }
        val porcentaje = ((contador / ingredientes.length()) * 100).toInt()
        val textoPorcentaje = "${porcentaje}%"

        when (numReceta) {
            0 -> binding.tvPorciento1.text = textoPorcentaje
            1 -> binding.tvPorciento2.text = textoPorcentaje
            2 -> binding.tvPorciento3.text = textoPorcentaje
            3 -> binding.tvPorciento4.text = textoPorcentaje
        }
        coloresRecetas(porcentaje, numReceta)
    }

    fun coloresRecetas(porcentaje : Int, numReceta : Int) {
        val origen = intent.getStringExtra("origen")
        if (origen.equals("main") || origen.equals("receta")) {
            ponerColorBienTexto()
        }else{
            when (numReceta) {
                0 -> binding.progressBar1.progress = porcentaje
                1 -> binding.progressBar2.progress = porcentaje
                2 -> binding.progressBar3.progress = porcentaje
                3 -> binding.progressBar4.progress = porcentaje
            }

            // Color según porcentaje
            val color = when {
                porcentaje > 90 -> ContextCompat.getColor(this, R.color.verdecito)
                porcentaje < 50 -> ContextCompat.getColor(this, R.color.rojito)
                else -> ContextCompat.getColor(this, R.color.marroncito)
            }

            when (numReceta) {
                0 -> binding.tvNombreReceta1.setTextColor(color)
                1 -> binding.tvNombreReceta2.setTextColor(color)
                2 -> binding.tvNombreReceta3.setTextColor(color)
                3 -> binding.tvNombreReceta4.setTextColor(color)
            }
        }
    }

    fun onClickAtras(vista: View) {
        val origen = intent.getStringExtra("origen")

        /*if (origen.equals("main")) {
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            intent.setClass(this, Ingredientes::class.java)
            startActivity(intent)
        }*/
        if (origen == "main") {
            finish()
        } else {
            finish()
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

            binding.tvPorciento1.visibility = View.INVISIBLE
            binding.tvPorciento2.visibility = View.INVISIBLE
            binding.tvPorciento3.visibility = View.INVISIBLE
            binding.tvPorciento4.visibility = View.INVISIBLE

            binding.btnAtrasIngredientes.visibility = View.INVISIBLE

            colocarComponentes()
        }
    }

    fun ponerColorBienTexto() {
        binding.tvNombreReceta1.setTextColor(ContextCompat.getColor(this, R.color.marroncito))
        binding.tvNombreReceta2.setTextColor(ContextCompat.getColor(this, R.color.marroncito))
        binding.tvNombreReceta3.setTextColor(ContextCompat.getColor(this, R.color.marroncito))
        binding.tvNombreReceta4.setTextColor(ContextCompat.getColor(this, R.color.marroncito))
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
                var receta = when (tv) {
                    binding.tvNombreReceta1 -> recetasArray?.getJSONObject(0)
                    binding.tvNombreReceta2 -> recetasArray?.getJSONObject(1)
                    binding.tvNombreReceta3 -> recetasArray?.getJSONObject(2)
                    binding.tvNombreReceta4 -> recetasArray?.getJSONObject(3)
                    else -> null
                }
                val intent = Intent(this, Receta::class.java)
                intent.putExtra("nombreReceta", tv.text.toString())
                receta?.let {

                    intent.putExtra("descripcion", receta.getString("descripcion"))

                    val ingredientesArray = receta.getJSONArray("ingredientes")
                    val ingredientesTexto = (0 until ingredientesArray.length())
                        .joinToString("\n") { ingredientesArray.getString(it) }

                    intent.putExtra("ingredientes", ingredientesTexto)
                }

                startActivity(intent)
            }
        }
    }

}
