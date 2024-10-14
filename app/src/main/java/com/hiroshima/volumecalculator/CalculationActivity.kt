package com.hiroshima.volumecalculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.hiroshima.volumecalculator.databinding.ActivityCalculationBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by AnjukaK on 12/06/24.
 */

class CalculationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculationBinding
    private var viewType: Int = -1
    private var selectedUnit = Constant.CENTIMETER
    private var finalUnit = " cm³"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_calculation)
        binding = ActivityCalculationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewType = intent.getIntExtra("type", -1)

        //init level
        binding.clRow1.visibility = View.GONE
        binding.clRow2.visibility = View.GONE
        binding.clRow3.visibility = View.GONE

        //unit cm or m
        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            if (id == R.id.rb_cm) {
                selectedUnit = Constant.CENTIMETER
                setLabelUnit("cm")
                finalUnit = " cm³"
            } else {
                selectedUnit = Constant.METER
                setLabelUnit("m")
                finalUnit = " m³"
            }
        }


        when (viewType) {
            Constant.TYPE_SPHERE -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.GONE
                binding.clRow3.visibility = View.GONE

                binding.tvL1.text = "Radius (r)"
                binding.ivType.setBackgroundResource(R.drawable.sphere)

            }

            Constant.TYPE_CONE -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.VISIBLE
                binding.clRow3.visibility = View.GONE

                binding.tvL1.text = "Base Radius (r)"
                binding.tvL2.text = "Height (h)"
                binding.ivType.setBackgroundResource(R.drawable.cone)

            }

            Constant.TYPE_CUBE -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.GONE
                binding.clRow3.visibility = View.GONE

                binding.tvL1.text = "Edge Length (a)"
                binding.ivType.setBackgroundResource(R.drawable.cube)

            }

            Constant.TYPE_CYLINDER -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.VISIBLE
                binding.clRow3.visibility = View.GONE

                binding.tvL1.text = "Base Radius (r)"
                binding.tvL2.text = "Height (h)"
                binding.ivType.setBackgroundResource(R.drawable.cylinder)
            }

            Constant.TYPE_RECTANGULAR -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.VISIBLE
                binding.clRow3.visibility = View.VISIBLE

                binding.tvL1.text = "Length (l)"
                binding.tvL2.text = "Width (w)"
                binding.tvL3.text = "Height (h)"
                binding.ivType.setBackgroundResource(R.drawable.rectacgular)
            }

            Constant.TYPE_CAPSULE -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.VISIBLE
                binding.clRow3.visibility = View.GONE

                binding.tvL1.text = "Base Radius (r)"
                binding.tvL2.text = "Height (h)"
                binding.ivType.setBackgroundResource(R.drawable.capsule)
            }

            Constant.TYPE_SPHERICAL -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.VISIBLE
                binding.clRow3.visibility = View.VISIBLE

                binding.tvL1.text = "Base Radius (r)"
                binding.tvL2.text = "Ball Radius (R)"
                binding.tvL3.text = "Height (h)"
                binding.ivType.setBackgroundResource(R.drawable.cap)
            }

            Constant.TYPE_CONICAL -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.VISIBLE
                binding.clRow3.visibility = View.VISIBLE

                binding.tvL1.text = "Top Radius (r)"
                binding.tvL2.text = "Bottom Radius (R)"
                binding.tvL3.text = "Height (h)"
                binding.ivType.setBackgroundResource(R.drawable.conical)
            }

            Constant.TYPE_ELLIPSOID -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.VISIBLE
                binding.clRow3.visibility = View.VISIBLE

                binding.tvL1.text = "Axis 1 (a)"
                binding.tvL2.text = "Axis 2 (b)"
                binding.tvL3.text = "Axis 3 (c)"
                binding.ivType.setBackgroundResource(R.drawable.ellipsoid)
            }

            Constant.TYPE_PYRAMID -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.VISIBLE
                binding.clRow3.visibility = View.GONE

                binding.tvL1.text = "Base Edge (a)"
                binding.tvL2.text = "Height (h)"
                binding.ivType.setBackgroundResource(R.drawable.pyramid)
            }

            Constant.TYPE_TUBE -> {

                binding.clRow1.visibility = View.VISIBLE
                binding.clRow2.visibility = View.VISIBLE
                binding.clRow3.visibility = View.VISIBLE

                binding.tvL1.text = "Outer Diameter (d1)"
                binding.tvL2.text = "Inner Diameter (d2)"
                binding.tvL3.text = "Length (l)"
                binding.ivType.setBackgroundResource(R.drawable.tube)
            }

            -1 -> { //is there any issue with type pass
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                lifecycleScope.launch {
                    delay(2000)
                    finish()
                }
            }
        }

        binding.btnCal.setOnClickListener {

            var line1Val = if (binding.etL1.text.toString().isNotEmpty()) {
                binding.etL1.text.toString().toDouble()
            } else {
                0.0
            }

            var line2Val = if (binding.etL2.text.toString().isNotEmpty()) {
                binding.etL2.text.toString().toDouble()
            } else {
                0.0
            }

            var line3Val = if (binding.etL3.text.toString().isNotEmpty()) {
                binding.etL3.text.toString().toDouble()
            } else {
                0.0
            }

            var finalVolume = VolumeCalculate.calVolume(viewType, line1Val, line2Val, line3Val)

            binding.clResultMain.visibility = View.VISIBLE
            binding.tvVolume.text = finalVolume + finalUnit
            binding.btnCal.visibility = View.GONE
        }

        binding.imageView.setOnClickListener {
            binding.clResultMain.visibility = View.GONE
            binding.btnCal.visibility = View.VISIBLE
        }

        //back
        binding.ivBack.setOnClickListener {
            finish()
        }

        //hide keyboard
        binding.main.setOnClickListener {
            hideKeyBoard()
        }
    }

    private fun setLabelUnit(unit: String) {
        binding.tvL1Unit.text = unit
        binding.tvL2Unit.text = unit
        binding.tvL3Unit.text = unit
    }

    private fun hideKeyBoard(){
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}