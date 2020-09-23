package social.data.character.relationship

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
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

    private val relationshipsZeroToOne = setOf(relationship0, relationship1)
    private val relationshipsZeroToTwo = setOf(relationship2)

    private val key01 = createKey(id0, id1)
    private val key02 = createKey(id0, id2)

    private val manager = RelationshipManager(
        key01 to relationshipsZeroToOne,
        key02 to relationshipsZeroToTwo,
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

    @Nested
    inner class GetRelationships {

        @Test
        fun `Get all relationships between 2 characters`() {
            assertThat(manager.getRelationships(id0, id1)).isEqualTo(relationshipsZeroToOne)
            assertThat(manager.getRelationships(id0, id2)).isEqualTo(relationshipsZeroToTwo)
        }

        @Test
        fun `Check if the other direction has the same relationships`() {
            assertThat(manager.getRelationships(id1, id0)).isEqualTo(relationshipsZeroToOne)
            assertThat(manager.getRelationships(id2, id0)).isEqualTo(relationshipsZeroToTwo)
        }

        @Test
        fun `Get an empty set, if 2 characters have no relationship`() {
            assertThat(manager.getRelationships(id1, id2)).isEmpty()
        }

    }

    @Nested
    inner class AddRelationship {

        private val newManager = RelationshipManager(key01 to setOf(relationship2))

        @Test
        fun `Add a new relationship`() {
            assertThat(RelationshipManager().addRelationship(id0, id1, relationship2))
                .isEqualTo(newManager)
            assertThat(RelationshipManager().addRelationship(id1, id0, relationship2))
                .isEqualTo(newManager)
        }

        @Test
        fun `Add another relationship between 2 characters`() {
            assertThat(newManager.addRelationship(id0, id1, relationship0))
                .isEqualTo(RelationshipManager(key01 to setOf(relationship0, relationship2)))
        }

        @Test
        fun `Add a relationship between other characters`() {
            assertThat(newManager.addRelationship(id0, id2, relationship0))
                .isEqualTo(RelationshipManager(key01 to setOf(relationship2), key02 to setOf(relationship0)))
        }

        @Test
        fun `Add an existing relationship`() {
            assertThat(newManager.addRelationship(id0, id1, relationship2)).isEqualTo(newManager)
            assertThat(newManager.addRelationship(id1, id0, relationship2)).isEqualTo(newManager)
        }
    }

    @Nested
    inner class RemoveRelationship {

        @Test
        fun `Remove a non-existing relationship`() {
            val manager = RelationshipManager()

            assertThat(manager.removeRelationship(id0, id1, relationship2)).isEqualTo(manager)
        }

        @Test
        fun `Remove a relationship between 2 characters`() {
            val input = RelationshipManager(key01 to setOf(relationship0, relationship2))
            val output = RelationshipManager(key01 to setOf(relationship0))

            assertThat(input.removeRelationship(id1, id0, relationship2)).isEqualTo(output)
        }

        @Test
        fun `Remove the last relationship between 2 characters`() {
            val manager = RelationshipManager(key01 to setOf(relationship2))

            assertThat(manager.removeRelationship(id0, id1, relationship2)).isEqualTo(RelationshipManager())
        }
    }

}