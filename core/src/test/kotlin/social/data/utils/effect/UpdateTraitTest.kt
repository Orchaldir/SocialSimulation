package social.data.utils.effect

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import social.data.character.Character
import social.data.character.trait.Trait
import social.data.character.trait.TraitComponent
import social.data.utils.CharacterRole.SPEAKER
import social.data.utils.Context
import social.data.utils.Update

internal class UpdateTraitTest {

    private val trait = Trait("trait")

    private val component0 = mockk<TraitComponent>()
    private val component1 = mockk<TraitComponent>()

    private val id = 4

    private val character = Character(id, traitComponent = component0)
    private val context = Context(mapOf(SPEAKER to character))
    private val update = Update(context)

    @Test
    fun `Add a trait`() {
        val effect = createAddTrait(SPEAKER, trait)

        every { component0.update(any()) } returns component1

        assertThat(effect.applyTo(update)).isEqualTo(
            Update(
                context,
                mapOf(SPEAKER to Character(id, traitComponent = component1))
            )
        )
    }

}