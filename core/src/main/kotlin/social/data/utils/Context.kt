package social.data.utils

import social.data.character.Character

data class Context(val roles: Map<CharacterRole, Character> = emptyMap())