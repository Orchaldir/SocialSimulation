package social.data.character.attitude

import social.data.character.CharacterId

data class AttitudeComponent(private val attitudes: Map<Pair<CharacterId, AttitudeType>, Int>) {

    constructor(vararg attitudes: Pair<Pair<CharacterId, AttitudeType>, Int>) : this(attitudes.toMap())

    fun getAttitudesTowards(id: CharacterId) = attitudes
        .filterKeys { it.first == id }
        .mapKeys { it.key.second }

    fun getAttitudesOfType(type: AttitudeType) = attitudes
        .filterKeys { it.second == type }
        .mapKeys { it.key.first }

    fun getAttitude(id: CharacterId, type: AttitudeType) = attitudes[Pair(id, type)] ?: type.defaultValue

    fun modifyAttitude(id: CharacterId, type: AttitudeType, modifier: Int): AttitudeComponent {
        val newAttitudes = HashMap(attitudes)

        val oldAttitude = getAttitude(id, type)
        val newAttitude = oldAttitude + modifier

        newAttitudes[Pair(id, type)] = newAttitude

        return AttitudeComponent(newAttitudes)
    }
}