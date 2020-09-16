package social.data.utility.condition

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import social.data.utility.Context

internal class FixedValueConditionTest {

    private val context = Context(emptyMap())

    @Test
    fun `The correct value is returned`() {
        assertTrue(FixedValueCondition(true).evaluate(context))
        assertFalse(FixedValueCondition(false).evaluate(context))
    }

}