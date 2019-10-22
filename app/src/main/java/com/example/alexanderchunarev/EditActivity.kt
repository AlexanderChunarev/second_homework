package com.example.alexanderchunarev

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit.*
import kotlin.math.*

class EditActivity : AppCompatActivity() {
    private var x = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        getData()
        button_solve.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("result",
                    " ${spinner.selectedItem.toString().
                        replace("x".toRegex(), x_value.text.toString())}=${String.format("%.5f", solve())}")
            })
            finish()
        }

        button_cancel.setOnClickListener {
            finish()
        }
    }

    private fun solve(): Double {
        return when (spinner.selectedItem.toString()) {
            "sin(x)" -> sin(x)
            "cos(x)" -> cos(x)
            "tan(x)" -> tan(x)
            "ctan(x)" -> 1.0 / tan(x)
            else -> 0.0
        }
    }

    private fun getData() {
        intent.apply {
            x = getStringExtra("x_value")!!.toDouble()
            x_value.text = String.format("%.3f", x)

        }
    }
}
