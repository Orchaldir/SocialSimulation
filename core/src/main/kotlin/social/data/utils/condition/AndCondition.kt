package social.data.utils.condition

import social.data.utils.Context

data class AndCondition(val conditions: List<Condition>) : Condition {

    override fun evaluate(context: Context): Boolean {
        conditions.forEach {
            if (!it.evaluate(context)) {
                return false
            }
        }

        return true
    }

}

fun and(vararg conditions: Condition) = AndCondition(conditions.asList())