package social.data.character.trait

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class TraitComponentTest {

    private val trait0 = Trait("trait0")
    private val trait1 = Trait("trait1")
    private val trait2 = Trait("trait2")

    private val component = TraitComponent(setOf(trait0, trait1))

    @Test
    fun `Check if a character has a trait`() {
        assertTrue(component.hasTrait(trait0))
        assertTrue(component.hasTrait(trait1))
        assertFalse(component.hasTrait(trait2))
    }
}