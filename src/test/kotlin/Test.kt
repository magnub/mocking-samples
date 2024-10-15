import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.example.DefaultMapper
import org.example.Mapper
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Test {

    private val hacker = mockk<DefaultMapper>()

    @Test
    fun `call mock`() {
        every { hacker.remap(any()) } returns "hello"

        assertEquals("hello", hacker.remap("r"))
        verify { hacker.remap("r") }
        confirmVerified(hacker)
    }

    @Test
    fun `call real`() {
        val hacker = DefaultMapper(mapping)

        assertEquals("1337", hacker.remap("leet"))
    }

    @Test
    fun `override mapper real`() {
        val hacker = DefaultMapper { _ -> 'a' }

        assertEquals("aaaa", hacker.remap("leet"))
    }

    @Test
    fun `override mapper and mockk`() {
        val remapper = spyk<Mapper>(DefaultMapper { _ -> 'a' })

        every { remapper.remap(any()) } returns "hello"

        assertEquals("hello", remapper.remap("leet"))

        verify { remapper.remap("leet") }
    }

    @Test
    fun `call original for specific param`() {
        val input1 = "asdfdasfasdf"
        val leet = "leet"
        val remapper = spyk<Mapper>(DefaultMapper(mapping))

        every { remapper.remap(any()) } returns "hello"
        every { remapper.remap(leet) } answers { callOriginal() }

        assertEquals("hello", remapper.remap(input1))
        assertEquals("hello", remapper.remap("abcd".random().toString()))
        assertEquals("1337", remapper.remap(leet))

        verify { remapper.remap(input1) }
        verify { remapper.remap(leet) }
    }
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