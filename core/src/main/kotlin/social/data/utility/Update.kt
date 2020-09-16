package social.data.utility

import social.data.character.Character

data class Update(val context: Context, val updatedCharacters: Map<CharacterRole, Character> = emptyMap()) {

    fun getCharacter(role: CharacterRole) = updatedCharacters.getOrElse(role) { context.roles[role] }

}