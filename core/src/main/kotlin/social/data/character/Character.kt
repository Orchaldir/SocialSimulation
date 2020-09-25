package social.data.character

import social.data.character.attitude.AttitudeComponent
import social.data.character.trait.TraitComponent

data class Character(
    val id: CharacterId,
    val attitudeComponent: AttitudeComponent = AttitudeComponent(),
    val traitComponent: TraitComponent = TraitComponent(),
)