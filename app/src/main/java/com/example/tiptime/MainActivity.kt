package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)       //Binding
        setContentView(binding.root)                                //Binding
        binding.calculateButton.setOnClickListener{ calculateTip() }//Binding
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text=""                                   //Binding
            binding.totalAmount.text=""                                 //Binding
            return
        }
        Log.println(Log.ERROR,"cost",cost.toString())
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked                      //Binding
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)       //NumberFormat to format normal numbers as currency
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
//        val totalAMT=binding.totalAmount
//        totalAMT.text=(tip+cost).toString()
        val totalAMT=NumberFormat.getCurrencyInstance().format(tip+cost)
        binding.totalAmount.text=getString(R.string.total_amount,totalAMT)

    }
}