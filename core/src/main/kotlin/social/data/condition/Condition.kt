package social.data.condition

import social.data.action.Context

interface Condition {
    fun evaluate(context: Context): Boolean
}