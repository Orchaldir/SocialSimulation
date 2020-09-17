package social.data.utility.effect

import assertk.assertThat
import assertk.assertions.containsAll
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import social.data.character.Character
import social.data.character.CharacterId
import social.data.character.attitude.Attitude
import social.data.character.attitude.AttitudeComponent
import social.data.character.attitude.AttitudeType
import social.data.utility.CharacterRole.SPEAKER
import social.data.utility.CharacterRole.TARGET
import social.data.utility.Context
import social.data.utility.Update

internal class ModifyAttitudeTest {

    private val id0: CharacterId = 0
    private val id1: CharacterId = 1

    private val type = AttitudeType("type0", 11)

    private val attitude0 = Attitude(type, 7)

    private val attitudes0 = AttitudeComponent(emptyMap())
    private val attitudes1 = AttitudeComponent(mapOf(id0 to mapOf(type to attitude0)))

    private val character0 = Character(id0, attitudes0)
    private val character1 = Character(id1, attitudes1)

    private val effect = ModifyAttitude(TARGET, SPEAKER, type, 12)

    @Test
    fun `Update existing attitude`() {
        val context = Context(mapOf(SPEAKER to character0, TARGET to character1))
        val oldUpdate = Update(context)

        val newUpdate = effect.applyTo(oldUpdate)

        assertThat(newUpdate.context).isEqualTo(context)
        assertThat(newUpdate.updatedCharacters).containsAll(
            TARGET to Character(
                id1,
                AttitudeComponent(mapOf(id0 to mapOf(type to Attitude(type, 19))))
            )
        )
    }

    @Test
    fun `Create a new attitude`() {
        val context = Context(mapOf(SPEAKER to character1, TARGET to character0))
        val oldUpdate = Update(context)

        val newUpdate = effect.applyTo(oldUpdate)

        assertThat(newUpdate.context).isEqualTo(context)
        assertThat(newUpdate.updatedCharacters).containsAll(
            TARGET to Character(
                id0,
                AttitudeComponent(mapOf(id1 to mapOf(type to Attitude(type, 23))))
            )
        )
    }
}