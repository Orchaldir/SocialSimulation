package social.data.utils.condition

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
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class HasRelationshipTest {

    private val relationship = Relationship("relationship")

    private val id0: CharacterId = 10
    private val id1: CharacterId = 11

    private val character0 = Character(id0)
    private val character1 = Character(id1)

    private val manager = mockk<RelationshipManager>()

    private val context =
        Context(
            mapOf(SPEAKER to character0, TARGET to character1),
            relationshipManager = manager
        )

    @Test
    fun `Characters have the relationship`() {
        val condition = HasRelationship(SPEAKER, TARGET, relationship)

        every { manager.hasRelationship(id0, id1, relationship) } returns true

        assertTrue(condition.evaluate(context))
    }

    @Test
    fun `Characters don't have the relationship`() {
        val condition = HasRelationship(TARGET, SPEAKER, relationship)

        every { manager.hasRelationship(id1, id0, relationship) } returns false

        assertFalse(condition.evaluate(context))
    }

    @Test
    fun `First character is missing`() {
        val context = Context(mapOf(TARGET to character1))
        val condition = HasRelationship(SPEAKER, TARGET, relationship)

        assertFalse(condition.evaluate(context))
    }

    @Test
    fun `Second character is missing`() {
        val context = Context(mapOf(SPEAKER to character1))
        val condition = HasRelationship(SPEAKER, TARGET, relationship)

        assertFalse(condition.evaluate(context))
    }
}