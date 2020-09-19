package social.data.utils.effect

import social.data.utils.Update

interface Effect {

    fun applyTo(update: Update): Update

}