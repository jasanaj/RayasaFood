package com.example.rayasafood.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rayasafood.Adapter.CartAdapter
import com.example.rayasafood.R
import com.example.rayasafood.databinding.FragmentCart2Binding


class CartFragment : Fragment() {
    private lateinit var binding : FragmentCart2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCart2Binding.inflate(inflater,container,false)

        val cartFoodName = listOf( "Burger","sandwich","momo","item","sandwich","momo")
        val cartItemPrice = listOf("Rp15.000", "Rp10.000", "Rp5.000", "Rp50.000", "Rp10.000", "Rp5.000")
        val cartImage = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu2,
            R.drawable.menu3
        )
        val adapter = CartAdapter(
            ArrayList(cartFoodName),
            ArrayList(cartItemPrice),
            ArrayList(cartImage))
        binding.cartRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecycleView.adapter =adapter
        return binding.root
    }

    companion object {

    }
}