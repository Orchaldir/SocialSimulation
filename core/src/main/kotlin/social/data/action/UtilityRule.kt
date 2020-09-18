package social.data.action

import social.data.utility.Context
import social.data.utility.condition.Condition

class UtilityRule(val description: String, private val condition: Condition, private val utilityModifier: Int) {

    fun getUtilityModifier(context: Context) = if (condition.evaluate(context)) {
        utilityModifier
    } else {
        0
    }

}