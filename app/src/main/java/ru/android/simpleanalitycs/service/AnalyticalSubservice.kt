package ru.android.simpleanalitycs.service

import org.json.JSONObject

abstract class AnalyticalSubservice {

    fun trackEvent(
        turninOn: Boolean,
        eventName: String,
        eventProps: JSONObject? = null,
        nameProps: String? = null,
        properties: String? = null
    ) {
        if (acceptEvent(turninOn)) {
            postEvent(eventName, eventProps, nameProps, properties)
        }
    }

    protected abstract fun acceptEvent(turninOn: Boolean): Boolean

    protected abstract fun postEvent(eventName: String, eventProps: JSONObject?, nameProps: String?, properties: String?)
}