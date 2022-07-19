package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
                calculateTip()
        }
    }

     fun calculateTip() {
         with(binding) {

             val cost = if (!costOfService.text.isNullOrEmpty()) {
                 (costOfService.text.toString()).toDouble()
             } else {
                 0.00

             }
             if (cost == 0.0) Toast.makeText( baseContext,"Digite Um valor", Toast.LENGTH_SHORT).show()
             val percentage = when (tipOptions.checkedRadioButtonId) {
                 optionTwentyPercent.id -> 0.2
                 optionEighteenPercent.id -> 0.18
                 else -> 0.15
             }
             var tip = percentage * cost

             if (roundUpSwitch.isChecked) tip = kotlin.math.ceil(tip)
             val  formatedTip = NumberFormat.getCurrencyInstance().format(tip)
            tipResult.text = getString(R.string.tip_amount,formatedTip)
         }
     }
}