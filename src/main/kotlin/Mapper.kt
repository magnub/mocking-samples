package org.example

interface Mapper {
    // Convert input string different string
    fun map(input: String): String
}

class DefaultMapper(
    val mapping: (Char) -> Char = defaultMapping
) : Mapper {
    override fun map(input: String) =
        input.toCharArray().map {
            mapping(it)
        }.joinToString(separator = "")
}

private val defaultMapping: (Char) -> Char = { char ->
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
