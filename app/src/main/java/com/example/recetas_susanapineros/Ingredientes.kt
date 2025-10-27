package com.example.recetas_susanapineros

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.recetas_susanapineros.databinding.ActivityIngredientesBinding

class Ingredientes : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ingredientesCheckBoxs()
    }

    fun ingredientesCheckBoxs(): MutableList<String> {
        val ingredientesSeleccionados = mutableListOf<String>()

        // Acceder al grid para poder usar los checkbox
        val gridLayout = binding.grid

        //Recorrer cada hijo del grid (los checkbox)
        for (i in 0 until gridLayout.childCount) {
            val checkbox = gridLayout.getChildAt(i)
            // verificar que es un checkbox y que está marcado
            if (checkbox is CheckBox && checkbox.isChecked) {
                ingredientesSeleccionados.add(checkbox.text.toString())
            }
        }
        return ingredientesSeleccionados;
    }

    fun onClickAtras(vista : View) {
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickSiguiente(vista : View) {
        val ingredientesSeleccionados = ingredientesCheckBoxs()

        intent.setClass(this, Progreso::class.java)
        intent.putExtra("origen", "ingredientes")
        intent.putStringArrayListExtra( "ingredientesSeleccionados",
            ArrayList(ingredientesSeleccionados)
        )
        startActivity(intent)
    }
}