package leite.sampaio.lucas.phonebookproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import leite.sampaio.lucas.phonebookproject.adapters.CustomAdapter
import leite.sampaio.lucas.phonebookproject.databinding.ActivityMainBinding
import leite.sampaio.lucas.phonebookproject.entities.Contact

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : FirebaseDatabase
    private lateinit var firebaseRef : DatabaseReference
    private lateinit var customAdapter: CustomAdapter
    private val contactsList: ArrayList<Contact> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database
        firebaseRef = database.getReference("contacts")

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        customAdapter = CustomAdapter(
            contactsList, binding.edtName, binding.edtPhone, binding.btnSend, binding.btnUpdate)
        binding.recyclerView.adapter = customAdapter
        getData()

        binding.btnSend.setOnClickListener(){
            saveData()
        }
    }

    private fun saveData() {
        val name = binding.edtName.text.toString().trim()
        val phoneNumber = binding.edtPhone.text.toString().trim()

        if (name.isEmpty()) {
            binding.edtName.error = "Please enter a name"
        } else if (phoneNumber.isEmpty()) {
            binding.edtPhone.error = "Please enter a phone number"
        } else {
            val contactId = firebaseRef.push().key
            val contact = Contact(contactId, name, phoneNumber)
            if (contactId != null) {
                firebaseRef.child(contactId).setValue(contact)
                    .addOnCompleteListener {
                        customAdapter.notifyItemInserted(contactsList.size - 1)
                        showToast("Success")
                        binding.edtName.text.clear()
                        binding.edtPhone.text.clear()
                    }
                    .addOnFailureListener {
                        showToast("Error: ${it.message}")
                    }
            }else{
                showToast("Error")
            }
        }
    }

    private fun getData() {
        firebaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                contactsList.clear()
                if (snapshot.exists()) {
                    for (contactSnap in snapshot.children) {
                        val contact = contactSnap.getValue(Contact::class.java)
                        contactsList.add(contact!!)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                showToast("Error: $error")
            }
        })
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}

