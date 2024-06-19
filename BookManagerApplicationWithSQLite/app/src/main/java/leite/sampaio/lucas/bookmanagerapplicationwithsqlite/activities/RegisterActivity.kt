package leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.databinding.ActivityRegisterBinding
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.sqlite.DatabaseController

class RegisterActivity : ComponentActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val titleString = binding.edtTitle.text.toString()
            val authorString = binding.edtAuthor.text.toString()
            val publisherString = binding.edtPublisher.text.toString()

            val dbController = DatabaseController(this)
            val result = dbController.insertData(titleString, authorString, publisherString)
            showToast(result)

            clearViews()

        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun clearViews() {
        binding.edtTitle.text.clear()
        binding.edtAuthor.text.clear()
        binding.edtPublisher.text.clear()
    }
}