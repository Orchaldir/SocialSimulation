package social.data.character.attitude

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import social.data.character.CharacterId

internal class AttitudeComponentTest {

    private val type = AttitudeType("type", 0)

    private val id0: CharacterId = 0
    private val id1: CharacterId = 1
    private val id2: CharacterId = 2

    private val attitudes0 = listOf(Attitude(type, 0), Attitude(type, 2))
    private val attitudes1 = listOf(Attitude(type, 3))

    private val component = AttitudeComponent(mapOf(id0 to attitudes0, id1 to attitudes1))

    @Test
    fun `Get attitudes towards a character`() {
        assertThat(component.getAttitudesTowards(id0)).isEqualTo(attitudes0)
        assertThat(component.getAttitudesTowards(id1)).isEqualTo(attitudes1)
    }

    @Test
    fun `Character has no attitudes towards another character`() {
        assertThat(component.getAttitudesTowards(id2)).isEmpty()
    }
}