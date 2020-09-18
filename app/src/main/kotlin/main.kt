import social.data.action.Action
import social.data.action.UtilityRule
import social.data.character.Character
import social.data.character.attitude.Attitude
import social.data.character.attitude.AttitudeComponent
import social.data.character.attitude.AttitudeType
import social.data.utility.CharacterRole.SPEAKER
import social.data.utility.CharacterRole.TARGET
import social.data.utility.Context
import social.data.utility.condition.AndCondition
import social.data.utility.condition.AttitudeCondition
import social.data.utility.condition.NotCondition
import social.data.utility.effect.ModifyAttitude

fun main() {
    val opinion = AttitudeType("opinion", 0)

    val id0 = 0
    val id1 = 1
    val id2 = 2

    val character0 = Character(
        id0, AttitudeComponent(
            mapOf(
                id1 to mapOf(opinion to Attitude(opinion, 10)),
                id2 to mapOf(opinion to Attitude(opinion, -10))
            )
        )
    )
    val character1 = Character(
        id1, AttitudeComponent(
            mapOf(id0 to mapOf(opinion to Attitude(opinion, 10)))
        )
    )
    val character2 = Character(id2, AttitudeComponent(mapOf(id0 to mapOf(opinion to Attitude(opinion, -10)))))

    val isFriend = AttitudeCondition(SPEAKER, TARGET, opinion, 5)
    val notEnemy = AttitudeCondition(SPEAKER, TARGET, opinion, -5)
    val isEnemy = NotCondition(notEnemy)
    val isStranger = AndCondition(NotCondition(isFriend), notEnemy)

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