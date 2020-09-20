package social.data.character.relationship

import social.data.character.CharacterId

class RelationshipManager(private val relationships: Map<RelationshipKey, Set<Relationship>>) {

    fun hasRelationship(id0: CharacterId, id1: CharacterId, relationship: Relationship) =
        relationships[createKey(id0, id1)]?.contains(relationship) ?: false

}