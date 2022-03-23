package com.frankcinatra.fundamentoskotlin.classes

class Smartphone(number: Int,val isPrivate: Boolean): Phone(number) {
    override fun showNumber() {
        if(isPrivate==true) println("Desconocido")
        else super.showNumber()

    }
}