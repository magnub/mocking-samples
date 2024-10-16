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

    private val mockingMapper = mockk<DefaultMapper>()

    @Test
    fun `call mock`() {
        every { mockingMapper.map(any()) } returns "hello"

        assertEquals("hello", mockingMapper.map("r"))
        verify { mockingMapper.map("r") }
        confirmVerified(mockingMapper)
    }

    @Test
    fun `call real`() {
        val hacker = DefaultMapper(mapping)

        assertEquals("1337", hacker.map("leet"))
    }

    @Test
    fun `override mapper real`() {
        val hacker = DefaultMapper { _ -> 'a' }

        assertEquals("aaaa", hacker.map("leet"))
    }

    @Test
    fun `override mapper and mockk`() {
        val mapper = spyk<Mapper>(DefaultMapper { _ -> 'a' })

        every { mapper.map(any()) } returns "hello"

        assertEquals("hello", mapper.map("leet"))

        verify { mapper.map("leet") }
    }

    @Test
    fun `call original for specific param`() {
        val input1 = "asdfdasfasdf"
        val leet = "leet"
        val mapper = spyk<Mapper>(DefaultMapper(mapping))

        every { mapper.map(any()) } returns "hello"
        every { mapper.map(leet) } answers { callOriginal() }

        assertEquals("hello", mapper.map(input1))
        assertEquals("hello", mapper.map("abcd".random().toString()))
        assertEquals("1337", mapper.map(leet))

        verify { mapper.map(input1) }
        verify { mapper.map(leet) }
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