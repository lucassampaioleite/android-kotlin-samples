package leite.sampaio.lucas.bookmanagerapplicationwithsqlite.adapters

import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.CreateDB
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.R
import leite.sampaio.lucas.bookmanagerapplicationwithsqlite.activities.UpdateActivity

class CustomCursorAdapter(private var cursor: Cursor):
    RecyclerView.Adapter<CustomCursorAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.itemBookTitle)
        val idTextView: TextView = itemView.findViewById(R.id.itemBookId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        val title = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.TITLE))
        val id = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ID))

        holder.titleTextView.text = title
        holder.idTextView.text = id

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java)
            intent.putExtra("TAG_CODE", id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    fun swapCursor(newCursor: Cursor?) {
        if (newCursor != null) {
            cursor.close()
            cursor = newCursor
            notifyDataSetChanged()
        }
    }
}