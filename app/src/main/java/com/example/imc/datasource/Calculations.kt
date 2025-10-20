package com.example.imc.datasource

import android.media.tv.AdResponse

object Calculations {

    fun CalculateIMC(height: String, weight: String, response: (String, Boolean) -> Unit) {
        if (height.isNotEmpty() && weight.isNotEmpty())
        {
            val numericWeight = weight.replace(",", ".").toDoubleOrNull()
            val numericHeight = height.toDoubleOrNull()

            if (numericWeight != null && numericHeight != null)
            {
                val imc = numericWeight / (numericHeight / 100 * numericHeight / 100)

                when {
                    imc < 18.5 -> response("IMC: %.1f — Underweight".format(imc), false)
                    imc in 18.5..24.9 -> response("IMC: %.1f — Normal weight".format(imc), false)
                    imc in 25.0..29.9 -> response("IMC: %.1f — Overweight".format(imc), false)
                    imc in 30.0..34.9 -> response("IMC: %.1f — Obesity class I".format(imc), false)
                    imc in 35.0..39.9 -> response("IMC: %.1f — Obesity class II".format(imc), false)
                    else -> response("IMC: %.1f — Obesity class III".format(imc), false)
                }
            }
        }
        else{
            response("Fill the input fields!", true)
        }
    }

}