package social.data.character.trait

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class TraitComponentTest {

    private val trait0 = Trait("trait0")
    private val trait1 = Trait("trait1")
    private val trait2 = Trait("trait2")

    private val component = TraitComponent(trait0, trait1)

    @Test
    fun `Check if a character has a trait`() {
        assertTrue(component.hasTrait(trait0))
        assertTrue(component.hasTrait(trait1))
        assertFalse(component.hasTrait(trait2))
    }

    @Nested
    inner class AddTrait {

        @Test
        fun `Add a new trait`() {
            assertThat(component.update(addTrait(trait2))).isEqualTo(TraitComponent(trait0, trait1, trait2))
        }

        @Test
        fun `Add an existing trait`() {
            assertThat(component.update(addTrait(trait0))).isEqualTo(component)
        }
    }

    @Nested
    inner class RemoveTrait {

        @Test
        fun `Remove a trait`() {
            assertThat(component.update(removeTrait(trait1))).isEqualTo(TraitComponent(trait0))
        }

        @Test
        fun `Remove a non-existing trait`() {
            assertThat(component.update(removeTrait(trait2))).isEqualTo(component)
        }
    }
}