package social.data.utils.effect

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import social.data.character.Character
import social.data.character.CharacterId
import social.data.character.attitude.AttitudeComponent
import social.data.character.attitude.AttitudeType
import social.data.utils.CharacterRole.SPEAKER
import social.data.utils.CharacterRole.TARGET
import social.data.utils.Context
import social.data.utils.Update

internal class ModifyAttitudeTest {

    private val speakerId: CharacterId = 0
    private val targetId: CharacterId = 1

    private val type = AttitudeType("type", 3)

    private val attitudes0 = mockk<AttitudeComponent>()
    private val attitudes1 = mockk<AttitudeComponent>()
    private val attitudes2 = mockk<AttitudeComponent>()

    private val speaker = Character(speakerId, attitudes0)
    private val target = Character(targetId, attitudes1)

    private val effect = ModifyAttitude(TARGET, SPEAKER, type, 12)

    @Test
    fun `Update an attitude`() {
        val context = Context(mapOf(SPEAKER to speaker, TARGET to target))
        val update = Update(context)

        every { attitudes1.modifyAttitude(speakerId, type, 12) } returns attitudes2

        assertThat(effect.applyTo(update))
            .isEqualTo(Update(context, updatedCharacters = mapOf(TARGET to Character(targetId, attitudes2))))
    }
}