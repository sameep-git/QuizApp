package edu.tcu.sameepshah.quiz

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nameEt = findViewById<TextInputEditText>(R.id.name_et)
        val startBtn = findViewById<Button>(R.id.start_btn)
        startBtn.setOnClickListener { _ ->
            if((nameEt.text?.isNotBlank() == true)) {
                goToQuestion(nameEt)
            } else {
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show()
            }
        }
        nameEt.setOnEditorActionListener { _, actionId, _ ->
            if((actionId == EditorInfo.IME_ACTION_GO) && (nameEt.text?.isNotBlank() == true)) {
                goToQuestion(nameEt)
                true
            } else {
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show()
                false
            }
        }
    }

    private fun goToQuestion(nameEt: TextInputEditText) {
        val intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra("username", nameEt.text.toString())
        startActivity(intent)
        finish()
    }
}