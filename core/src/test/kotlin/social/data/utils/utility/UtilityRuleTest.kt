package social.data.utils.utility

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import social.data.utils.Context
import social.data.utils.condition.Condition

internal class UtilityRuleTest {

    private val modifier = 99

    private val condition = mockk<Condition>()
    private val context = mockk<Context>()

    private val rule = UtilityRule("Test", condition, modifier)

    @Test
    fun `Get utility modifier if the condition is true`() {
        every { condition.evaluate(context) } returns true

        assertThat(rule.getUtilityModifier(context)).isEqualTo(modifier)
    }

    @Test
    fun `Get 0 if the condition is false`() {
        every { condition.evaluate(context) } returns false

        assertThat(rule.getUtilityModifier(context)).isEqualTo(0)
    }

}