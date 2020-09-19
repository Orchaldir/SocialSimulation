package social.data.utils.condition

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import social.data.utils.Context

internal class NotConditionTest {

    private val context = Context()

    @Test
    fun `Negate the inner condition`() {
        assertFalse(not(FixedValueCondition(true)).evaluate(context))
        assertTrue(not(FixedValueCondition(false)).evaluate(context))
    }

}