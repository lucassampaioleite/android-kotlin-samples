package leite.sampaio.lucas.androidrecyclerviewapplication

import leite.sampaio.lucas.androidrecyclerviewapplication.adapters.TeamsCustomAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import leite.sampaio.lucas.androidrecyclerviewapplication.entities.Team

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTeams)

        val arrayTeamNames = resources.getStringArray(R.array.array_team_names)
        val arrayTeamContinents = resources.getStringArray(R.array.array_team_continents)

        val teams = listOf(
            Team(arrayTeamNames[0], arrayTeamContinents[0], R.drawable.brasil),
            Team(arrayTeamNames[1], arrayTeamContinents[0], R.drawable.argentina),
            Team(arrayTeamNames[2], arrayTeamContinents[1], R.drawable.alemanha),
        )

        val teamsCustomAdapter = TeamsCustomAdapter(teams)
        recyclerView.adapter = teamsCustomAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

