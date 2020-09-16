package social.data.utility.condition

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import social.data.utility.Context

internal class NotConditionTest {

    private val context = Context(emptyMap())

    @Test
    fun `Negate the inner condition`() {
        assertFalse(NotCondition(FixedValueCondition(true)).evaluate(context))
        assertTrue(NotCondition(FixedValueCondition(false)).evaluate(context))
    }

}