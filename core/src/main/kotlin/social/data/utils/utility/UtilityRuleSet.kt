package social.data.utils.utility

import social.data.utils.Context

class UtilityRuleSet(private val utilityRules: List<UtilityRule> = emptyList()) {

    constructor(vararg rules: UtilityRule) : this(rules.toList())

    fun calculateUtility(context: Context) = utilityRules
        .fold(0) { utility, rule -> utility + rule.getUtilityModifier(context) }

}