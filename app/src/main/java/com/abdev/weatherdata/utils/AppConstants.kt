package com.abdev.weatherdata.utils

import androidx.annotation.IntDef

@Retention(AnnotationRetention.BINARY)
@IntDef(AppConstants.METRIC_MIN_TEMP, AppConstants.METRIC_MXN_TEMP, AppConstants.METRIC_RAINFALL)
annotation class AppConstants {
    companion object {
        const val METRIC_MIN_TEMP = 0
        const val METRIC_MXN_TEMP = 1
        const val METRIC_RAINFALL = 2
    }
}
