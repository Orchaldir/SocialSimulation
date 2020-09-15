package social.data.condition

import social.data.action.Context

data class FixedValueCondition(val value: Boolean) : Condition {

    override fun evaluate(context: Context) = value

}