package social.data.utils.effect

import social.data.character.attitude.AttitudeType
import social.data.utils.CharacterRole
import social.data.utils.Update
import social.data.utils.copyAndAdd

data class ModifyAttitude(
    val from: CharacterRole,
    val toward: CharacterRole,
    val type: AttitudeType,
    val modifier: Int,
) : Effect {

    override fun applyTo(update: Update): Update {
        val fromCharacter = requireNotNull(update.getCharacter(from))
        val towardCharacter = requireNotNull(update.getCharacter(toward))

        val updatedComponent = fromCharacter.attitudeComponent
            .modifyAttitude(towardCharacter.id, type, modifier)

        val newFromCharacter = fromCharacter.copy(attitudeComponent = updatedComponent)

        val newUpdatedCharacters = update.updatedCharacters.copyAndAdd(from, newFromCharacter)

        return update.copy(updatedCharacters = newUpdatedCharacters)
    }

}