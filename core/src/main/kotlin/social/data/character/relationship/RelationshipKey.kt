package social.data.character.relationship

import social.data.character.CharacterId

data class RelationshipKey(val lowerId: CharacterId, val higherId: CharacterId) {
    init {
        require(lowerId < higherId) { "lowerId $lowerId must be smaller than higherId $higherId. Use createKey()" }
    }
}

fun createKey(id0: CharacterId, id1: CharacterId) = if (id0 < id1) {
    RelationshipKey(id0, id1)
} else {
    RelationshipKey(id1, id0)
}