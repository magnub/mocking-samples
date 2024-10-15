package org.example

fun main() {
    //val start = System.nanoTime()
    println("testar lite ha sa kul detta blev".remap())
    /*val time = System.nanoTime() - start
    println("Ran for: $time ns")
    println("Ran for: ${time / 1000} us")
    println("Ran for: ${time / (1000 * 1000)} ms")*/
}

val mapping: (Char) -> Char = { char ->
    when (char) {
        'a' -> '4'
        'b' -> '8'
        'e' -> '3'
        'i' -> '1'
        'l' -> '1'
        'g' -> '6'
        'o' -> '0'
        's' -> '5'
        't' -> '7'
        else -> char
    }
}

fun String.remap() = DefaultMapper(mapping).remap(this)