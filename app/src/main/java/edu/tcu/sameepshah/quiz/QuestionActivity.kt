package edu.tcu.sameepshah.quiz

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private val questions = Constants.getQuestions().shuffled()
    private var questionIdx = 0
    private val optionTvs = mutableListOf<TextView>()
    private var answerRevealed = false
    private var selectedOptionIdx = -1

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
        findViewById<Button>(R.id.submit_btn).setOnClickListener(this)
        setQuestion()
    }

    private fun setQuestion() {
        selectedOptionIdx = -1
        val question = questions[questionIdx]
        findViewById<TextView>(R.id.question_tv).text = question.question
        // Needs work on image setter
        // Set up the progress bar
        findViewById<ProgressBar>(R.id.progress_bar).progress = questionIdx + 1
        findViewById<TextView>(R.id.progress_tv).setText("${questionIdx+1}/10")
        findViewById<ImageView>(R.id.flag_iv).setImageResource(question.image)
        setOptionTvs(question)
        findViewById<Button>(R.id.submit_btn).text = R.string.submit.toString()
        // Change submit button text
    }

    private fun setOptionTvs(question: Question){
        // Remove all the options from the previous question from the LinearLayout
        // Clear all the options from optionTvs
        val optionLl = findViewById<LinearLayout>(R.id.option_ll)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, dpToPx(), 0, 0)
        for (option in question.options.shuffled()) {
            val optionTv = TextView(this)
            optionTv.setBackgroundResource(R.drawable.default_option_bg)
            optionTv.setOnClickListener(this)
            optionTv.layoutParams = layoutParams
            optionTv.setPadding(0, dpToPx(), 0, dpToPx())
            optionTv.textSize = 16F
            optionTv.setTextColor(Color.BLACK)
            optionTv.gravity = Gravity.CENTER_HORIZONTAL
            optionTvs.add(optionTv)
            optionTv.text = option
            optionLl.addView(optionTv)
        }
    }

    private fun selectedOptionView(selectedOptionTv: TextView) {
        // Set all options to default look
        // Set selected button to selected look
    }

    private fun answerView(correctOptionTv: TextView) {

    }

    private fun goToResult() {
        // Use intent to go to result page
    }

    override fun onClick(view: View?) {
        if(view == findViewById<Button>(R.id.submit_btn)) {
            if (!answerRevealed) {
                if (selectedOptionIdx == -1) {
                    Toast.makeText(this, "Select an option!", Toast.LENGTH_SHORT).show()
                }
            } else {
                questionIdx++
                setQuestion() // Back to phase 1
                // Or goToResult()
            }
        } else { // If an option is clicked
            // Call answer_view
            for (optionTv in optionTvs) {
                if (view == optionTv) {
                    selectedOptionView(optionTv)
                }
            }
        }
    }

    private fun dpToPx(): Int {
        return (10 * Resources.getSystem().displayMetrics.density).toInt()
    }
}