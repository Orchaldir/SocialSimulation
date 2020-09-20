package social.data.utils.utility

import social.data.utils.Context

class UtilityRuleSet(private val utilityRules: List<UtilityRule>) {

    fun calculateUtility(context: Context) = utilityRules
        .fold(0) { utility, rule -> utility + rule.getUtilityModifier(context) }

}