package leite.sampaio.lucas.androidlistviewapplication.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import leite.sampaio.lucas.androidlistviewapplication.R
import leite.sampaio.lucas.androidlistviewapplication.entities.Team

class TeamsCustomAdapter(private val context: Context, private val teams: List<Team>) : BaseAdapter() {
    override fun getCount(): Int {
        return teams.size
    }

    override fun getItem(position: Int): Team {
        return teams[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.custom_list_view_item, parent, false)
        val team = teams[position]
        val teamName = itemView.findViewById<TextView>(R.id.textView_team)
        val teamContinent = itemView.findViewById<TextView>(R.id.textView_continent)
        val teamShield = itemView.findViewById<ImageView>(R.id.imageView_teamShield)
        teamName.text = team.name
        teamContinent.text = team.continent
        teamShield.setImageResource(team.shield)
        return itemView
    }
}


