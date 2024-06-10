package leite.sampaio.lucas.androidrecyclerviewapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import leite.sampaio.lucas.androidrecyclerviewapplication.adapters.TeamsCustomAdapter
import leite.sampaio.lucas.androidrecyclerviewapplication.databinding.ActivityMainBinding
import leite.sampaio.lucas.androidrecyclerviewapplication.entities.Team

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayTeamNames = resources.getStringArray(R.array.array_teams_names)
        val arrayTeamContinents = resources.getStringArray(R.array.array_teams_continents)

        val teamsList = listOf(
            Team(arrayTeamNames[0], arrayTeamContinents[0], R.drawable.brasil),
            Team(arrayTeamNames[1], arrayTeamContinents[0], R.drawable.argentina),
            Team(arrayTeamNames[2], arrayTeamContinents[1], R.drawable.alemanha),
            Team(arrayTeamNames[3], arrayTeamContinents[1], R.drawable.portugal),
            Team(arrayTeamNames[4], arrayTeamContinents[1], R.drawable.croacia),
            Team(arrayTeamNames[5], arrayTeamContinents[2], R.drawable.malawi),
            Team(arrayTeamNames[6], arrayTeamContinents[3], R.drawable.catar),
        )

        val recyclerView = binding.recyclerViewTeams
        val teamsCustomAdapter = TeamsCustomAdapter(teamsList)
        recyclerView.adapter = teamsCustomAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

//        recyclerView.layoutManager = LinearLayoutManager(this,
//            LinearLayoutManager.HORIZONTAL, false)

//        recyclerView.layoutManager = GridLayoutManager(this, 2)

//        recyclerView.layoutManager = StaggeredGridLayoutManager(2,
//            StaggeredGridLayoutManager.VERTICAL)

    }
}

