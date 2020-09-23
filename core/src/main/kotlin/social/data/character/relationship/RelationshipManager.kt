package social.data.character.relationship

import social.data.character.CharacterId

data class RelationshipManager(private val relationships: Map<RelationshipKey, Set<Relationship>>) {

    constructor(vararg relationships: Pair<RelationshipKey, Set<Relationship>>) : this(relationships.toMap())

    fun hasRelationship(id0: CharacterId, id1: CharacterId, relationship: Relationship) =
        relationships[createKey(id0, id1)]?.contains(relationship) ?: false

    fun getRelationships(id0: CharacterId, id1: CharacterId) =
        relationships.getOrElse(createKey(id0, id1)) { emptySet() }

    fun addRelationship(id0: CharacterId, id1: CharacterId, relationship: Relationship): RelationshipManager {
        val newRelationships = HashMap(relationships)

        val key = createKey(id0, id1)
        val newValue = newRelationships.getOrElse(key) { emptySet() } + relationship
        newRelationships[key] = newValue

        return RelationshipManager(newRelationships)
    }

    fun removeRelationship(id0: CharacterId, id1: CharacterId, relationship: Relationship): RelationshipManager {
        val newRelationships = HashMap(relationships)

        val key = createKey(id0, id1)
        val oldValue = newRelationships[key] ?: return this
        val newValue = oldValue - relationship

        if (newValue.isEmpty()) {
            newRelationships.remove(key)
        } else {
            newRelationships[key] = newValue
        }

        return RelationshipManager(newRelationships)
    }

}