package social.data.character.relationship

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import social.data.character.CharacterId
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class RelationshipManagerTest {

    private val relationship0 = Relationship("r0")
    private val relationship1 = Relationship("r1")
    private val relationship2 = Relationship("r2")

    private val id0: CharacterId = 0
    private val id1: CharacterId = 1
    private val id2: CharacterId = 2

    private val manager = RelationshipManager(
        createKey(id0, id1) to setOf(relationship0, relationship1),
        createKey(id0, id2) to setOf(relationship2),
    )

    @Nested
    inner class HasRelationship {

        @Test
        fun `Check if 2 characters have a specific relationship`() {
            assertZeroToOne(id0, id1)
            assertZeroToTwo(id0, id2)
            assertOneToTwo(id1, id2)
        }

        @Test
        fun `Check if the other direction has the same result`() {
            assertZeroToOne(id1, id0)
            assertZeroToTwo(id2, id0)
            assertOneToTwo(id2, id1)
        }

        private fun assertZeroToOne(first: CharacterId, second: CharacterId) {
            assertTrue(manager.hasRelationship(first, second, relationship0))
            assertTrue(manager.hasRelationship(first, second, relationship1))
            assertFalse(manager.hasRelationship(first, second, relationship2))
        }

        private fun assertZeroToTwo(first: CharacterId, second: CharacterId) {
            assertFalse(manager.hasRelationship(first, second, relationship0))
            assertFalse(manager.hasRelationship(first, second, relationship1))
            assertTrue(manager.hasRelationship(first, second, relationship2))
        }

        private fun assertOneToTwo(first: CharacterId, second: CharacterId) {
            assertFalse(manager.hasRelationship(first, second, relationship0))
            assertFalse(manager.hasRelationship(first, second, relationship1))
            assertFalse(manager.hasRelationship(first, second, relationship2))
        }
    }

}