package com.paulpatena.mytodolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import io.realm.Realm

class AddToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)

        title = "Add New Item"

        val button = findViewById<Button>(R.id.addButton)

        button.setOnClickListener {
            val editText = findViewById<EditText>(R.id.addToDoEditText)
            val text = editText.text.toString()
            val important = findViewById<CheckBox>(R.id.importantCheckbox).isChecked

            var toDoItem = ToDoItem(toDo_txt = text, important = important)

            var realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealm(toDoItem)
            realm.commitTransaction()

            finish() // close activity and go back
        }


    }
}
