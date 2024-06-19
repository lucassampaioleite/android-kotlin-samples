package leite.sampaio.lucas.bookmanagerapplicationwithsqlite.adapter

import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.R
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities.UpdateDeleteActivity
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.sqlite.CreateDB

class CustomAdapter (private var cursor: Cursor):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtId: TextView = itemView.findViewById(R.id.itemTxtId)
        val txtTitle: TextView = itemView.findViewById(R.id.itemTxtTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        val title = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.TITLE))
        val id = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ID))

        holder.txtId.text = id
        holder.txtTitle.text = title

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateDeleteActivity::class.java)
            intent.putExtra("TAG_CODE", id)
            holder.itemView.context.startActivity(intent)
        }
    }

    fun updateData(newCursor: Cursor) {
        cursor.close()
        cursor = newCursor
        notifyDataSetChanged()
    }


}