package social.data.utility.condition

import social.data.utility.Context

data class NotCondition(val condition: Condition) : Condition {

    override fun evaluate(context: Context) = !condition.evaluate(context)

}