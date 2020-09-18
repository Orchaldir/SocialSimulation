package social.data.utility.condition

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import social.data.utility.Context

internal class NotConditionTest {

    private val context = Context()

    @Test
    fun `Negate the inner condition`() {
        assertFalse(not(FixedValueCondition(true)).evaluate(context))
        assertTrue(not(FixedValueCondition(false)).evaluate(context))
    }

}