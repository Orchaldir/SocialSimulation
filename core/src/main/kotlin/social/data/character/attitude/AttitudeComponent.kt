package social.data.character.attitude

import social.data.character.CharacterId

data class AttitudeComponent(val attitudes: Map<CharacterId, List<Attitude>>) {

    fun getAttitudesTowards(id: CharacterId) = attitudes.getOrDefault(id, emptyList())
}