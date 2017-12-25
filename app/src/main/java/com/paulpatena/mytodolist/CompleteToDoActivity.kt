package com.paulpatena.mytodolist

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import io.realm.Realm

class CompleteToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_to_do)

        title = "Complete ToDo"
        val toDo_id = intent.getStringExtra("toDoItem")
        //        println("completeAct id = $toDo_id")
        val realm = Realm.getDefaultInstance()
        val toDoItem = realm.where(ToDoItem::class.java).equalTo("id", toDo_id).findFirst()
//        println(toDoItem?.toDo_txt)

        val toDoText = findViewById<TextView>(R.id.toDoTextView)
        toDoText.text = toDoItem?.toDo_txt

        if (toDoItem?.important == true)
        {
            toDoText.typeface = Typeface.DEFAULT_BOLD
        }

        val completeButton = findViewById<Button>(R.id.completeButton)
        completeButton.setOnClickListener {
            realm.beginTransaction()
            toDoItem?.deleteFromRealm()
            realm.commitTransaction()
            finish()
        }
    }
}
