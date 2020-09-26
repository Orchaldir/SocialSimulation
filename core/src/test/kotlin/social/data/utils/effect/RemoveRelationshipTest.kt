package social.data.utils.effect

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import social.data.character.Character
import social.data.character.CharacterId
import social.data.character.relationship.Relationship
import social.data.character.relationship.RelationshipManager
import social.data.utils.CharacterRole.SPEAKER
import social.data.utils.CharacterRole.TARGET
import social.data.utils.Context
import social.data.utils.Update
import kotlin.test.assertFailsWith

internal class RemoveRelationshipTest {

    private val speakerId: CharacterId = 0
    private val targetId: CharacterId = 1

    private val relationship = Relationship("relationship")

    private val manager = mockk<RelationshipManager>()
    private val updatedManager = mockk<RelationshipManager>()

    private val speaker = Character(speakerId)
    private val target = Character(targetId)

    private val effect = RemoveRelationship(TARGET, SPEAKER, relationship)

    @Test
    fun `Remove a relationship`() {
        val context = Context(mapOf(SPEAKER to speaker, TARGET to target), relationshipManager = manager)
        val update = Update(context)

        every { manager.removeRelationship(targetId, speakerId, relationship) } returns updatedManager

        assertThat(effect.applyTo(update)).isEqualTo(Update(context, updatedRelationshipManager = updatedManager))
    }

    @Test
    fun `Role0 not available`() {
        val update = Update(Context(mapOf(SPEAKER to speaker)))

        assertFailsWith<IllegalArgumentException> { effect.applyTo(update) }
    }

    @Test
    fun `Role1 not available`() {
        val update = Update(Context(mapOf(TARGET to target)))

        assertFailsWith<IllegalArgumentException> { effect.applyTo(update) }
    }
}