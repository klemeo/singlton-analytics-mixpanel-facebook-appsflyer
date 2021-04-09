package ru.android.simpleanalitycs.base

import android.app.Application
import ru.android.simpleanalitycs.service.AFApplication
import ru.android.simpleanalitycs.service.AnalyticalService
import ru.android.simpleanalitycs.service.FacebookTracker
import ru.android.simpleanalitycs.service.MixpanelTracker

class CoroutinesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AnalyticalService.init(
            MixpanelTracker(this),
            AFApplication(this),
            FacebookTracker(this)
        )
    }

}