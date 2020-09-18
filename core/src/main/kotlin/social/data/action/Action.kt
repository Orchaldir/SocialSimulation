package social.data.action

import social.data.utility.Context
import social.data.utility.effect.Effect

class Action(val name: String, val utilityRules: List<UtilityRule>, val effects: List<Effect>) {

    fun calculateUtility(context: Context): Int {
        var utility = 0

        utilityRules.forEach { r -> utility += r.getUtilityModifier(context) }

        return utility
    }
}