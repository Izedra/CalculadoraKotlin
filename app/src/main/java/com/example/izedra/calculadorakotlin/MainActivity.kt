package com.example.izedra.calculadorakotlin

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var operacion = ""
    var memoria: Float = 0f
    var num: Float = 0f
    var resultado: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun pulsar(view: View){
        var boton = view as Button
        var num = boton.text
        tv_nums.text = tv_nums.text.toString() + num
    }

    fun borrar(view: View){
        if (tv_nums.length() > 0) {
            var numeros = tv_nums.text.toString()

            numeros = numeros.substring(
                    startIndex = 0,
                    endIndex = numeros.lastIndex
            )

            tv_nums.text = numeros
        }
    }

    fun sumar(view: View){
        igual(view)
        operacion = "sumar"
    }

    fun restar(view: View){
        igual(view)
        operacion = "restar"
    }

    fun multiplicar(view: View){
        igual(view)
        operacion = "multiplicar"
    }

    fun dividir(view: View){
        igual(view)
        operacion = "dividir"
    }

    fun acSumar(view: View){
        var valor1: Float

        try {
            valor1 = tv_nums.text.toString().toFloat()
        }catch (e: NumberFormatException){
            valor1 = 0f
        }

        memoria = memoria + valor1
        tv_resultado.text = memoria.toString() + " +"

        tv_nums.text = ""
    }

    fun acRestar(view: View){
        var valor1: Float

        try {
            valor1 = tv_nums.text.toString().toFloat()
        }catch (e: NumberFormatException){
            valor1 = 0f
        }

        memoria = memoria - valor1
        tv_resultado.text = memoria.toString() + " -"

        tv_nums.text = ""
    }

    fun acMultiplicar(view: View){
        var valor1: Float

        try {
            valor1 = tv_nums.text.toString().toFloat()
        }catch (e: NumberFormatException){
            valor1 = 0f
        }

        if(valor1 != 0f ) {
            memoria = memoria * valor1
            tv_resultado.text = memoria.toString() + " x"
        }

        tv_nums.text = ""
    }

    fun acDividir(view: View){
        var valor1: Float

        try {
            valor1 = tv_nums.text.toString().toFloat()
        }catch (e: NumberFormatException){
            valor1 = 1f
        }

        memoria = memoria / valor1
        println(memoria)
        println(valor1)
        tv_resultado.text = memoria.toString()

        tv_nums.text = ""
    }

    fun sino(){
        try {
            memoria = tv_nums.text.toString().toFloat()
        }catch (e: NumberFormatException){}

        tv_resultado.text = memoria.toString()
        tv_nums.text = ""
    }

    fun igual(view: View) {

        when (operacion) {
            "sumar" -> acSumar(view)
            "restar" -> acRestar(view)
            "multiplicar" -> acMultiplicar(view)
            "dividir" -> acDividir(view)
            else -> sino()
        }

        tv_resultado.text = memoria.toString()
        operacion = ""
    }

    fun limpiar(view: View){
        tv_nums.text = ""
        tv_resultado.text = ""
        operacion = ""
        memoria = 0f
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState (savedInstanceState: Bundle?){
        if (tv_nums.text.toString() != "" ) {
            savedInstanceState!!.putFloat("numero", tv_nums.text.toString().toFloat())
        }

        if (memoria != 0f) {
            savedInstanceState!!.putFloat("resultado", memoria)
        }

        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState!!.getFloat("numero") != 0f) {
            tv_nums.text = savedInstanceState.getFloat("numero").toString()
        }

        if (savedInstanceState!!.getFloat("resultado") != 0f) {
            memoria = savedInstanceState.getFloat("resultado") as Float
            tv_resultado.text = memoria.toString()
        }

    }

}
