package com.hiroshima.volumecalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hiroshima.volumecalculator.databinding.ActivityMainBinding

/**
 * Created by AnjukaK on 10/06/24.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.gvItem.isVerticalFadingEdgeEnabled = true
        binding.gvItem.setFadingEdgeLength(200)

        val list = listOf(
            "Sphere",
            "Cone",
            "Cube",
            "Cylinder"
        )

        val adapter = GridViewAdapter(this, list, screenWidth(applicationContext))
        binding.gvItem.adapter = adapter

        binding.gvItem.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, CalculationActivity::class.java)
            intent.putExtra("type", position)
            startActivity(intent)
        }

    }

    private fun screenWidth(context: Context): Int{
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
}