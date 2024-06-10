package leite.sampaio.lucas.androidrecyclerviewapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import leite.sampaio.lucas.androidrecyclerviewapplication.databinding.ActivityTeamBinding

class TeamActivity : ComponentActivity() {

    private lateinit var binding: ActivityTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val teamName = intent.getStringExtra("TAG_NAME")
        val teamContinent = intent.getStringExtra("TAG_CONTINENT")
        val teamShieldId = intent.getIntExtra("TAG_SHIELD", 0)

        binding.actTeamTxtName.text = teamName.toString()
        binding.actTeamTxtContinent.text = teamContinent.toString()
        binding.actTeamImgTeamShield.setImageResource(teamShieldId)
    }
}