package com.example.rayasafood.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rayasafood.Contact
import com.example.rayasafood.ContactAdapter
import com.example.rayasafood.DatabaseHelper
import com.example.rayasafood.R

class HistoryFragment : Fragment() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_history2, container, false)

        // Initialize database helper
        dbHelper = DatabaseHelper(requireContext())

        // Get references to UI elements
        val nameInput = rootView.findViewById<EditText>(R.id.nameInput)
        val phoneInput = rootView.findViewById<EditText>(R.id.phoneInput)
        val addButton = rootView.findViewById<Button>(R.id.addButton)
        val contactList = rootView.findViewById<ListView>(R.id.contactList)

        // Set up adapter for ListView
        contactAdapter = ContactAdapter(requireContext(), mutableListOf())
        contactList.adapter = contactAdapter

        // Load contacts
        loadContacts()

        // Handle add button click
        addButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val result = dbHelper.addContact(name, phone)
                if (result != -1L) {
                    Toast.makeText(requireContext(), "Contact Added", Toast.LENGTH_SHORT).show()
                    nameInput.text.clear()
                    phoneInput.text.clear()
                    loadContacts()
                }
            } else {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    private fun loadContacts() {
        val contacts = dbHelper.getAllContacts()
        contactAdapter.updateContacts(contacts)
    }
}
