package leite.sampaio.lucas.androidrecyclerviewapplication.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import leite.sampaio.lucas.androidrecyclerviewapplication.R
import leite.sampaio.lucas.androidrecyclerviewapplication.TeamActivity
import leite.sampaio.lucas.androidrecyclerviewapplication.entities.Team

class TeamsCustomAdapter(private val teams: List<Team>) :
    RecyclerView.Adapter<TeamsCustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTeam: TextView = view.findViewById(R.id.txt_team)
        val textViewContinent: TextView = view.findViewById(R.id.txt_continent)
        val imageViewShield: ImageView = view.findViewById(R.id.imv_teamShield)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.textViewTeam.text = team.name
        holder.textViewContinent.text = team.continent
        holder.imageViewShield.setImageResource(team.shield)

        holder.itemView.setOnClickListener {
            openSecondActivity(holder.itemView, team.name, team.continent, team.shield)
        }
    }

    private fun openSecondActivity(view: View, name: String, continent: String, shield: Int) {
        val intent = Intent(view.context, TeamActivity::class.java)
        intent.putExtra("TAG_NAME", name)
        intent.putExtra("TAG_CONTINENT", continent)
        intent.putExtra("TAG_SHIELD", shield)

        view.context.startActivity(intent)
    }
}





