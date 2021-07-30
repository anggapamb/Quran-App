package com.anggapambudi.quranapp.helper

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        @JvmField
        var context: Context? = null


        @JvmStatic
        fun getMyApplicationContext(): Context? {
            return context
        }

        fun getSession(): SessionHelper? {
            val context = getMyApplicationContext()
            val session = context?.let { SessionHelper(it) }
            session?.setName("prefs")
            return session
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}