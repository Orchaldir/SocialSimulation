package social.data.utility.condition

import social.data.character.attitude.AttitudeType
import social.data.utility.CharacterRole
import social.data.utility.Context

data class AttitudeCondition(
    val from: CharacterRole,
    val toward: CharacterRole,
    val type: AttitudeType,
    val threshold: Int
) : Condition {

    override fun evaluate(context: Context): Boolean {
        val fromCharacter = context.roles[from] ?: return false
        val towardCharacter = context.roles[toward] ?: return false
        val attitude = fromCharacter.attitudeComponent.getAttitudeValue(towardCharacter.id, type)
        return attitude >= threshold
    }

}