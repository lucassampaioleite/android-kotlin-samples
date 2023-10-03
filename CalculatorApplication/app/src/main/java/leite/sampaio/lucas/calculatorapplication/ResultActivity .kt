package leite.sampaio.lucas.calculatorapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val message = intent.getDoubleExtra("TAG_INTENT", 0.0)

        val textViewResult = findViewById<TextView>(R.id.textViewResultAct2)
        textViewResult.text = message.toString()
    }
}