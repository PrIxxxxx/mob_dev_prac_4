package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var display1: TextView
    private lateinit var display2: TextView

    private lateinit var buttonDivide: MaterialButton
    private lateinit var buttonMultiply: MaterialButton
    private lateinit var buttonPlus: MaterialButton
    private lateinit var buttonMinus: MaterialButton
    private lateinit var buttonEquals: MaterialButton
    private lateinit var button0: MaterialButton
    private lateinit var button1: MaterialButton
    private lateinit var button2: MaterialButton
    private lateinit var button3: MaterialButton
    private lateinit var button4: MaterialButton
    private lateinit var button5: MaterialButton
    private lateinit var button6: MaterialButton
    private lateinit var button7: MaterialButton
    private lateinit var button8: MaterialButton
    private lateinit var button9: MaterialButton
    private lateinit var buttonAC: MaterialButton
    private lateinit var buttonMS: MaterialButton
    private lateinit var buttonMR: MaterialButton
    private lateinit var buttonMC: MaterialButton

    private var memory: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display1 = findViewById(R.id.display)
        display2 = findViewById(R.id.display2)

        buttonDivide = findViewById(R.id.button18)
        buttonMultiply = findViewById(R.id.button13)
        buttonPlus = findViewById(R.id.button5)
        buttonMinus = findViewById(R.id.button8)
        buttonEquals = findViewById(R.id.button21)
        button0 = findViewById(R.id.button20)
        button1 = findViewById(R.id.button2)
        button2 = findViewById(R.id.button3)
        button3 = findViewById(R.id.button4)
        button4 = findViewById(R.id.button9)
        button5 = findViewById(R.id.button6)
        button6 = findViewById(R.id.button7)
        button7 = findViewById(R.id.button11)
        button8 = findViewById(R.id.button10)
        button9 = findViewById(R.id.button12)
        buttonAC = findViewById(R.id.button19)
        buttonMS = findViewById(R.id.button22)
        buttonMR = findViewById(R.id.button24)
        buttonMC = findViewById(R.id.button23)

        setClickListeners()
    }

    private fun setClickListeners() {
        buttonDivide.setOnClickListener(this)
        buttonMultiply.setOnClickListener(this)
        buttonPlus.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
        buttonEquals.setOnClickListener(this)
        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        buttonAC.setOnClickListener(this)

        buttonMS.setOnClickListener(memoryButtonClickListener)
        buttonMR.setOnClickListener(memoryButtonClickListener)
        buttonMC.setOnClickListener(memoryButtonClickListener)
    }

    private val memoryButtonClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.button22 -> {
                val save = display1.text.toString().toDoubleOrNull()
                if (save != null) {
                    memory = save //
                    Toast.makeText(this, "yeeeeeeeeeeeesssssss $save", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "aaaaaaaaa", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.button24 -> {
                val memoryVal = memory?.toString() ?: "0"
                display2.text = display2.text.toString() + memoryVal
                Toast.makeText(this,  "$memoryVal", Toast.LENGTH_SHORT).show()
            }
            R.id.button23 -> {
                memory = null
            }
        }
    }

    override fun onClick(view: View) {
        val button = view as MaterialButton
        val text = button.text.toString()
        var calc = display2.text.toString()

        when (text) {
            "AC" -> {
                display2.text = ""
                display1.text = "0"
            }
            "=" -> {
                val result = display1.text.toString()
                display2.text = result
            }
            else -> {
                calc = if (text == "C") {
                    if (calc.isNotEmpty()) {
                        calc.substring(0, calc.length - 1)
                    } else {
                        calc
                    }
                } else {
                    calc + text
                }
                display2.text = calc


                if (calc.isNotEmpty()) {
                    val finalResult: String = getResult(calc)
                    if (finalResult != "nop") {
                        display1.text = finalResult
                    }
                }
            }
        }
    }

    private fun getResult(data: String?): String {
        return try {
            val expression = ExpressionBuilder(data).build()
            val result = expression.evaluate()
            if (result == result.toLong().toDouble()) {
                result.toLong().toString()
            } else {
                result.toString()
            }
        } catch (e: Exception) {
            "nop"
        }
    }
}
