package edu.tcu.sameepshah.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle: Bundle ?= intent.extras
        val correct = bundle!!.getInt("correct")
        val maxCorrect = bundle.getInt("max_correct")
        val username = bundle.getString("username")

        if ((correct/ maxCorrect.toFloat()) > 0.5) {
            findViewById<ImageView>(R.id.result_iv).setImageResource(R.drawable.ic_trophy)
            findViewById<TextView>(R.id.result_message_tv).text =
                getString(R.string.congratulations, username)
        } else {
            findViewById<ImageView>(R.id.result_iv).setImageResource(R.drawable.ic_sweat_face)
            findViewById<TextView>(R.id.result_message_tv).text =
                getString(R.string.good_luck_next_time, username)
        }

        findViewById<TextView>(R.id.result_score_tv).text =
            getString(R.string.your_score_is_out_of, correct.toString(), maxCorrect.toString())

        findViewById<Button>(R.id.restart_btn).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}