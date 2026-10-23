package com.example.guessthenumber.gorohovilya
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class GuesserActivity : AppCompatActivity() {
    private var begin: Int = 0
    private var end: Int = 100

    private lateinit var guessedNumber: TextView
    private lateinit var buttonsGroup: LinearLayout
    private lateinit var yesBtn: Button
    private lateinit var greaterBtn: Button
    private lateinit var lesserBtn: Button

    private var candidate: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guesser_activity)

        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)

        guessedNumber = findViewById(R.id.GuessedNumber)
        buttonsGroup = findViewById(R.id.ButtonsGroup)
        yesBtn = findViewById(R.id.yesBtn)
        greaterBtn = findViewById(R.id.GreaterBtn)
        lesserBtn = findViewById(R.id.LesserBtn)

        nextGuess()

        yesBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        greaterBtn.setOnClickListener {
            begin = candidate + 1
            if (begin > end) {
                showLose()
            } else {
                nextGuess()
            }
        }

        lesserBtn.setOnClickListener {
            end = candidate - 1
            if (begin > end) {
                showLose()
            } else {
                nextGuess()
            }
        }
    }

    private fun nextGuess() {
        candidate = if (begin == end) begin
        else kotlin.random.Random.nextInt(begin, end + 1)
        guessedNumber.text = candidate.toString()

        if (begin == end) {
            greaterBtn.isEnabled = false
            lesserBtn.isEnabled = false
        }
    }

    private fun showLose() {
        guessedNumber.text = "Ты ошибся!"
        greaterBtn.isEnabled = false
        lesserBtn.isEnabled = false
        yesBtn.isEnabled = false
    }
}