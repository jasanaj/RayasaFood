package com.example.rayasafood

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class KonversiBeratActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_konversi_berat)

        // Menemukan referensi dari layout
        val etInputBerat = findViewById<EditText>(R.id.etInputBerat)
        val spinnerInputSatuan = findViewById<Spinner>(R.id.spinnerInputSatuan)
        val spinnerOutputSatuan = findViewById<Spinner>(R.id.spinnerOutputSatuan)
        val btnKonversi = findViewById<Button>(R.id.btnKonversi)
        val tvHasilKonversi = findViewById<TextView>(R.id.tvHasilKonversi)

        // Daftar satuan berat yang bisa dipilih
        val satuanBerat = arrayOf("Kilogram (kg)", "Gram (g)", "Pound (lb)", "Ounce (oz)", "Ton (ton)", "Stones (st)")

        // Menyiapkan adapter untuk Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, satuanBerat)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerInputSatuan.adapter = adapter
        spinnerOutputSatuan.adapter = adapter

        // Set action pada tombol konversi
        btnKonversi.setOnClickListener {
            val inputBerat = etInputBerat.text.toString()

            if (inputBerat.isNotEmpty()) {
                try {
                    // Mengambil nilai dari Spinner untuk satuan input dan output
                    val inputSatuan = spinnerInputSatuan.selectedItem.toString()
                    val outputSatuan = spinnerOutputSatuan.selectedItem.toString()

                    val beratInput = inputBerat.toDouble()

                    // Menghitung konversi berdasarkan satuan input dan output
                    val hasilKonversi = convertWeight(beratInput, inputSatuan, outputSatuan)

                    // Menampilkan hasil konversi
                    tvHasilKonversi.text = "Hasil Konversi: $hasilKonversi $outputSatuan"
                } catch (e: NumberFormatException) {
                    // Menampilkan pesan error jika input tidak valid
                    Toast.makeText(this, "Masukkan berat yang valid", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Menampilkan pesan jika input kosong
                Toast.makeText(this, "Masukkan berat terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi untuk melakukan konversi berat
    private fun convertWeight(value: Double, inputSatuan: String, outputSatuan: String): Double {
        // Mengonversi input ke kilogram terlebih dahulu
        val valueInKg = when (inputSatuan) {
            "Kilogram (kg)" -> value
            "Gram (g)" -> value / 1000
            "Pound (lb)" -> value / 2.20462
            "Ounce (oz)" -> value / 35.274
            "Ton (ton)" -> value * 1000
            "Stones (st)" -> value / 0.15747
            else -> value
        }

        // Mengonversi dari kilogram ke satuan output yang dipilih
        return when (outputSatuan) {
            "Kilogram (kg)" -> valueInKg
            "Gram (g)" -> valueInKg * 1000
            "Pound (lb)" -> valueInKg * 2.20462
            "Ounce (oz)" -> valueInKg * 35.274
            "Ton (ton)" -> valueInKg / 1000
            "Stones (st)" -> valueInKg * 0.15747
            else -> valueInKg
        }
    }
}
