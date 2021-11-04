package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

 const val TAG="QuizViewModel"
class QuizViewModel:ViewModel() {

    private val questionBank = listOf(
        Question(R.string.first_Q, false),
        Question(R.string.sec_Q, false),
        Question(R.string.third_Q, true),
        Question(R.string.fth_Q, true),
        Question(R.string.faith_Q, false)
    )
    var currentIndex = 0
    var isCheater= false

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun preQuestion() {
        if (currentIndex != 0) {
            currentIndex = (currentIndex - 1) % questionBank.size
        } else {
            currentIndex = questionBank.size - 1
        }

        fun textViewQuestion() {
            currentIndex = (currentIndex + 1) % questionBank.size
        }

    }
}