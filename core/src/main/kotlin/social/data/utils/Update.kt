package social.data.utils

import social.data.character.Character
import social.data.character.relationship.RelationshipManager

data class Update(
    private val context: Context,
    private val updatedCharacters: Map<CharacterRole, Character> = emptyMap(),
    private val updatedRelationshipManager: RelationshipManager? = null,
) {

    fun getUpdatedCharacters() = updatedCharacters

    fun getCharacter(role: CharacterRole) = updatedCharacters.getOrElse(role) { context.roles[role] }

    fun getRelationshipManager() = updatedRelationshipManager ?: context.relationshipManager

}

fun <K, V> Map<K, V>.copyAndAdd(key: K, value: V): Map<K, V> {
    val newMap = HashMap(this)
    newMap[key] = value
    return newMap
}