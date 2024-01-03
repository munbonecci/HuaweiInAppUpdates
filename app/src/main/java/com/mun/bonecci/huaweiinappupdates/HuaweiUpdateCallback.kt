package com.mun.bonecci.huaweiinappupdates

import android.content.Context
import android.content.Intent
import android.os.Build
import com.huawei.hms.jos.AppUpdateClient
import com.huawei.hms.jos.JosApps
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
import com.huawei.updatesdk.service.otaupdate.UpdateKey
import java.io.Serializable

class HuaweiUpdateCallback(private var context: Context) : CheckUpdateCallBack {
    val client: AppUpdateClient? = JosApps.getAppUpdateClient(context)
    override fun onUpdateInfo(intent: Intent?) {
        intent?.let {
            val forceUpdate = intent.getBooleanExtra(UpdateKey.MUST_UPDATE, false)
            val info = it.serializable<java.io.Serializable>(UpdateKey.INFO)
            if (info is ApkUpgradeInfo) client?.showUpdateDialog(context, info, forceUpdate)
        }
    }

    override fun onMarketInstallInfo(intent: Intent?) {
        // This method is reserved, no implementation needed
    }

    override fun onMarketStoreError(errorCode: Int) {
        // This method is reserved, no implementation needed
    }

    override fun onUpdateStoreError(errorCode: Int) {
        // This method is reserved, no implementation needed
    }

}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(
        key, T::class.java
    )

    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}