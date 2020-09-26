package social.data.utils.effect

import social.data.character.relationship.Relationship
import social.data.utils.CharacterRole
import social.data.utils.Update

data class AddRelationship(
    private val role0: CharacterRole,
    private val role1: CharacterRole,
    private val relationship: Relationship,
) : Effect {

    override fun applyTo(update: Update): Update {
        val character0 = requireNotNull(update.getCharacter(role0))
        val character1 = requireNotNull(update.getCharacter(role1))

        val updatedRelationshipManager =
            update.getRelationshipManager().addRelationship(character0.id, character1.id, relationship)

        return update.copy(updatedRelationshipManager = updatedRelationshipManager)
    }

}