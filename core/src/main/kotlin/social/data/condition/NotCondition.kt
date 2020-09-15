package social.data.condition

import social.data.action.Context

data class NotCondition(val condition: Condition) : Condition {

    override fun evaluate(context: Context) = !condition.evaluate(context)

}