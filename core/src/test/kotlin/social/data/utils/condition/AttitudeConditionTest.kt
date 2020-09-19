package social.data.utils.condition

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import social.data.character.Character
import social.data.character.CharacterId
import social.data.character.attitude.AttitudeComponent
import social.data.character.attitude.AttitudeType
import social.data.utils.CharacterRole.SPEAKER
import social.data.utils.CharacterRole.TARGET
import social.data.utils.Context

internal class AttitudeConditionTest {

    private val id0: CharacterId = 0
    private val id1: CharacterId = 1

    private val type = AttitudeType("type0", 11)

    private val attitudes0 = mockk<AttitudeComponent>()
    private val attitudes1 = mockk<AttitudeComponent>()

    private val character0 = Character(id0, attitudes0)
    private val character1 = Character(id1, attitudes1)

    private val threshold = 10

    private val condition = AttitudeCondition(SPEAKER, TARGET, type, threshold)

    @Test
    fun `The from role is empty in the context`() {
        val context = Context(mapOf(TARGET to character1))

        assertFalse(condition.evaluate(context))
    }

    @Test
    fun `The toward role is empty in the context`() {
        val context = Context(mapOf(SPEAKER to character0))

        assertFalse(condition.evaluate(context))
    }

    @Test
    fun `Attitude is smaller than the threshold`() {
        val context = Context(mapOf(SPEAKER to character0, TARGET to character1))

        every { character0.attitudeComponent.getAttitudeValue(id1, type) } returns threshold - 5

        assertFalse(condition.evaluate(context))
    }

    @Test
    fun `Attitude is bigger than the threshold`() {
        val context = Context(mapOf(SPEAKER to character0, TARGET to character1))

        every { character0.attitudeComponent.getAttitudeValue(id1, type) } returns threshold + 5

        assertTrue(condition.evaluate(context))
    }

    @Test
    fun `Attitude is equal to the threshold`() {
        val context = Context(mapOf(SPEAKER to character0, TARGET to character1))

        every { character0.attitudeComponent.getAttitudeValue(id1, type) } returns threshold

        assertTrue(condition.evaluate(context))
    }
}