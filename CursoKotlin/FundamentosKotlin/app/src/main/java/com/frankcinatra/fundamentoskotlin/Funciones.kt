package com.frankcinatra.fundamentoskotlin

import kotlin.math.abs

fun main(){
    sayHello()

    newTopic("Argumentos")
    //println(sum(2,3))
    val a = 2
    val b = 3
    println("$a + $b = ${sum(a,b)}")
    println("$a - $b = ${sub(a,b)}")

    newTopic("Infix")

    val c = -3
    println(c.enableAbs(false))
    println("$a + $c = ${sum(a,c)}") //en sum() - c = c.enableAbs(false)
    println("$a + $c = ${sum(a,c.enableAbs(true))}")

    newTopic("Sobrecarga")
    showProduct("Soda", "10%")
    showProduct("Pan")
    showProduct("Caramelo", "15%")
    showProduct(name="Jugo", validity = "15 de Marzo")
}

private fun sayHello(): Unit{//Unit es como el void en Java
    println("Hola kotlin")
}

fun sum(a: Int, b: Int): Int{
    return a+b
}
fun sub(a: Int, b: Int) = a-b

infix fun Int.enableAbs(enable: Boolean) = if(enable) abs(this) else this

fun showProduct(name: String, promo: String = "Sin promoción", validity: String = "Agotar existencias"){
    println("$name = $promo hasta $validity")
}