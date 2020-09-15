package social.data.action

import social.data.character.Character

data class Context(val roles: Map<CharacterRole, Character>)