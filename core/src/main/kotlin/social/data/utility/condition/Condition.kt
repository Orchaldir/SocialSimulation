package social.data.utility.condition

import social.data.utility.Context

interface Condition {
    fun evaluate(context: Context): Boolean
}