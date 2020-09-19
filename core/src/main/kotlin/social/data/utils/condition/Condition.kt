package social.data.utils.condition

import social.data.utils.Context

interface Condition {
    fun evaluate(context: Context): Boolean
}