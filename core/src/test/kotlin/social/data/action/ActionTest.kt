package social.data.action

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import social.data.utility.Context

internal class ActionTest {

    private val rule0 = mockk<UtilityRule>()
    private val rule1 = mockk<UtilityRule>()
    private val rule2 = mockk<UtilityRule>()

    private val context = mockk<Context>()

    private val action = Action("action0", listOf(rule0, rule1, rule2), emptyList())

    @Test
    fun `Calculate the utility of an action`() {
        every { rule0.getUtilityModifier(context) } returns 5
        every { rule1.getUtilityModifier(context) } returns 15
        every { rule2.getUtilityModifier(context) } returns -8

        assertThat(action.calculateUtility(context)).isEqualTo(12)
    }

}