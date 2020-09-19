package social.data.utils.effect

import social.data.character.attitude.AttitudeComponent
import social.data.character.attitude.AttitudeType
import social.data.utils.CharacterRole
import social.data.utils.Update

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

        val newAttitudes = HashMap(fromCharacter.attitudeComponent.attitudes)

        val oldValue = fromCharacter.attitudeComponent.getAttitude(towardCharacter.id, type)
        val newValue = oldValue + modifier

        newAttitudes[Pair(towardCharacter.id, type)] = newValue

        val newFromCharacter = fromCharacter.copy(attitudeComponent = AttitudeComponent(newAttitudes))

        val newUpdatedCharacters = update.updatedCharacters.copyAndAdd(from, newFromCharacter)

        return update.copy(updatedCharacters = newUpdatedCharacters)
    }

}