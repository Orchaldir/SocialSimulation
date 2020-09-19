package social.data.action

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import social.data.utils.Context
import social.data.utils.Update
import social.data.utils.effect.Effect

internal class ActionTest {

    private val rule0 = mockk<UtilityRule>()
    private val rule1 = mockk<UtilityRule>()
    private val rule2 = mockk<UtilityRule>()

    private val effect0 = mockk<Effect>()
    private val effect1 = mockk<Effect>()

    private val context = mockk<Context>()

    private val update0 = mockk<Update>()
    private val update1 = mockk<Update>()

    private val action = Action("action0", listOf(rule0, rule1, rule2), listOf(effect0, effect1))

    @Test
    fun `Calculate the utility of an action`() {
        every { rule0.getUtilityModifier(context) } returns 5
        every { rule1.getUtilityModifier(context) } returns 15
        every { rule2.getUtilityModifier(context) } returns -8

        assertThat(action.calculateUtility(context)).isEqualTo(12)
    }

    @Test
    fun `Execute an action`() {
        every { effect0.applyTo(any()) } returns update0
        every { effect1.applyTo(update0) } returns update1

        assertThat(action.execute(context)).isEqualTo(update1)
    }

}