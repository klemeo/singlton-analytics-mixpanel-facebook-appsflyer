package ru.android.simpleanalitycs.service

import android.content.Context
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import org.json.JSONObject
import ru.android.simpleanalitycs.base.CoroutinesApplication

class FacebookTracker (context: Context): AnalyticalSubservice() {

    private val logger: AppEventsLogger

    init {
        FacebookSdk.sdkInitialize(context.applicationContext)
        AppEventsLogger.activateApp(context as CoroutinesApplication)
        FacebookSdk.getApplicationSignature(context)
        FacebookSdk.setApplicationId("facebook_app_id")
        FacebookSdk.fullyInitialize()
        logger = AppEventsLogger.newLogger(context)
    }

    override fun acceptEvent(
        turninOn: Boolean
    ): Boolean {
        return turninOn
    }

    override fun postEvent(eventName: String, eventProps: JSONObject?, nameProps: String?, properties: String?) {
        logger.logEvent(eventName)
        Log.i("FacebookTracker",eventName)
    }

}