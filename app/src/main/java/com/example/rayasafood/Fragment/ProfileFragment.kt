package com.example.rayasafood.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.rayasafood.KalkulatorActivity
import com.example.rayasafood.KonversiBeratActivity
import com.example.rayasafood.KonversiMataUangActivity
import com.example.rayasafood.KonversiSuhuActivity
import com.example.rayasafood.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment
        val rootView = inflater.inflate(R.layout.fragment_profile2, container, false)

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
