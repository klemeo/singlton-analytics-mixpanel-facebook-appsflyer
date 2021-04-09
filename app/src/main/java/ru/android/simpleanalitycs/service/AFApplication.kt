package ru.android.simpleanalitycs.service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerLib
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLibCore.LOG_TAG
import org.json.JSONObject
import ru.android.simpleanalitycs.base.CoroutinesApplication

class AFApplication(private var context: Context) : AnalyticalSubservice() {

    companion object {
        val devKey = "YOUR_TOKEN"
    }

    private val conversionDataListener = object : AppsFlyerConversionListener {
        @SuppressLint("LogNotTimber")
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            data?.let { cvData ->
                cvData.map {
                    Log.i(LOG_TAG, "conversion_attribute:  ${it.key} = ${it.value}")
                }
            }
        }

        @SuppressLint("LogNotTimber")
        override fun onConversionDataFail(error: String?) {
            Log.e(LOG_TAG, "error onAttributionFailure :  $error")
        }

        @SuppressLint("LogNotTimber")
        override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
            data?.map {
                Log.d(LOG_TAG, "onAppOpen_attribute: ${it.key} = ${it.value}")
            }
        }

        @SuppressLint("LogNotTimber")
        override fun onAttributionFailure(error: String?) {
            Log.e(LOG_TAG, "error onAttributionFailure :  $error")
        }
    }

    private var appsFlyerLib: AppsFlyerLib = AppsFlyerLib.getInstance().apply {
        init(devKey, conversionDataListener, context)
        setCollectOaid(false)
        getAppsFlyerUID(context)
        startTracking(context as CoroutinesApplication)
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
        appsFlyerLib.trackEvent(context, eventName, emptyMap())
        Log.i("AFApplication", eventName)
    }
}