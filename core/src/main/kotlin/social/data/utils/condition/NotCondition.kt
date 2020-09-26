package social.data.utils.condition

import social.data.utils.Context

data class NotCondition(private val condition: Condition) : Condition {

    override fun evaluate(context: Context) = !condition.evaluate(context)

}

fun not(condition: Condition) = NotCondition(condition)