package leite.sampaio.lucas.bookmanagerapplicationwithsqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities.ListDataActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities.RegisterActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCreate: Button = findViewById(R.id.buttonAdd)
        val buttonUpdate: Button = findViewById(R.id.buttonUpd)
        val buttonList: Button = findViewById(R.id.buttonList)

        buttonCreate.setOnClickListener {
            startNewActivity(RegisterActivity::class.java)
        }

        buttonUpdate.setOnClickListener {
            startNewActivity(ListDataActivity::class.java)
        }

        buttonList.setOnClickListener {
            startNewActivity(ListDataActivity::class.java)
        }
    }

    private fun startNewActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
