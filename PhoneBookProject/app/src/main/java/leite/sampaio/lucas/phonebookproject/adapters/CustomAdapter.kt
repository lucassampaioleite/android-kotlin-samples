package leite.sampaio.lucas.phonebookproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import leite.sampaio.lucas.phonebookproject.R
import leite.sampaio.lucas.phonebookproject.entities.Contact

class CustomAdapter (
    private val contactList: ArrayList<Contact>,
    private val edtName: EditText,
    private val edtPhone: EditText,
    private val btnSend: Button,
    private val btnUpdate: Button):
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

        holder.itemView.setOnClickListener {
            edtName.setText(holder.txtName.text)
            edtPhone.setText(holder.txtPhone.text)
            btnSend.isEnabled=false
            btnUpdate.isEnabled=true
            btnUpdate.setOnClickListener{
                currentItem.name = edtName.text.toString()
                currentItem.phoneNumber = edtPhone.text.toString()
                val firebaseRef = FirebaseDatabase.getInstance().getReference("contacts")
                firebaseRef.child(currentItem.id!!).setValue(currentItem)
                    .addOnCompleteListener{
                        notifyItemChanged(position)
                        btnSend.isEnabled=true
                        btnUpdate.isEnabled=false
                        edtName.text.clear()
                        edtPhone.text.clear()
                        showToast(holder.itemView.context, " data updated successfully")
                    }
                    .addOnFailureListener{
                        btnSend.isEnabled=true
                        btnUpdate.isEnabled=false
                        edtName.text.clear()
                        edtPhone.text.clear()
                        showToast(holder.itemView.context, "error ${it.message}")
                    }
            }
        }
        holder.itemView.setOnLongClickListener{
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Delete item permanently")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes"){_,_ ->
                    val firebaseRef = FirebaseDatabase.getInstance().getReference("contacts")
                    firebaseRef.child(currentItem.id.toString()).removeValue()
                        .addOnSuccessListener {
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, contactList.size)
                            showToast(holder.itemView.context,"Item removed successfully")
                        }
                        .addOnFailureListener {error ->
                            showToast(holder.itemView.context,"error ${error.message}")
                        }
                }
                .setNegativeButton("No"){_,_ ->
                    showToast(holder.itemView.context,"canceled")
                }
                .show()
            return@setOnLongClickListener true
        }
    }

    private fun showToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}

