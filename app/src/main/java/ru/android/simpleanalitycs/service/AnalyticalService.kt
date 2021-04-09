package ru.android.simpleanalitycs.service

import org.json.JSONObject

object AnalyticalService {

    private lateinit var trackers: Array<out AnalyticalSubservice>

    fun trackEvent(
        turninOn: Boolean,
        eventName: String,
        eventProps: JSONObject? = null,
        nameProps: String? = null,
        properties: String? = null
    ) {
        trackers.forEach { it.trackEvent(turninOn, eventName, eventProps, nameProps, properties) }
    }

    fun init(vararg trackers: AnalyticalSubservice) {
        AnalyticalService.trackers = trackers
    }
}