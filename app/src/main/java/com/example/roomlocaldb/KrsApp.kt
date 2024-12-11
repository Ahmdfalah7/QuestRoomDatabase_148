package com.example.roomlocaldb

import android.app.Application
import com.example.roomlocaldb.dependenciesinjection.ContainerApp
import com.example.roomlocaldb.dependenciesinjection.InterfaceContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        //Membuat instance ContainerApp
        containerApp = ContainerApp(this)
    }
}