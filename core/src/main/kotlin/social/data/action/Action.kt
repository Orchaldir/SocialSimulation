package social.data.action

import social.data.utility.Context
import social.data.utility.effect.Effect

class Action(val name: String, private val utilityRules: List<UtilityRule>, private val effects: List<Effect>) {

    fun calculateUtility(context: Context) = utilityRules
        .fold(0) { utility, rule -> utility + rule.getUtilityModifier(context) }
}