package social.data.utils.utility

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import social.data.utils.Context

internal class UtilityRuleSetTest {

    private val rule0 = mockk<UtilityRule>()
    private val rule1 = mockk<UtilityRule>()
    private val rule2 = mockk<UtilityRule>()

    private val context = mockk<Context>()

    private val ruleSet = UtilityRuleSet(rule0, rule1, rule2)

    @Test
    fun `Calculate the utility of an action`() {
        every { rule0.getUtilityModifier(context) } returns 5
        every { rule1.getUtilityModifier(context) } returns 15
        every { rule2.getUtilityModifier(context) } returns -8

        assertThat(ruleSet.calculateUtility(context)).isEqualTo(12)
    }
}