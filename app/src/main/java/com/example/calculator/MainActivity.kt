package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.PI
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    var isLastInputNumber = false
    var hasDotInTextView = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onDigitClicked(view: View){
        val clickedButton = view as Button


        if (!textViewInput.text.contains("²") && !textViewInput.text.contains("|") && !textViewInput.text.contains("!")/* && !textViewInput.text.contains("^") */) {
            if (clickedButton.text == "π"){
                textViewInput.text = "3.14159"
                hasDotInTextView = true
                isLastInputNumber = true
            }
            else {
                textViewInput.append(clickedButton.text)

                isLastInputNumber = true
            }
       }


    }


    fun clearText(view: View){
        textViewInput.text = ""
        isLastInputNumber = false
        hasDotInTextView = false
    }


    fun onDecimalClicked(view: View){
        if (isLastInputNumber && !hasDotInTextView){
            textViewInput.append(".")
            hasDotInTextView = true
        }
        if (!isLastInputNumber){
            textViewInput.text = "0."
            hasDotInTextView = true
        }
    }

    @SuppressLint("SetTextI18n")
    fun onOperatorClicked(view: View) {
        val button = view as Button

        if (!isLastInputNumber && !isOperatorSelected(textViewInput.text.toString())){



            if(button.text == "√" && !textViewInput.text.contains("√") && !textViewInput.text.contains("!") && !textViewInput.text.contains("|") && !textViewInput.text.contains("²") && !textViewInput.text.contains("^")){
                textViewInput.append("√")
                isLastInputNumber = false
                hasDotInTextView = false
            }
            else{
                if (button.text == "3√" && !textViewInput.text.contains("√") && !textViewInput.text.contains("!") && !textViewInput.text.contains("|") && !textViewInput.text.contains("²") && !textViewInput.text.contains("^")){
                    textViewInput.append("3√")
                    isLastInputNumber = false
                    hasDotInTextView = false
                }
            }
        }
        else{
            if (isLastInputNumber && !isOperatorSelected(textViewInput.text.toString()) && !textViewInput.text.contains("√")) {

                if ((button.text.contains("+") || button.text.contains("-") || button.text.contains("×") || button.text.contains("÷")) && !textViewInput.text.contains("^") && !textViewInput.text.contains("²") && !textViewInput.text.contains("|" ) ){
                    textViewInput.append(button.text)
                    isLastInputNumber = false
                    hasDotInTextView = false
                }
                else {
                    if (button.text == "x²" && !textViewInput.text.contains("^")){
                        textViewInput.append("²")
                        isLastInputNumber = false
                        hasDotInTextView = false
                    }
                    else {
                        if (button.text == "x^y" && !textViewInput.text.contains("^") ) {
                            textViewInput.append("^")
                            isLastInputNumber = false
                            hasDotInTextView = false
                        }
                        else{
                            if (button.text == "x^3" && !textViewInput.text.contains("^")){
                                textViewInput.append("^3")
//                                isLastInputNumber = false
                                hasDotInTextView = false
                            }
                            else{
                                if (button.text == "x!" && !textViewInput.text.contains("^")){
                                    textViewInput.append("!")
                                    isLastInputNumber = false
                                    hasDotInTextView = false
                                }
                                else{
                                    if (button.text == "|x|" && !textViewInput.text.contains("^")){
                                        textViewInput.text = "|${textViewInput.text}|"
                                        isLastInputNumber = false
                                        hasDotInTextView = false
                                    }
                                }
                            }
                        }
                    }
                }


            }

        }

    }

//    fun isOperatorSelected(text:String):Boolean{
//        if (text.get(text.lastIndex).toString() == "+" || text.get(text.lastIndex).toString() == "-" || text.get(text.lastIndex).toString() == "×" || text.get(text.lastIndex).toString() == "÷"){
//            return true
//        }
//        else {
//            return false
//        }
//    }


    fun onEqualClicked(view: View) {
        var prefix = ""

        var inputValue = textViewInput.text.toString()

        if (inputValue.contains("²")){
            val splitValueArray = inputValue.split("²")
            var firstNumber = splitValueArray[0]
            val result = firstNumber.toDouble() * firstNumber.toDouble()
            textViewInput.text = result.toString()
            hasDotInTextView = true
            isLastInputNumber = true
        }

        if (inputValue.contains("^")){
            val splitValueArray = inputValue.split("^")
            var firstNumber = splitValueArray[0]
            var secondNumber = splitValueArray[1]
            val result = Math.pow(firstNumber.toDouble(), secondNumber.toDouble())
            textViewInput.text = result.toString()
            hasDotInTextView = true

        }

        if (!isLastInputNumber && !inputValue.contains("|") && !inputValue.contains("!") && !inputValue.contains("²") ) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            return
        }




        if (inputValue.contains("+")){
            val splitValueArray = inputValue.split("+")
            var firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]


            val result = firstNumber.toDouble() + secondNumber.toDouble()

            textViewInput.text = result.toString()
            hasDotInTextView = true
        }

        if (inputValue.contains("×")){
            val splitValueArray = inputValue.split("×")
            var firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]


            val result = firstNumber.toDouble() * secondNumber.toDouble()

            textViewInput.text = result.toString()
            hasDotInTextView = true
        }



        if (inputValue.contains("÷")){
            val splitValueArray = inputValue.split("÷")
            var firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]


            val result = firstNumber.toDouble() / secondNumber.toDouble()

            textViewInput.text = result.toString()
            hasDotInTextView = true

        }

        if (inputValue.contains("3√")){
            val splitValueArray = inputValue.split("3√")

            val secondNumber = splitValueArray[1]


            val result = Math.cbrt(secondNumber.toDouble())

            textViewInput.text = result.toString()
            hasDotInTextView = true

        }
        else{
            if (inputValue.contains("√")){
                val splitValueArray = inputValue.split("√")

                val secondNumber = splitValueArray[1]


                val result = Math.sqrt(secondNumber.toDouble())

                textViewInput.text = result.toString()
                hasDotInTextView = true

            }
        }

        if (inputValue.contains("!")){

            if (inputValue.startsWith("-")){
                Toast.makeText(this,"Invalid format",Toast.LENGTH_SHORT).show()
            }
            else {

                val splitValueArray = inputValue.split("!")
                var firstNumber = splitValueArray[0]

                if (inputValue.contains(".")){
                    val result = factorialDouble(firstNumber.toDouble())
                    textViewInput.text = result.toString()
                    hasDotInTextView = true
                    isLastInputNumber = true
                }
                else {
                    val result = factorial(firstNumber.toLong())
                    textViewInput.text = result.toString()
                    hasDotInTextView = false
                    isLastInputNumber = true
                }
            }

        }








        if (inputValue.get(1) == '-' && inputValue.contains("|")){
            prefix = "-"
            inputValue = inputValue.substring(1,inputValue.length-1)

            val result = Math.abs(inputValue.toDouble())

            textViewInput.text = result.toString()
            hasDotInTextView = true
            prefix = ""
            isLastInputNumber = true
        }
        else{
            if (inputValue.get(1) != '-' && inputValue.contains("|")){

                inputValue = inputValue.substring(1,inputValue.length-1)

                val result = Math.abs(inputValue.toDouble())

                textViewInput.text = result.toString()
                hasDotInTextView = true
                prefix = ""
                isLastInputNumber = true
            }
            else{
                if (inputValue.startsWith("-")){
                prefix = "-"
                inputValue = inputValue.substring(1)
            }

            if (inputValue.contains("-")){
                val splitValueArray = inputValue.split("-")
                var firstNumber = splitValueArray[0]
                val secondNumber = splitValueArray[1]

                if (!prefix.isEmpty()){
                    firstNumber = prefix + firstNumber
                }
                val result = firstNumber.toDouble() - secondNumber.toDouble()

                textViewInput.text = result.toString()
                hasDotInTextView = true
                prefix = ""
            }
            }
        }
//        if (inputValue.contains("|")){
//            val splitValueArray = inputValue.split("|")
//
//            var secondNumber = splitValueArray[1]
//
//            if (!prefix.isEmpty()){
//                secondNumber = prefix + secondNumber
//            }
//
//
//            val result = Math.abs(secondNumber.toDouble())
//
//            textViewInput.text = result.toString()
//            hasDotInTextView = true
//            prefix = ""
//
//        }

//        else{
//            if (inputValue.startsWith("-")){
//                prefix = "-"
//                inputValue = inputValue.substring(1)
//            }
//
//            if (inputValue.contains("-")){
//                val splitValueArray = inputValue.split("-")
//                var firstNumber = splitValueArray[0]
//                val secondNumber = splitValueArray[1]
//
//                if (!prefix.isEmpty()){
//                    firstNumber = prefix + firstNumber
//                }
//                val result = firstNumber.toDouble() - secondNumber.toDouble()
//
//                textViewInput.text = result.toString()
//                hasDotInTextView = true
//                prefix = ""
//            }
//        }















    }



    fun factorial (input: Long):Long{
        var number : Long = 1
        for(output: Long in 1..input){
            number *= output
        }
        return number
    }


    fun factorialDouble (input: Double): Double{
        var input1 = input
        var output = input
        while (input1 >= 1){
            output = output * (input1 - 1)
            input1--
        }
        output = output * sqrt(PI)
        return output
    }


    fun isOperatorSelected(text: String):Boolean{
        if (text.startsWith("-")){
            return false
        }

        if (text.contains("+") || text.contains("-") || text.contains("×") || text.contains("÷")){
            return true
        }
        else {
            return false
        }
    }



}