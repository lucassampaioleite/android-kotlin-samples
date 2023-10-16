package leite.sampaio.lucas.androidlistviewapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class TeamActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val teamName = intent.getStringExtra("TAG_NAME")
        val teamContinent = intent.getStringExtra("TAG_CONTINENT")
        val teamShieldId = intent.getIntExtra("TAG_SHIELD", 0)

        val textViewName = findViewById<TextView>(R.id.acTeamTextViewName)
        val textViewContinent = findViewById<TextView>(R.id.acTeamTextViewContinent)
        val imageViewShield = findViewById<ImageView>(R.id.acTeamImageViewTeamShield)

        textViewName.text = teamName.toString()
        textViewContinent.text = teamContinent.toString()
        imageViewShield.setImageResource(teamShieldId)
    }
}

