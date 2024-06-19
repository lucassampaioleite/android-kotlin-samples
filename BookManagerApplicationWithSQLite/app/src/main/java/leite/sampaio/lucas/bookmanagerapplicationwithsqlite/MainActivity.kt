package leite.sampaio.lucas.bookmanagerapplicationwithsqlite

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities.ListDataActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities.RegisterActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener(){
            startNewActivity(RegisterActivity::class.java)
        }

        binding.btnListAll.setOnClickListener(){
            startNewActivity(ListDataActivity::class.java)
        }

        binding.btnUpdate.setOnClickListener(){
            startNewActivity(ListDataActivity::class.java)
        }
    }
    private fun startNewActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}

