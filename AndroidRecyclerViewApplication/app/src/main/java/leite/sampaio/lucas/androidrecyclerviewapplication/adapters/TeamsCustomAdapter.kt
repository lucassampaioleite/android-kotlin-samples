package leite.sampaio.lucas.androidrecyclerviewapplication.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import leite.sampaio.lucas.androidrecyclerviewapplication.R
import leite.sampaio.lucas.androidrecyclerviewapplication.entities.Team

class TeamsCustomAdapter(private val teams: List<Team>) :
    RecyclerView.Adapter<TeamsCustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textView_team)
        val textViewContinent: TextView = view.findViewById(R.id.textView_continent)
        val imageViewShield: ImageView = view.findViewById(R.id.imageView_teamShield)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.custom_recycler_view_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val team = teams[position]

        viewHolder.textViewName.text = team.name
        viewHolder.textViewContinent.text = team.continent
        viewHolder.imageViewShield.setImageResource(team.shield)

        viewHolder.itemView.setOnClickListener {
            openSecondActivity(viewHolder.itemView, team.name, team.continent, team.shield)
        }
    }

    override fun getItemCount() = teams.size

    private fun openSecondActivity(view: View, name: String, continent: String, shield: Int) {
        val intent = Intent(view.context, TeamActivity::class.java)
        intent.putExtra("TAG_NAME", name)
        intent.putExtra("TAG_CONTINENT", continent)
        intent.putExtra("TAG_SHIELD", shield)

        view.context.startActivity(intent)
    }
}
