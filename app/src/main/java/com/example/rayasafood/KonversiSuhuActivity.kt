package com.example.rayasafood

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KonversiSuhuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Untuk tampilan penuh (Edge to Edge)
        setContentView(R.layout.activity_konversi_suhu) // Ubah layout ke layout yang sesuai

        val editTextTemperature = findViewById<EditText>(R.id.editTextTemperature)
        val spinnerUnit = findViewById<Spinner>(R.id.spinnerUnit)
        val convertButton = findViewById<Button>(R.id.convertButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // Menambahkan pilihan satuan suhu ke Spinner
        val units = arrayOf("Celsius", "Fahrenheit", "Kelvin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUnit.adapter = adapter

        // Menangani tombol konversi
        convertButton.setOnClickListener {
            val temperature = editTextTemperature.text.toString()
            if (temperature.isNotEmpty()) {
                val temperatureValue = temperature.toDouble()
                val selectedUnit = spinnerUnit.selectedItem.toString()

                // Lakukan konversi berdasarkan satuan yang dipilih
                val result = when (selectedUnit) {
                    "Celsius" -> convertFromCelsius(temperatureValue)
                    "Fahrenheit" -> convertFromFahrenheit(temperatureValue)
                    "Kelvin" -> convertFromKelvin(temperatureValue)
                    else -> null
                }

                // Tampilkan hasil konversi
                resultTextView.text = result ?: "Konversi tidak valid"
            } else {
                Toast.makeText(this, "Masukkan suhu terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Konversi dari Celsius ke Fahrenheit dan Kelvin
    private fun convertFromCelsius(celsius: Double): String {
        val fahrenheit = (celsius * 9/5) + 32
        val kelvin = celsius + 273.15
        return "Fahrenheit: %.2f °F\nKelvin: %.2f K".format(fahrenheit, kelvin)
    }

    // Konversi dari Fahrenheit ke Celsius dan Kelvin
    private fun convertFromFahrenheit(fahrenheit: Double): String {
        val celsius = (fahrenheit - 32) * 5/9
        val kelvin = celsius + 273.15
        return "Celsius: %.2f °C\nKelvin: %.2f K".format(celsius, kelvin)
    }

    // Konversi dari Kelvin ke Celsius dan Fahrenheit
    private fun convertFromKelvin(kelvin: Double): String {
        val celsius = kelvin - 273.15
        val fahrenheit = (celsius * 9/5) + 32
        return "Celsius: %.2f °C\nFahrenheit: %.2f °F".format(celsius, fahrenheit)
    }
}
