package com.example.recetas_susanapineros

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recetas_susanapineros.databinding.ActivityMainBinding
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun onClickRecetas(vista : View) {
        val intent = Intent(this, Progreso::class.java)
        intent.putExtra("origen", "main")
        startActivity(intent)
    }

    fun onClickIngredientes(vista : View) {
        val intent = Intent(this, Ingredientes::class.java)
        startActivity(intent)
    }
}