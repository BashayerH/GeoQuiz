package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWEN= " what the hell cheater!!"

class CheatActivity : AppCompatActivity() {
    private lateinit var answertTv:TextView
    private lateinit var showAnswerBTN:Button

    var answerIsTrueOrFalse =false//نعرف متغير نستقبل فيه البوت اكسترا

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)


        answertTv=findViewById(R.id.answer_tv)
        showAnswerBTN=findViewById(R.id.cheat_result)

        answerIsTrueOrFalse =intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE_FALSE,false)

        showAnswerBTN.setOnClickListener {
            answertTv.setText(answerIsTrueOrFalse.toString())
            setAnswerShownResult()
        }
    }
    fun setAnswerShownResult(){
        val data =Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWEN,true)
        }
        setResult(Activity.RESULT_OK,data)
    }
}