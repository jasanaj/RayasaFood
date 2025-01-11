package com.example.rayasafood

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class KonversiMataUangActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_konversi_mata_uang)

        // Inisialisasi elemen UI
        val editTextAmount = findViewById<EditText>(R.id.editTextAmount)
        val spinnerFromCurrency = findViewById<Spinner>(R.id.spinnerFromCurrency)
        val spinnerToCurrency = findViewById<Spinner>(R.id.spinnerToCurrency)
        val convertButton = findViewById<Button>(R.id.convertButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // Daftar mata uang yang dapat dipilih
        val currencies = listOf("IDR", "USD", "EUR", "JPY")

        // Adapter untuk Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFromCurrency.adapter = adapter
        spinnerToCurrency.adapter = adapter

        // Tombol Konversi
        convertButton.setOnClickListener {
            val amount = editTextAmount.text.toString().toDoubleOrNull()
            val fromCurrency = spinnerFromCurrency.selectedItem.toString()
            val toCurrency = spinnerToCurrency.selectedItem.toString()

            if (amount != null) {
                // Panggil fungsi untuk mengkonversi
                val result = convertCurrency(amount, fromCurrency, toCurrency)
                resultTextView.text = "Hasil: $result $toCurrency"
            } else {
                resultTextView.text = "Masukkan jumlah uang yang valid!"
            }
        }
    }

    // Fungsi untuk mengkonversi mata uang
    private fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Double {
        val exchangeRates = mapOf(
            "IDR" to 1.0,  // IDR ke IDR
            "USD" to 0.000067,  // IDR ke USD
            "EUR" to 0.000061,  // IDR ke EUR
            "JPY" to 0.0075  // IDR ke JPY
        )

        val fromRate = exchangeRates[fromCurrency] ?: 1.0
        val toRate = exchangeRates[toCurrency] ?: 1.0

        // Mengkonversi jumlah uang dari satu mata uang ke mata uang lainnya
        return (amount / fromRate) * toRate
    }
}
