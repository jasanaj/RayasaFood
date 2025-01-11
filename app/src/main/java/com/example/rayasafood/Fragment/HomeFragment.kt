package com.example.rayasafood.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.rayasafood.Adapter.PopularAdapter
import com.example.rayasafood.MenuBottomSheetFragment
import com.example.rayasafood.R
import com.example.rayasafood.databinding.FragmentHome2Binding
import com.example.rayasafood.DetailActivity  // Import the DetailActivity class

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHome2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHome2Binding.inflate(inflater, container, false)

        // Set up "View Menu" click listener
        binding.viewAllMenu.setOnClickListener {
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager, "Test")
        }

        // Sample data for popular items
        val foodNames = listOf("Burger", "Pizza", "Pasta", "Sandwich")
        val prices = listOf("Rp 15.000", "Rp 20.000", "Rp 10.000", "Rp 5.000")
        val images = listOf(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4)

        // Set up the adapter and pass the click listener
        val adapter = PopularAdapter(foodNames, prices, images) { position ->
            // Handle the button click and start the DetailActivity
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("FOOD_NAME", foodNames[position]) // Pass the data to DetailActivity
            startActivity(intent)
        }

        binding.PopularRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.PopularRecycleView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Prepare the list of images for the ImageSlider
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))

        // Set the image list to the ImageSlider
        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
    }

    companion object {
        // Add companion methods if needed
    }
}
