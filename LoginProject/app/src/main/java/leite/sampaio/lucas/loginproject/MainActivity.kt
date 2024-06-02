package leite.sampaio.lucas.loginproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import leite.sampaio.lucas.loginproject.databinding.ActivityMainBinding
import java.util.Date


class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_USER = "USER"
        const val EXTRA_DATE_HOUR = "DATE_HOUR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtEmailAddress.setText(getString(R.string.user_admin))
        binding.edtPassword.setText(getString(R.string.user_password))

        binding.btnLogin.setOnClickListener(){
            val email = binding.edtEmailAddress.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            if(checkCredentials(email, password)) {
                Toast.makeText(this, getString(R.string.toast_success), Toast.LENGTH_LONG).show()
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(EXTRA_USER, email)
                intent.putExtra(EXTRA_DATE_HOUR, Date().toString())
                startActivity(intent)
                finish()
            }else
                Toast.makeText(this, getString(R.string.toast_failure), Toast.LENGTH_LONG).show()
        }
    }

    private fun checkCredentials(email: String, password: String): Boolean{
        return email == getString(R.string.user_admin) && password == getString(R.string.user_password)
    }
}

