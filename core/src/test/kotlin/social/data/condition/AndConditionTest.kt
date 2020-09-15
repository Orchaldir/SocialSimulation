package social.data.condition

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import social.data.action.Context

internal class AndConditionTest {

    private val context = Context(emptyMap())

    @Test
    fun `An empty AndCondition evaluates to true`() {
        assertTrue(AndCondition(emptyList()).evaluate(context))
    }

    @Test
    fun `An AndCondition with only true conditions evaluates to true`() {
        val condition = FixedValueCondition(true)

        assertTrue(AndCondition(listOf(condition)).evaluate(context))
        assertTrue(AndCondition(listOf(condition, condition)).evaluate(context))
        assertTrue(AndCondition(listOf(condition, condition, condition)).evaluate(context))
    }

    @Test
    fun `An AndCondition with one or more false conditions evaluates to false`() {
        val trueCondition = FixedValueCondition(true)
        val falseCondition = FixedValueCondition(false)

        assertFalse(AndCondition(listOf(falseCondition)).evaluate(context))
        assertFalse(AndCondition(listOf(falseCondition, trueCondition)).evaluate(context))
        assertFalse(AndCondition(listOf(trueCondition, falseCondition, trueCondition)).evaluate(context))
        assertFalse(AndCondition(listOf(falseCondition, falseCondition, falseCondition)).evaluate(context))
    }
}