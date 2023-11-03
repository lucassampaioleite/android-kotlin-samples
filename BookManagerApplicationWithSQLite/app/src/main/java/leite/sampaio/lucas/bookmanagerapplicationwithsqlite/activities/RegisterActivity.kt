package leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.DatabaseController
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.R

class RegisterActivity : ComponentActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var authorEditText: EditText
    private lateinit var publisherEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initializeViews()

        registerButton.setOnClickListener {
            val titleString = titleEditText.text.toString()
            val authorString = authorEditText.text.toString()
            val publisherString = publisherEditText.text.toString()

            val dbController = DatabaseController(applicationContext)
            val result = dbController.insertData(titleString, authorString, publisherString)
            showToast(result)

            clearViews()

            navigateToListDataActivity()
        }
    }

    private fun initializeViews() {
        titleEditText = findViewById(R.id.etTitle)
        authorEditText = findViewById(R.id.etAuthor)
        publisherEditText = findViewById(R.id.etPublisher)
        registerButton = findViewById(R.id.buttonRegister)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun clearViews() {
        titleEditText.text.clear()
        authorEditText.text.clear()
        publisherEditText.text.clear()
    }

    private fun navigateToListDataActivity() {
        val intent = Intent(this, ListDataActivity::class.java)
        startActivity(intent)
    }
}

