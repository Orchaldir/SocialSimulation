package social.data.utils.condition

import social.data.utils.Context

data class FixedValueCondition(private val value: Boolean) : Condition {

    override fun evaluate(context: Context) = value

}