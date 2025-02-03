package com.potatomioo.expenx

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize

class ExpenxApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }
}