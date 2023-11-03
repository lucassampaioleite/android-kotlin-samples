package leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities

import android.database.Cursor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.DatabaseController
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.R
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.adapters.CustomCursorAdapter

class ListDataActivity : ComponentActivity() {

    private lateinit var dbController: DatabaseController
    private lateinit var cursor: Cursor
    private lateinit var adapter: CustomCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_data)

        dbController = DatabaseController(applicationContext)
        cursor = dbController.loadData()!!

        val recyclerView = findViewById<RecyclerView>(R.id.rvData)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomCursorAdapter(cursor)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        cursor = dbController.loadData()!!
        adapter.swapCursor(cursor)
    }
}

