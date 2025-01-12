package com.example.rayasafood.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rayasafood.KalkulatorActivity
import com.example.rayasafood.KonversiBeratActivity
import com.example.rayasafood.KonversiMataUangActivity
import com.example.rayasafood.KonversiSuhuActivity
import com.example.rayasafood.R

class ProfileFragment : Fragment() {

    private lateinit var editProfileName: EditText
    private lateinit var editProfileEmail: EditText
    private lateinit var editProfileAddress: EditText
    private lateinit var editProfilePhone: EditText
    private lateinit var saveButton: Button

    // Database atau SharedPreferences
    // Jika menggunakan SQLite atau Room, sesuaikan logika di sini.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile2, container, false)

        // Inisialisasi view
        editProfileName = rootView.findViewById(R.id.editProfileName)
        editProfileEmail = rootView.findViewById(R.id.editProfileEmail)
        editProfileAddress = rootView.findViewById(R.id.editProfileAddress)
        editProfilePhone = rootView.findViewById(R.id.editProfilePhone)
        saveButton = rootView.findViewById(R.id.buttonSave)

        // Mengakses SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE)

        // Menampilkan data yang sudah disimpan (opsional)
        val savedName = sharedPref.getString("profileName", "")
        val savedEmail = sharedPref.getString("profileEmail", "")
        val savedAddress = sharedPref.getString("profileAddress", "")
        val savedPhone = sharedPref.getString("profilePhone", "")

        editProfileName.setText(savedName)
        editProfileEmail.setText(savedEmail)
        editProfileAddress.setText(savedAddress)
        editProfilePhone.setText(savedPhone)

        // Tombol Save untuk menyimpan data
        saveButton.setOnClickListener {
            val name = editProfileName.text.toString()
            val email = editProfileEmail.text.toString()
            val address = editProfileAddress.text.toString()
            val phone = editProfilePhone.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty()) {
                // Simpan data menggunakan SharedPreferences
                val editor = sharedPref.edit()
                editor.putString("profileName", name)
                editor.putString("profileEmail", email)
                editor.putString("profileAddress", address)
                editor.putString("profilePhone", phone)
                editor.apply()

                // Tampilkan pesan sukses
                Toast.makeText(requireContext(), "Profile saved successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }

        // Menghubungkan tombol dengan ID
        val buttonKalkulator: Button = rootView.findViewById(R.id.buttonKalkulator)
        val buttonKonversiMataUang: Button = rootView.findViewById(R.id.buttonKonversiMataUang)
        val buttonKonversiSuhu: Button = rootView.findViewById(R.id.buttonKonversiSuhu)
        val buttonKonversiBerat: Button = rootView.findViewById(R.id.buttonKonversiBerat)

        // Menangani klik tombol untuk berpindah ke halaman lain
        buttonKonversiMataUang.setOnClickListener {
            val intent = Intent(requireContext(), KonversiMataUangActivity::class.java)
            startActivity(intent)
        }

        buttonKonversiSuhu.setOnClickListener {
            val intent = Intent(requireContext(), KonversiSuhuActivity::class.java)
            startActivity(intent)
        }

        buttonKonversiBerat.setOnClickListener {
            val intent = Intent(requireContext(), KonversiBeratActivity::class.java)
            startActivity(intent)
        }

        buttonKalkulator.setOnClickListener {
            val intent = Intent(requireContext(), KalkulatorActivity::class.java)
            startActivity(intent)
        }

        return rootView
    }
}
