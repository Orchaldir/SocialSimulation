package social.data.utility.effect

import social.data.utility.Update

interface Effect {

    fun applyTo(update: Update): Update

}