package com.paulpatena.mytodolist

import android.app.Application
import io.realm.Realm

/**
 * Created by Paul on 12/23/2017.
 */

class ToDoList : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}