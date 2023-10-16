package leite.sampaio.lucas.androidlistviewapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.ComponentActivity
import leite.sampaio.lucas.androidlistviewapplication.adapters.TeamsCustomAdapter
import leite.sampaio.lucas.androidlistviewapplication.entities.Team

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teamListView = findViewById<ListView>(R.id.listView)

        val arrayTeamNames = resources.getStringArray(R.array.array_team_names)
        val arrayTeamContinents = resources.getStringArray(R.array.array_team_continents)

        val teams = listOf(
            Team(arrayTeamNames[0], arrayTeamContinents[0], R.drawable.brasil),
            Team(arrayTeamNames[1], arrayTeamContinents[0], R.drawable.argentina),
            Team(arrayTeamNames[2], arrayTeamContinents[1], R.drawable.alemanha)
        )

        val teamsCustomAdapter = TeamsCustomAdapter(this, teams)
        teamListView.adapter = teamsCustomAdapter

        teamListView.setOnItemClickListener { _, _, position, _ ->
            val element = teamsCustomAdapter.getItem(position)
            val intent = Intent(this, TeamActivity::class.java)
            intent.putExtra("TAG_NAME", element.name)
            intent.putExtra("TAG_CONTINENT", element.continent)
            intent.putExtra("TAG_SHIELD", element.shield)
            startActivity(intent)
        }

    }
}

