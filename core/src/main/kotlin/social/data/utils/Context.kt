package social.data.utils

import social.data.character.Character
import social.data.character.relationship.RelationshipManager
import social.data.utils.utility.UtilityRuleSet

data class Context(
    val roles: Map<CharacterRole, Character> = emptyMap(),
    val utilityRuleSet: UtilityRuleSet = UtilityRuleSet(),
    val relationshipManager: RelationshipManager = RelationshipManager(),
)