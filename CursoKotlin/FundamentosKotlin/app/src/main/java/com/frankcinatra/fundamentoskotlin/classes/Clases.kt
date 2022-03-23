package com.frankcinatra.fundamentoskotlin.classes

import com.frankcinatra.fundamentoskotlin.newTopic

fun main(){
    newTopic("Clases")
    val phone: Phone = Phone(123456789)
    phone.call()
    phone.showNumber()
    //println(phone.number)

    newTopic("Herencia")
    val smartphone = Smartphone(987654321, true)
    smartphone.call()

    newTopic("Sobreescritura")
    smartphone.showNumber()
    println("Privado? ${smartphone.isPrivate}")

    newTopic("Data Clases")
    val myUser = User(0, "Frank", "Grijalva", Group.FAMILY.ordinal)
    val bro = myUser.copy(1, "Isra")
    val friend = bro.copy(id=2, group = Group.FRIEND.ordinal)

    println(myUser.component3())
    println(myUser)
    println(bro)
    println(friend)

    newTopic("Funciones de alcance")
    with(smartphone){
        println("Privado? $isPrivate")
        call()
    }

    friend.apply {
        group = Group.WORK.ordinal
        name = "Victor"
        lastName = "Lechuga"
    }
    println(friend)
}