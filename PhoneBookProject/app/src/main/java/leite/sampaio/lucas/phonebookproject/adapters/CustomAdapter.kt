package leite.sampaio.lucas.phonebookproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import leite.sampaio.lucas.phonebookproject.R
import leite.sampaio.lucas.phonebookproject.entities.Contact

class CustomAdapter (private val contactList: ArrayList<Contact>):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtNameItem)
        val txtPhone: TextView = itemView.findViewById(R.id.txtPhoneItem)
        val txtId: TextView = itemView.findViewById(R.id.txtIdItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = contactList[position]

        holder.txtName.text = currentItem.name
        holder.txtPhone.text = currentItem.phoneNumber
        holder.txtId.text = currentItem.id
    }
}

