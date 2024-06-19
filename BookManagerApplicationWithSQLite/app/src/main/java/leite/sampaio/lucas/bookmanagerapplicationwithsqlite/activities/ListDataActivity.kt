package leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities

import android.database.Cursor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.adapter.CustomAdapter
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.databinding.ActivityListDataBinding
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.sqlite.DatabaseController

class ListDataActivity : ComponentActivity() {
    private lateinit var binding: ActivityListDataBinding
    private lateinit var dbController: DatabaseController
    private lateinit var cursor: Cursor
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbController = DatabaseController(this)
        cursor = dbController.loadData()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        customAdapter = CustomAdapter(cursor)
        binding.recyclerView.adapter = customAdapter

    }
    override fun onResume() {
        super.onResume()
        cursor = dbController.loadData()
        customAdapter.updateData(cursor)
    }
}

