package com.example.rayasafood

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KalkulatorActivity : AppCompatActivity() {

    private var currentInput = ""
    private var currentOperation = ""
    private var lastNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Set listener untuk tombol angka
        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val number = (it as Button).text.toString()
                currentInput += number
                tvResult.text = currentInput
            }
        }

        // Set listener untuk tombol operasi
        val operations = mapOf(
            R.id.btnAdd to "+", R.id.btnSubtract to "-",
            R.id.btnMultiply to "*", R.id.btnDivide to "/"
        )
        operations.forEach { (id, op) ->
            findViewById<Button>(id).setOnClickListener {
                if (currentInput.isNotEmpty()) {
                    lastNumber = currentInput
                    currentOperation = op
                    currentInput = ""
                }
            }
        }

        // Set listener untuk tombol "="
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            if (currentInput.isNotEmpty() && lastNumber.isNotEmpty() && currentOperation.isNotEmpty()) {
                val result = calculate(lastNumber.toDouble(), currentInput.toDouble(), currentOperation)
                tvResult.text = result.toString()
                currentInput = result.toString()
                currentOperation = ""
                lastNumber = ""
            }
        }

        // Set listener untuk tombol "C"
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            currentInput = ""
            currentOperation = ""
            lastNumber = ""
            tvResult.text = "0"
        }

        // Set listener untuk tombol "."
        findViewById<Button>(R.id.btnDecimal).setOnClickListener {
            if (!currentInput.contains(".")) {
                currentInput += "."
                tvResult.text = currentInput
            }
        }
    }

    private fun calculate(a: Double, b: Double, operation: String): Double {
        return when (operation) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> 0.0
        }
    }
}
