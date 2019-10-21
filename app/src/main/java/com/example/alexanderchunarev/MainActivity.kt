package com.example.alexanderchunarev

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setActionEvents()
    }

    private fun setActionEvents() {
        enter_button.setOnClickListener {
            if (input_text.text.toString().trim().isNotEmpty()) {
                startActivityForResult(Intent(this, EditActivity::class.java).apply {

                    putExtra("x_value", input_text.text.toString())

                }, 1)
            } else
                Toast.makeText(this, "Please, enter the number", Toast.LENGTH_SHORT).show()
        }

        button_send.setOnClickListener {
            val title = "Trigonometric calculator"
            if (final_result.text.trim().isNotEmpty()) {
                startActivity(Intent.createChooser(Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_SUBJECT, title)
                    putExtra(Intent.EXTRA_TEXT, final_result.text)
                }, "Choose an email client"))
            } else
                Toast.makeText(this, "Nothing to send", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                final_result.text = data.getStringExtra("result")
            }
        }
    }
}
