package social.data.utils.condition

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import social.data.character.Character
import social.data.character.CharacterId
import social.data.character.trait.Trait
import social.data.character.trait.TraitComponent
import social.data.utils.CharacterRole
import social.data.utils.Context
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class HasTraitTest {

    private val trait = Trait("relationship")

    private val id: CharacterId = 10

    private val traitComponent = mockk<TraitComponent>()

    private val character = Character(id, traitComponent = traitComponent)

    private val context = Context(mapOf(CharacterRole.SPEAKER to character))

    @Test
    fun `Character has a trait`() {
        val condition = HasTrait(CharacterRole.SPEAKER, trait)

        every { traitComponent.hasTrait(trait) } returns true

        assertTrue(condition.evaluate(context))
    }

    @Test
    fun `Character doesn't have a trait`() {
        val condition = HasTrait(CharacterRole.SPEAKER, trait)

        every { traitComponent.hasTrait(trait) } returns false

        assertFalse(condition.evaluate(context))
    }

    @Test
    fun `Role not supported`() {
        val condition = HasTrait(CharacterRole.TARGET, trait)

        assertFalse(condition.evaluate(context))

        confirmVerified(traitComponent)
    }
}