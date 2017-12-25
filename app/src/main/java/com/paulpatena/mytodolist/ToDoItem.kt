package com.paulpatena.mytodolist

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Paul on 12/23/2017.
 */
open class Dog : RealmObject() {
    var name = ""
    var age = 0
}

open class ToDoItem(var toDo_txt: String = "", var important: Boolean = false) :RealmObject() {
//    var toDo_txt = ""
//    var important = false
    var finished = false

    @PrimaryKey
    private var id = UUID.randomUUID().toString()

    fun getId() : String {
        return this.id
    }

    override fun toString(): String {
        return toDo_txt
    }
}