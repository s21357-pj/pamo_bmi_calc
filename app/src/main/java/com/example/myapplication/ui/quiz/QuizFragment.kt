package com.example.myapplication.ui.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentQuizBinding

class QuizFragment : Fragment(), View.OnClickListener {
    private var falseButton: Button? = null
    private var trueButton: Button? = null
    private var nextButton: ImageButton? = null
    private var prevButton: ImageButton? = null
    private var Image: ImageView? = null
    private var questionTextView: TextView? = null
    private var correct = 0

    // to keep current question track
    private var currentQuestionIndex = 0
    private val questionBank = arrayOf( // array of objects of class Question
        // providing questions from string
        // resource and the correct ans
        Question(R.string.a, false),
        Question(R.string.b, true),
        Question(R.string.c, true),
        Question(R.string.d, true),
        Question(R.string.e, true),
        Question(R.string.f, false)
    )
    private val binding: FragmentQuizBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        args: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        // setting up the buttons
        // associated with id
        falseButton = view.findViewById(R.id.false_button)
        trueButton = view.findViewById(R.id.true_button)
        nextButton = view.findViewById(R.id.next_button)
        prevButton = view.findViewById(R.id.prev_button)
        // register our buttons to listen to
        // click events
        questionTextView = view.findViewById(R.id.answer_text_view)
        Image = view.findViewById(R.id.myimage)
        falseButton?.setOnClickListener(this)
        trueButton?.setOnClickListener(this)
        nextButton?.setOnClickListener(this)
        prevButton?.setOnClickListener(this)
        return view
    }

    @SuppressLint("SetTextI18n", "NonConstantResourceId")
    override fun onClick(v: View) {
        // checking which button is
        // clicked by user
        // in this case user choose false
        when (v.id) {
            R.id.false_button -> checkAnswer(false)
            R.id.true_button -> checkAnswer(true)
            R.id.next_button ->                 // go to next question
                // limiting question bank range
                if (currentQuestionIndex < 7) {
                    currentQuestionIndex = currentQuestionIndex + 1
                    // we are safe now!
                    // last question reached
                    // making buttons
                    // invisible
                    if (currentQuestionIndex == 6) {
                        questionTextView!!.text = getString(
                            R.string.correct, correct
                        )
                        nextButton!!.visibility = View.INVISIBLE
                        prevButton!!.visibility = View.INVISIBLE
                        trueButton!!.visibility = View.INVISIBLE
                        falseButton!!.visibility = View.INVISIBLE
                        if (correct > 3) questionTextView!!.text = ("CORRECTNESS IS " + correct
                                + " "
                                + "OUT OF 6")
                        // showing correctness
                        //else
                        //Image.setImageResource(
                        //        R.drawable.resu);
                        // if correctness<3 showing sad emoji
                    } else {
                        updateQuestion()
                    }
                }
            R.id.prev_button -> if (currentQuestionIndex > 0) {
                currentQuestionIndex = ((currentQuestionIndex - 1)
                        % questionBank.size)
                updateQuestion()
            }
        }
    }

    private fun updateQuestion() {
        Log.d(
            "Current",
            "onClick: $currentQuestionIndex"
        )
        questionTextView?.setText(
            questionBank[currentQuestionIndex]
                .answerResId
        )
        when (currentQuestionIndex) {
            1 -> {}
            2 -> {}
            3 -> {}
            4 -> {}
            5 -> {}
            6 -> {}
            7 -> {}
        }
    }

    private fun checkAnswer(userChooseCorrect: Boolean) {
        val answerIsTrue = questionBank[currentQuestionIndex]
            .isAnswerTrue
        // getting correct ans of current question
        val toastMessageId: Int
        // if ans matches with the
        // button clicked
        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer
            correct++
        } else {
            // showing toast
            // message correct
            toastMessageId = R.string.wrong_answer
        }
        Toast.makeText(
            activity, toastMessageId,
            Toast.LENGTH_SHORT
        )
            .show()
    }
}