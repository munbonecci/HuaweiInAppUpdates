package com.mun.bonecci.huaweiinappupdates

import android.content.Context
import android.os.Build
import com.google.android.gms.common.GoogleApiAvailability
import com.mun.bonecci.huaweiinappupdates.Constants.HUAWEI_DEVICE

fun Context.hasGooglePlayServices(): Boolean {
    val apiAvailability = GoogleApiAvailability.getInstance()
    val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)
    return resultCode == com.google.android.gms.common.ConnectionResult.SUCCESS
}

fun isHuaweiDevice(): Boolean {
    return Build.MANUFACTURER.equals(HUAWEI_DEVICE, ignoreCase = true)
}