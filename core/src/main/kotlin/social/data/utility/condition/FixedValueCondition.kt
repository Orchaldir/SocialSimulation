package social.data.utility.condition

import social.data.utility.Context

data class FixedValueCondition(val value: Boolean) : Condition {

    override fun evaluate(context: Context) = value

}