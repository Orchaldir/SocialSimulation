import social.data.character.Character
import social.data.character.attitude.AttitudeComponent

fun main() {
    val character = Character(99, AttitudeComponent(emptyMap()))
    println("Hello $character")
}