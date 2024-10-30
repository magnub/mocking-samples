package org.example

fun Boolean.runIfElse(onTrue: () -> Unit, onFalse: () -> Unit) {
    when (this) {
        true -> onTrue()
        false -> onFalse()
    }
}

fun main() {
    val myValTrue: Boolean = true
    myValTrue.runIfElse(::myFunTrue, ::myFunFalse)
    println("----")
    val myValFalse: Boolean = false
    myValFalse.runIfElse(::myFunTrue, ::myFunFalse)
    val greetFunction: (String) -> Unit = ::greet
    greetFunction("mange")
}

fun myFunTrue(): Boolean {
    println("true")
    return true
}

fun myFunFalse(): Boolean {
    println("false")
    return false
}

fun greet(name: String) {
    println("Hello $name")
}