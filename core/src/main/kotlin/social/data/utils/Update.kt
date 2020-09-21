package social.data.utils

import social.data.character.Character
import social.data.character.relationship.RelationshipManager

data class Update(
    val context: Context,
    val updatedCharacters: Map<CharacterRole, Character> = emptyMap(),
    private val updatedRelationshipManager: RelationshipManager? = null,
) {

    fun getCharacter(role: CharacterRole) = updatedCharacters.getOrElse(role) { context.roles[role] }

    fun getRelationshipManager() = updatedRelationshipManager ?: context.relationshipManager

}