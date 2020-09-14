package social.data.character.attitude

import social.data.character.CharacterId

data class AttitudeComponent(val attitudes: Map<CharacterId, Map<AttitudeType, Attitude>>) {

    fun getAttitudesTowards(id: CharacterId) = attitudes.getOrDefault(id, emptyMap())

    fun getAttitudesOfType(type: AttitudeType) : Map<CharacterId,Attitude> {
        val result = mutableMapOf<CharacterId,Attitude>()

        attitudes.forEach { (id, attitudes) ->
            val attitude = attitudes[type]
            if (attitude != null) {
                result[id] = attitude
            }
        }

        return result
    }
}