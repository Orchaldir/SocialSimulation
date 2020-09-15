package social.data.condition

import social.data.action.Context

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