package social.data.utility.effect

import social.data.character.attitude.Attitude
import social.data.character.attitude.AttitudeComponent
import social.data.character.attitude.AttitudeType
import social.data.utility.CharacterRole
import social.data.utility.Update

data class ModifyAttitude(
    val from: CharacterRole,
    val toward: CharacterRole,
    val type: AttitudeType,
    val modifier: Int,
) : Effect {

    override fun applyTo(update: Update): Update {
        val fromCharacter = requireNotNull(update.getCharacter(from))
        val towardCharacter = requireNotNull(update.getCharacter(toward))

        val oldAttitude = fromCharacter.attitudeComponent.getAttitude(towardCharacter.id, type)
        val oldValue = oldAttitude?.value ?: type.defaultValue
        val newValue = oldValue + modifier
        val newAttitude = Attitude(type, newValue)

        val newAttitudesToward = HashMap(fromCharacter.attitudeComponent.attitudes[fromCharacter.id] ?: emptyMap())
        newAttitudesToward[type] = newAttitude

        val newAttitudes = HashMap(fromCharacter.attitudeComponent.attitudes)
        newAttitudes[towardCharacter.id] = newAttitudesToward

        val newFromCharacter = fromCharacter.copy(attitudeComponent = AttitudeComponent(newAttitudes))

        val newUpdatedCharacters = HashMap(update.updatedCharacters)
        newUpdatedCharacters[from] = newFromCharacter

        return update.copy(updatedCharacters = newUpdatedCharacters)
    }

}