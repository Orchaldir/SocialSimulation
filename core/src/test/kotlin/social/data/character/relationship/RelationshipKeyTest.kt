package social.data.character.relationship

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class RelationshipKeyTest {

    private val lowerId = 11
    private val higherId = 42

    @Test
    fun `Create valid key`() {
        val validKey = RelationshipKey(lowerId, higherId)

        assertThat(createKey(lowerId, higherId)).isEqualTo(validKey)
        assertThat(createKey(higherId, lowerId)).isEqualTo(validKey)
    }

    @Test
    fun `Creating a key with the same id twice fails`() {
        assertFailsWith<IllegalArgumentException> { RelationshipKey(lowerId, lowerId) }
        assertFailsWith<IllegalArgumentException> { createKey(lowerId, lowerId) }
    }

    @Test
    fun `lowerId must be smaller than higherId`() {
        assertFailsWith<IllegalArgumentException> { RelationshipKey(higherId, lowerId) }
    }
}