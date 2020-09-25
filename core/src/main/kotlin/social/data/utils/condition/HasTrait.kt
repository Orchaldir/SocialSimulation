package social.data.utils.condition

import social.data.character.trait.Trait
import social.data.utils.CharacterRole
import social.data.utils.Context

class HasTrait(
    private val id: CharacterRole,
    private val trait: Trait,
) : Condition {

    override fun evaluate(context: Context): Boolean {
        val character = context.roles[id] ?: return false

        return character.traitComponent.hasTrait(trait)
    }

}