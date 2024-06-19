package leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.databinding.ActivityUpdateDeleteBinding
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.sqlite.CreateDB
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.sqlite.DatabaseController

class UpdateDeleteActivity : ComponentActivity() {
    private lateinit var binding: ActivityUpdateDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val code = intent.getStringExtra("TAG_CODE").toString()

        val dbController = DatabaseController(this)
        val cursor = dbController.loadDataById(code.toInt())

        cursor.apply {
            binding.edtUpdActTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.TITLE)))
            binding.edtUpdActAuthor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.AUTHOR)))
            binding.edtUpdActPublisher.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.PUBLISHER)))
        }

        binding.btnUpdActUpdate.setOnClickListener {
            dbController.updateData(
                code.toInt(),
                binding.edtUpdActTitle.text.toString(),
                binding.edtUpdActAuthor.text.toString(),
                binding.edtUpdActPublisher.text.toString()
            )
            finish()
        }

        binding.btnUpdActDelete.setOnClickListener {
            dbController.deleteData(code.toInt())
            finish()
        }

    }
}

