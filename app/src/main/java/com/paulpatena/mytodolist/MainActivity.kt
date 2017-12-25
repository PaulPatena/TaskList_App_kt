package com.paulpatena.mytodolist

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // onCreate is only executed when the activity is created, when you go back to this activity
    // onCreate is NOT executed again
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        title = "ToDo List"

        fab.setOnClickListener {
            var addToDoIntent = Intent(this, AddToDoActivity::class.java)
            startActivity(addToDoIntent)
        }

        // Playground realm
        /*var myDawg = Dog()
        myDawg.name = "brownie"
        myDawg.age = 4

        var realm = Realm.getDefaultInstance()


        realm.beginTransaction()
        realm.copyToRealm(myDawg)
        realm.commitTransaction()

        val query = realm.where(Dog::class.java)
        val results = query.findAll()
        for (dog in results) {
            println(dog.name)
        }
        println("results size = ${results.size}")*/
    }

    // This function is always executed when you go back to the activity, even on the first execution,
    // this is run after onCreate
    override fun onResume() {
        super.onResume()

        var realm = Realm.getDefaultInstance()
        val query = realm.where(ToDoItem::class.java)
        val results = query.findAll()

        val listView = findViewById<ListView>(R.id.toDoItemsListView)
        val adapter = ToDoAdapter(this,android.R.layout.simple_list_item_1, results)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val selectedToDo = results[i]
            val finishIntent = Intent(this, CompleteToDoActivity::class.java)
            val toDo_id = selectedToDo?.getId()
            println(toDo_id)
            finishIntent.putExtra("toDoItem", toDo_id)
            startActivity(finishIntent)
        }
    }

        /*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}

class ToDoAdapter(context: Context?, resource: Int, objects: MutableList<ToDoItem>?) : ArrayAdapter<ToDoItem>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        return super.getView(position, convertView, parent)
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val toDoView = inflator.inflate(android.R.layout.simple_list_item_1, parent, false) as TextView

        val toDoItem = getItem(position)
        toDoView.text = toDoItem.toDo_txt
        if (toDoItem.important)
        {
            toDoView.typeface = Typeface.DEFAULT_BOLD
        }
        return toDoView
    }
}
