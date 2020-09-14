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

    private val attitude0 = Attitude(type0, 1)
    private val attitude1 = Attitude(type1, 2)
    private val attitude2 = Attitude(type0, 3)

    private val attitudes0 = mapOf(type0 to attitude0, type1 to attitude1)
    private val attitudes1 = mapOf(type0 to attitude2)

    private val component = AttitudeComponent(mapOf(id0 to attitudes0, id1 to attitudes1))

    @Nested
    inner class GetAttitudesTowards {

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

    @Nested
    inner class GetAttitudesOfType {

        @Test
        fun `Get all attitudes of a type`() {
            assertThat(component.getAttitudesOfType(type0)).isEqualTo(
                mapOf(
                    id0 to attitude0,
                    id1 to attitude2
                )
            )
        }

        @Test
        fun `Character has no attitude of that type to another character`() {
            assertThat(component.getAttitudesOfType(type1)).isEqualTo(mapOf(id0 to attitude1))
        }
    }

    @Nested
    inner class GetAttitudeValue {

        @Test
        fun `Get a specific attitude value`() {
            assertThat(component.getAttitudeValue(id0, type0)).isEqualTo(attitude0.value)
            assertThat(component.getAttitudeValue(id0, type1)).isEqualTo(attitude1.value)
            assertThat(component.getAttitudeValue(id1, type0)).isEqualTo(attitude2.value)
        }

        @Test
        fun `Character has no attitudes`() {
            assertThat(component.getAttitudeValue(id2, type0)).isEqualTo(type0.defaultValue)
            assertThat(component.getAttitudeValue(id2, type1)).isEqualTo(type1.defaultValue)
        }

        @Test
        fun `Character has no attitude of that type`() {
            assertThat(component.getAttitudeValue(id1, type1)).isEqualTo(type1.defaultValue)
        }
    }
}