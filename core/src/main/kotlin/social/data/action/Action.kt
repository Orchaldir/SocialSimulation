package social.data.action

import social.data.utils.Context
import social.data.utils.Update
import social.data.utils.effect.Effect
import social.data.utils.utility.UtilityRuleSet

class Action(val name: String, private val utilityRuleSet: UtilityRuleSet, private val effects: List<Effect>) {

    fun calculateUtility(context: Context) =
        utilityRuleSet.calculateUtility(context) + context.utilityRuleSet.calculateUtility(context)

    fun execute(context: Context) = effects
        .fold(Update(context)) { update, effect -> effect.applyTo(update) }
}