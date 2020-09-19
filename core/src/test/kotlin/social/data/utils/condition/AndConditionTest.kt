package social.data.utils.condition

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import social.data.utils.Context

internal class AndConditionTest {

    private val context = Context()

    @Test
    fun `An empty AndCondition evaluates to true`() {
        assertTrue(AndCondition(emptyList()).evaluate(context))
        assertTrue(and().evaluate(context))
    }

    @Test
    fun `An AndCondition with only true conditions evaluates to true`() {
        val condition = FixedValueCondition(true)

        assertTrue(and(condition).evaluate(context))
        assertTrue(and(condition, condition).evaluate(context))
        assertTrue(and(condition, condition, condition).evaluate(context))
    }

    @Test
    fun `An AndCondition with one or more false conditions evaluates to false`() {
        val trueCondition = FixedValueCondition(true)
        val falseCondition = FixedValueCondition(false)

        assertFalse(and(falseCondition).evaluate(context))
        assertFalse(and(falseCondition, trueCondition).evaluate(context))
        assertFalse(and(trueCondition, falseCondition, trueCondition).evaluate(context))
        assertFalse(and(falseCondition, falseCondition, falseCondition).evaluate(context))
    }
}