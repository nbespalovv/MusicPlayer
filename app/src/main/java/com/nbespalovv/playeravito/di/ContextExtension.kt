package com.nbespalovv.playeravito.di

import android.content.Context
import com.nbespalovv.playeravito.App

@Suppress("UseIfInsteadOfWhen")
val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }