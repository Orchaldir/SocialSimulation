package social.data.utility.condition

import social.data.utility.Context

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