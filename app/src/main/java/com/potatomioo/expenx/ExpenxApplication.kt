package com.potatomioo.expenx

import android.app.Application

class ExpenxApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        com.google.firebase.FirebaseApp.initializeApp(this)
    }
}