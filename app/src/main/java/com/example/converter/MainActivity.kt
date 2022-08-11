package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateResult() }

    }

    private fun calculateResult(){
        val stringTextField = binding.valueToConvert.text.toString()
        val length = stringTextField.toDoubleOrNull()
        if (length == null){
            binding.resultValue.text = ""
            return
        }
        //valor ingresado en cm
        val lengthEntered = when (binding.fromLengthOption.checkedRadioButtonId) {
            R.id.option_millimeter -> length / 10
            R.id.option_centimeter -> length
            R.id.option_meter -> length * 100
            else -> length * 100000 //Kilometer to cm
        }
        // Form cm to the option selected

        val lengthResult = when (binding.toLengthOption.checkedRadioButtonId) {
            R.id.result_option_millimeter -> lengthEntered * 10
            R.id.result_option_centimeter -> lengthEntered
            R.id.result_option_meter -> lengthEntered / 100
            else -> lengthEntered / 100000 //Kilometer to cm
        }

        val typeResult = when (binding.toLengthOption.checkedRadioButtonId) {
            R.id.result_option_millimeter -> "[mm]"
            R.id.result_option_centimeter -> "[cm]"
            R.id.result_option_meter -> "[m]"
            else -> "[km]"
        }

        displayResult(lengthResult, typeResult)

        //binding.resultValue.text = "Funciona Uwu"
    }
    private fun displayResult(result: Double, type: String){
        //val finalValue = String.format("%.2f", result) + type
        val finalValue = result.toString() + type
        binding.resultValue.text = finalValue
    }
}