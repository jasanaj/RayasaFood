package com.example.rayasafood

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ContactAdapter(context: Context, private var contacts: MutableList<Contact>) :
    ArrayAdapter<Contact>(context, 0, contacts) {

    fun updateContacts(newContacts: List<Contact>) {
        contacts.clear()
        contacts.addAll(newContacts)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)

        val contact = getItem(position)
        val nameTextView: TextView = view.findViewById(android.R.id.text1)
        val phoneTextView: TextView = view.findViewById(android.R.id.text2)

        nameTextView.text = contact?.name
        phoneTextView.text = contact?.phone

        return view
    }
}
