package leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.CreateDB
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.DatabaseController
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.R

class UpdateActivity : ComponentActivity() {
    private lateinit var code: String
    private lateinit var book: EditText
    private lateinit var author: EditText
    private lateinit var publisher: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        code = intent.getStringExtra("TAG_CODE").toString()
        initializeViews()

        val dbController = DatabaseController(applicationContext)
        val cursor = dbController.loadDataById(code.toInt())

        cursor?.apply {
            book.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.TITLE)))
            author.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.AUTHOR)))
            publisher.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.PUBLISHER)))
        }

        updateButton.setOnClickListener {
            dbController.updateData(
                code.toInt(),
                book.text.toString(),
                author.text.toString(),
                publisher.text.toString()
            )
            finish()
        }

        deleteButton.setOnClickListener {
            dbController.deleteData(code.toInt())
            finish()
        }
    }

    private fun initializeViews() {
        book = findViewById(R.id.etUpdateTitle)
        author = findViewById(R.id.etUpdateAuthor)
        publisher = findViewById(R.id.etUpdatePublisher)
        updateButton = findViewById(R.id.btUpdateUpdate)
        deleteButton = findViewById(R.id.btUpdateDelete)
    }
}