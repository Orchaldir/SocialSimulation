package social.data.action

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import social.data.utils.Context
import social.data.utils.Update
import social.data.utils.effect.Effect
import social.data.utils.utility.UtilityRuleSet

internal class ActionTest {

    private val effect0 = mockk<Effect>()
    private val effect1 = mockk<Effect>()

    private val actionRuleSet = mockk<UtilityRuleSet>()
    private val contextRuleSet = mockk<UtilityRuleSet>()

    private val context = Context(emptyMap(), contextRuleSet)

    private val update0 = mockk<Update>()
    private val update1 = mockk<Update>()

    private val action = Action("action0", actionRuleSet, listOf(effect0, effect1))

    @Test
    fun `Calculate the utility of an action`() {
        every { actionRuleSet.calculateUtility(context) } returns 16
        every { contextRuleSet.calculateUtility(context) } returns -4

        assertThat(action.calculateUtility(context)).isEqualTo(12)
    }

    @Test
    fun `Execute an action`() {
        every { effect0.applyTo(any()) } returns update0
        every { effect1.applyTo(update0) } returns update1

        assertThat(action.execute(context)).isEqualTo(update1)
    }

}