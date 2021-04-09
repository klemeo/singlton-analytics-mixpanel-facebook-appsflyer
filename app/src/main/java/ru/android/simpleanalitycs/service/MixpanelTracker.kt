package ru.android.simpleanalitycs.service

import android.content.Context
import android.util.Log
import com.mixpanel.android.mpmetrics.MixpanelAPI
import org.json.JSONObject

class MixpanelTracker(context: Context) : AnalyticalSubservice() {

    companion object {
        const val token = "YOUR_TOKEN"
    }

    private val tracker: MixpanelAPI = MixpanelAPI.getInstance(context, token)

    init {
        tracker.people.identify(tracker.distinctId)
        tracker.people.set(tracker.superProperties)
    }

    override fun acceptEvent(
        turninOn: Boolean
    ): Boolean {
        return turninOn
    }

    override fun postEvent(
        eventName: String,
        eventProps: JSONObject?,
        nameProps: String?,
        properties: String?
    ) {
        tracker.track(eventName, eventProps)
        tracker.people.set(nameProps, properties)
        tracker.flush()
        Log.i("MixpanelTracker",eventName)
    }
}