package social.data.utility

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import social.data.character.Character
import social.data.utility.CharacterRole.SPEAKER
import social.data.utility.CharacterRole.TARGET

internal class UpdateTest {

    private val character0 = mockk<Character>()
    private val character1 = mockk<Character>()
    private val character2 = mockk<Character>()

    @Test
    fun `Role is unknown by the update`() {
        val context = Context()
        val update = Update(context)

        assertNull(update.getCharacter(SPEAKER))
    }

    @Test
    fun `Return updated characters`() {
        val context = Context()
        val update = Update(context, mapOf(SPEAKER to character0, TARGET to character1))

        assertThat(update.getCharacter(SPEAKER)).isEqualTo(character0)
        assertThat(update.getCharacter(TARGET)).isEqualTo(character1)
    }

    @Test
    fun `Return original characters`() {
        val context = Context(mapOf(SPEAKER to character0, TARGET to character1))
        val update = Update(context)

        assertThat(update.getCharacter(SPEAKER)).isEqualTo(character0)
        assertThat(update.getCharacter(TARGET)).isEqualTo(character1)
    }

    @Test
    fun `Updated character overwrites the original character`() {
        val context = Context(mapOf(SPEAKER to character0, TARGET to character1))
        val update = Update(context, mapOf(SPEAKER to character2))

        assertThat(update.getCharacter(SPEAKER)).isEqualTo(character2)
        assertThat(update.getCharacter(TARGET)).isEqualTo(character1)
    }

}