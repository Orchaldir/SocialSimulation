package social.data.character.attitude

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import social.data.character.CharacterId

internal class AttitudeComponentTest {

    private val type0 = AttitudeType("type0", 11)
    private val type1 = AttitudeType("type1", 22)

    private val id0: CharacterId = 0
    private val id1: CharacterId = 1
    private val id2: CharacterId = 2

    private val component = AttitudeComponent(
        mapOf(
            Pair(id0, type0) to 1,
            Pair(id0, type1) to 2,
            Pair(id1, type0) to 3
        )
    )

    @Nested
    inner class GetAttitudesTowards {

        @Test
        fun `Get attitudes towards a character`() {
            assertThat(component.getAttitudesTowards(id0)).isEqualTo(mapOf(type0 to 1, type1 to 2))
            assertThat(component.getAttitudesTowards(id1)).isEqualTo(mapOf(type0 to 3))
        }

        @Test
        fun `Character has no attitudes towards another character`() {
            assertThat(component.getAttitudesTowards(id2)).isEmpty()
        }
    }

    @Nested
    inner class GetAttitudesOfType {

        @Test
        fun `Get all attitudes of a type`() {
            assertThat(component.getAttitudesOfType(type0)).isEqualTo(mapOf(id0 to 1, id1 to 3))
        }

        @Test
        fun `Character has no attitude of that type to another character`() {
            assertThat(component.getAttitudesOfType(type1)).isEqualTo(mapOf(id0 to 2))
        }
    }

    @Nested
    inner class GetAttitudeValue {

        @Test
        fun `Get a specific attitude value`() {
            assertThat(component.getAttitude(id0, type0)).isEqualTo(1)
            assertThat(component.getAttitude(id0, type1)).isEqualTo(2)
            assertThat(component.getAttitude(id1, type0)).isEqualTo(3)
        }

        @Test
        fun `Character has no attitudes`() {
            assertThat(component.getAttitude(id2, type0)).isEqualTo(type0.defaultValue)
            assertThat(component.getAttitude(id2, type1)).isEqualTo(type1.defaultValue)
        }

        @Test
        fun `Character has no attitude of that type`() {
            assertThat(component.getAttitude(id1, type1)).isEqualTo(type1.defaultValue)
        }
    }
}