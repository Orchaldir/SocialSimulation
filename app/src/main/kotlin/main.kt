import social.data.action.Action
import social.data.action.UtilityRule
import social.data.character.Character
import social.data.character.attitude.AttitudeComponent
import social.data.character.attitude.AttitudeType
import social.data.utils.CharacterRole.SPEAKER
import social.data.utils.CharacterRole.TARGET
import social.data.utils.Context
import social.data.utils.condition.AttitudeCondition
import social.data.utils.condition.and
import social.data.utils.condition.not
import social.data.utils.effect.ModifyAttitude

fun main() {
    val opinion = AttitudeType("opinion", 0)

    val id0 = 0
    val id1 = 1
    val id2 = 2

    val character0 = Character(id0, AttitudeComponent(Pair(id1, opinion) to 10, Pair(id2, opinion) to -10))
    val character1 = Character(id1, AttitudeComponent(Pair(id0, opinion) to 10))
    val character2 = Character(id2, AttitudeComponent(Pair(id0, opinion) to -10))

    val isFriend = AttitudeCondition(SPEAKER, TARGET, opinion, 5)
    val notEnemy = AttitudeCondition(SPEAKER, TARGET, opinion, -5)
    val isEnemy = not(notEnemy)
    val isStranger = and(not(isFriend), notEnemy)

    val forFriends = UtilityRule("For friends", isFriend, 5)
    val forEnemies = UtilityRule("For enemies", isEnemy, 5)
    val forStrangers = UtilityRule("For strangers", isStranger, 5)

    val praise = Action("praise", listOf(forFriends), listOf(ModifyAttitude(SPEAKER, TARGET, opinion, 2)))
    val smalltalk = Action("smalltalk", listOf(forStrangers), listOf(ModifyAttitude(SPEAKER, TARGET, opinion, 1)))
    val insult = Action("insult", listOf(forEnemies), listOf(ModifyAttitude(SPEAKER, TARGET, opinion, -2)))

    val actions = listOf(praise, smalltalk, insult)

    calculateUtility(character0, character1, actions)
    calculateUtility(character0, character2, actions)
    calculateUtility(character1, character0, actions)
    calculateUtility(character1, character2, actions)
    calculateUtility(character2, character0, actions)
    calculateUtility(character2, character1, actions)
}

private fun calculateUtility(
    speaker: Character,
    target: Character,
    actions: List<Action>,
) {
    println("${speaker.id} to ${target.id}:")
    val context = Context(mapOf(SPEAKER to speaker, TARGET to target))

    actions.forEach {
        val utility = it.calculateUtility(context)
        println("  Action '${it.name}' has utility $utility")
    }
}