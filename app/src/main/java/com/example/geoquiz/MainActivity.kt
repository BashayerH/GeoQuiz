package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

private const val KEY_INDEX="index"//نستخدم، للبوندل
private const val anotherKEY_INDEX=" text_index"
const val EXTRA_ANSWER_IS_TRUE_FALSE="mainActivity.answer"//نقدر نحط اي اسم
private const val REQUEST_CODE_CHEAT=0


class MainActivity : AppCompatActivity() {

   private lateinit var falseButton:Button
   private lateinit var trueButton:Button
   private lateinit var nextButton: Button
   private lateinit var prevButton: Button
   private lateinit var questionTextView: TextView

   private lateinit var cheatButton: Button




     val TAG ="Main_Activity"


    private val QuizViewModel by lazy {ViewModelProvider(this).get(QuizViewModel::class.java)}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode!= Activity.RESULT_OK){
            return
        }
        if(requestCode== REQUEST_CODE_CHEAT){
            QuizViewModel.isCheater= data?.getBooleanExtra(EXTRA_ANSWER_SHOWEN,false)?:false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate()")  //طريقة الطباعة في الاندرويد

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?:0
        QuizViewModel.currentIndex=currentIndex
        Log.d(TAG,"bundle:$currentIndex")

        //val provider =ViewModelProvider(this)طريقة تعريف المتغير في سطر واحد
        //val QuizViewModel=ViewModelProvider(this).get(QuizViewModel::class.java)
        Log.d(TAG,"hi iam viewModel from main Activity $QuizViewModel")

        falseButton = findViewById(R.id.falseButton)
        trueButton = findViewById(R.id.trueButton)
        nextButton= findViewById(R.id.nextButton)
        prevButton=findViewById(R.id.preButton)
        questionTextView = findViewById(R.id.qusetion_textv)

        cheatButton=findViewById(R.id.toCheat)


        cheatButton.setOnClickListener {
            val intent=Intent(this,CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE_FALSE,QuizViewModel.currentQuestionAnswer)//عرفت معلومات  اضافية وحطيتلها اي دي
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }

        falseButton.setOnClickListener {
            checkAnswae(false)
        }


        trueButton.setOnClickListener {
            checkAnswae(true)
        }

        nextButton.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }
        prevButton.setOnClickListener {
            QuizViewModel.preQuestion()
            updateQuestion()
        }

        questionTextView.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
       // Log.d(TAG,"a valu been saved")
        outState.putInt(KEY_INDEX,QuizViewModel.currentIndex)
      //  outState.putInt(anotherKEY_INDEX,QuizViewModel.currentQuestionText)//سؤال الواجب
    }

    private fun updateQuestion(){
        val questionTextResId =QuizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswae(userAnswer:Boolean){
        val correctAnswer = QuizViewModel.currentQuestionAnswer
        val toastMessage= when{
            QuizViewModel.isCheater -> R.string.Toast_cheater
            userAnswer==correctAnswer->R.string.correct_toast
            else ->R.string.incorrect_toast
        }
        Toast.makeText(this,toastMessage,Toast.LENGTH_LONG).show()

//        if (userAnswer ==correctAnswer){
//            val toast: Toast =Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG)
//            toast.setGravity(Gravity.TOP, 0, 250)
//                toast.show()
//        }else{
//            val toast: Toast = Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG)
//                   toast.setGravity(Gravity.TOP, 0, 250)
//                     toast.show()
//        }
    }
}