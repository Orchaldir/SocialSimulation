package social.data.utility.condition

import social.data.utility.Context

data class AndCondition(val conditions: List<Condition>) : Condition {

    constructor(vararg conditions: Condition) : this(conditions.asList())

    override fun evaluate(context: Context): Boolean {
        conditions.forEach {
            if (!it.evaluate(context)) {
                return false
            }
        }

        return true
    }

}