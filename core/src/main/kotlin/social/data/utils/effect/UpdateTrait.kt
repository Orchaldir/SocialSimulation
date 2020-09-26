package social.data.utils.effect

import social.data.character.trait.Trait
import social.data.character.trait.TraitUpdater
import social.data.character.trait.addTrait
import social.data.character.trait.removeTrait
import social.data.utils.CharacterRole
import social.data.utils.Update
import social.data.utils.copyAndAdd

data class UpdateTrait(
    val role: CharacterRole,
    val updater: TraitUpdater,
) : Effect {

    override fun applyTo(update: Update): Update {
        val character = requireNotNull(update.getCharacter(role))

        val updatedComponent = character.traitComponent.update(updater)
        val updatedCharacter = character.copy(traitComponent = updatedComponent)
        val updatedCharacters = update.updatedCharacters.copyAndAdd(role, updatedCharacter)

        return update.copy(updatedCharacters = updatedCharacters)
    }

}

fun createAddTrait(role: CharacterRole, trait: Trait) = UpdateTrait(role, addTrait(trait))
fun createRemoveTrait(role: CharacterRole, trait: Trait) = UpdateTrait(role, removeTrait(trait))