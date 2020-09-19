package social.data.action

import social.data.utils.Context
import social.data.utils.Update
import social.data.utils.effect.Effect

class Action(val name: String, private val utilityRules: List<UtilityRule>, private val effects: List<Effect>) {

    fun calculateUtility(context: Context) = utilityRules
        .fold(0) { utility, rule -> utility + rule.getUtilityModifier(context) }

    fun execute(context: Context) = effects
        .fold(Update(context)) { update, effect -> effect.applyTo(update) }
}