package social.data.utility.condition

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import social.data.utility.Context

internal class AndConditionTest {

    private val context = Context()

    @Test
    fun `An empty AndCondition evaluates to true`() {
        assertTrue(AndCondition(emptyList()).evaluate(context))
        assertTrue(AndCondition().evaluate(context))
    }

    @Test
    fun `An AndCondition with only true conditions evaluates to true`() {
        val condition = FixedValueCondition(true)

        assertTrue(AndCondition(condition).evaluate(context))
        assertTrue(AndCondition(condition, condition).evaluate(context))
        assertTrue(AndCondition(condition, condition, condition).evaluate(context))
    }

    @Test
    fun `An AndCondition with one or more false conditions evaluates to false`() {
        val trueCondition = FixedValueCondition(true)
        val falseCondition = FixedValueCondition(false)

        assertFalse(AndCondition(falseCondition).evaluate(context))
        assertFalse(AndCondition(falseCondition, trueCondition).evaluate(context))
        assertFalse(AndCondition(trueCondition, falseCondition, trueCondition).evaluate(context))
        assertFalse(AndCondition(falseCondition, falseCondition, falseCondition).evaluate(context))
    }
}