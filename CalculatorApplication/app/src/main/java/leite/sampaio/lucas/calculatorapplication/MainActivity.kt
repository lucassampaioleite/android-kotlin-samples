package leite.sampaio.lucas.calculatorapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import leite.sampaio.lucas.calculatorapplication.ui.theme.CalculatorApplicationTheme

private lateinit var btnSum: Button
private lateinit var btnSub: Button
private lateinit var btnMul: Button
private lateinit var btnDiv: Button
private lateinit var editTextNum1: EditText
private lateinit var editTextNum2: EditText
private lateinit var textViewResult: TextView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSum = findViewById<Button>(R.id.buttonSUM)
        btnSub = findViewById<Button>(R.id.buttonSUB)
        btnMul = findViewById<Button>(R.id.buttonMUL)
        btnDiv = findViewById<Button>(R.id.buttonDIV)
        editTextNum1 = findViewById<EditText>(R.id.editTextNumber1)
        editTextNum2 = findViewById<EditText>(R.id.editTextNumber2)
        textViewResult = findViewById<TextView>(R.id.textViewResult)

        createListeners()
    }

    private fun createListeners(){
        btnSum.setOnClickListener{operation(1)}
        btnSub.setOnClickListener{operation(2)}
        btnMul.setOnClickListener{operation(3)}
        btnDiv.setOnClickListener{operation(4)}
    }

    private fun operation(option: Int){
        val value1 = editTextNum1.text.toString().toDoubleOrNull() ?: 0.0
        val value2 = editTextNum2.text.toString().toDoubleOrNull() ?: 0.0
        var result = 0.0
        when(option){
            1 -> result = value1 + value2
            2 -> result = value1 - value2
            3 -> result = value1 * value2
            4 -> result = value1 / value2
        }
        textViewResult.text = "$result"

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("TAG_INTENT", result)

        startActivity(intent)
    }


}
