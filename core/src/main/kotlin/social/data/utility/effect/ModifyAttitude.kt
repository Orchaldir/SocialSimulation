package social.data.utility.effect

import social.data.character.Character
import social.data.character.attitude.Attitude
import social.data.character.attitude.AttitudeComponent
import social.data.character.attitude.AttitudeType
import social.data.utility.CharacterRole
import social.data.utility.Update

private fun <K, V> Map<K, V>.copyAndAdd(key: K, value: V): Map<K, V> {
    val newMap = HashMap(this)
    newMap[key] = value
    return newMap
}

data class ModifyAttitude(
    val from: CharacterRole,
    val toward: CharacterRole,
    val type: AttitudeType,
    val modifier: Int,
) : Effect {

    override fun applyTo(update: Update): Update {
        val fromCharacter = requireNotNull(update.getCharacter(from))
        val towardCharacter = requireNotNull(update.getCharacter(toward))

        val newAttitude = updateAttitude(fromCharacter, towardCharacter)

        val newAttitudesTowardCharacter = (fromCharacter.attitudeComponent.attitudes[fromCharacter.id] ?: emptyMap())
            .copyAndAdd(type, newAttitude)

        val newAttitudes = fromCharacter.attitudeComponent.attitudes
            .copyAndAdd(towardCharacter.id, newAttitudesTowardCharacter)

        val newFromCharacter = fromCharacter.copy(attitudeComponent = AttitudeComponent(newAttitudes))

        val newUpdatedCharacters = update.updatedCharacters.copyAndAdd(from, newFromCharacter)

        return update.copy(updatedCharacters = newUpdatedCharacters)
    }

    private fun updateAttitude(
        fromCharacter: Character,
        towardCharacter: Character
    ): Attitude {
        val oldAttitude = fromCharacter.attitudeComponent.getAttitude(towardCharacter.id, type)
        val oldValue = oldAttitude?.value ?: type.defaultValue
        val newValue = oldValue + modifier
        return Attitude(type, newValue)
    }

}