package social.data.utils.condition

import social.data.character.relationship.Relationship
import social.data.utils.CharacterRole
import social.data.utils.Context

class HasRelationship(
    private val from: CharacterRole,
    private val toward: CharacterRole,
    private val relationship: Relationship,
) : Condition {

    override fun evaluate(context: Context): Boolean {
        val fromCharacter = context.roles[from] ?: return false
        val towardCharacter = context.roles[toward] ?: return false

        return context.relationshipManager.hasRelationship(fromCharacter.id, towardCharacter.id, relationship)
    }

}