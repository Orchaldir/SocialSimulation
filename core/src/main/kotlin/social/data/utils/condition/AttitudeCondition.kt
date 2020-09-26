package social.data.utils.condition

import social.data.character.attitude.AttitudeType
import social.data.utils.CharacterRole
import social.data.utils.Context

data class AttitudeCondition(
    private val from: CharacterRole,
    private val toward: CharacterRole,
    private val type: AttitudeType,
    private val threshold: Int
) : Condition {

    override fun evaluate(context: Context): Boolean {
        val fromCharacter = context.roles[from] ?: return false
        val towardCharacter = context.roles[toward] ?: return false
        val attitude = fromCharacter.attitudeComponent.getAttitude(towardCharacter.id, type)
        return attitude >= threshold
    }

}