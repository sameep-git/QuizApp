package edu.tcu.sameepshah.quiz

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private val questions = Constants.getQuestions().shuffled()
    private var questionIdx = 0
    private val optionTvs = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<ProgressBar>(R.id.progress_bar).max = questions.size
        findViewById<ProgressBar>(R.id.progress_bar).progress = questionIdx + 1
        findViewById<TextView>(R.id.progress_tv).setText("${questionIdx+1}/10")
        findViewById<Button>(R.id.submit_btn).setOnClickListener(this)
        setQuestion()
    }

    private fun setQuestion() {
        val question = questions[questionIdx]
        findViewById<TextView>(R.id.question_tv).text = question.question
        // Needs work on image setter
        findViewById<ImageView>(R.id.flag_iv).setImageResource(question.image)
        setOptionTvs(question)
    }

    private fun setOptionTvs(question: Question){
        val optionLl = findViewById<LinearLayout>(R.id.option_ll)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(10, 10, 10, 10)
        for (option in question.options) {
            val optionTv = TextView(this)
            optionTv.setBackgroundResource(R.drawable.default_option_bg)
            optionTv.setOnClickListener(this)
            optionTv.layoutParams = layoutParams
            optionTv.setPadding(10, 30, 10, 30)
            optionTv.textSize = 16F
            optionTv.setTextColor(Color.BLACK)
            optionTv.gravity = Gravity.CENTER_HORIZONTAL
            optionTvs.add(optionTv)
            optionTv.text = option
            optionLl.addView(optionTv)
        }
    }

    override fun onClick(view: View?) {
        if(view == findViewById<Button>(R.id.submit_btn)) {
            println("Submit button clicked.")
        } else { // If an option is clicked

        }
    }
}