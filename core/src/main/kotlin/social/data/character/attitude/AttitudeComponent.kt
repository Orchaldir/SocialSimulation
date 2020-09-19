package social.data.character.attitude

import social.data.character.CharacterId

data class AttitudeComponent(val attitudes: Map<Pair<CharacterId, AttitudeType>, Int>) {

    fun getAttitudesTowards(id: CharacterId) = attitudes
        .filterKeys { it.first == id }
        .mapKeys { it.key.second }

    fun getAttitudesOfType(type: AttitudeType) = attitudes
        .filterKeys { it.second == type }
        .mapKeys { it.key.first }

    fun getAttitude(id: CharacterId, type: AttitudeType) = attitudes[Pair(id, type)] ?: type.defaultValue
}