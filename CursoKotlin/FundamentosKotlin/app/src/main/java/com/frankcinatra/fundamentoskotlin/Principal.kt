package com.frankcinatra.fundamentoskotlin
const val separator = "~~~~~~~~~~~~~~~~~~~"

fun main(){
    newTopic("Hola Kotlin")
    newTopic("Variables y Constantes")
    //val a = 2
    //val a = "Hola"
    val a = true
    println("a = $a")

    //var b = 2
    var b: Int
    b = 5
    println("b = $b")

    var objNull: Any?    //el signo ? dice que puede ser null y Any que es de cualquier tipo
    objNull = null
    objNull = "Hi"
    println(objNull)
}

fun newTopic(topic: String){
    //print("\n~~~~~~~~~~~~~~~~~~~ $topic ~~~~~~~~~~~~~~~~~~~\n")
    println("\n$separator $topic $separator")
}