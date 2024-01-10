package com.example.biometricapp.presentation.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.biometricapp.R
import com.example.biometricapp.databinding.FragmentHomeBinding
import com.example.biometricapp.model.Product
import java.util.Locale
import java.util.Objects

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var adapter = ProductAdapter(this::addProductToCart)

    private fun addProductToCart(product: Product) {
        findNavController().navigate(R.id.cartFragment, bundleOf("products" to product))
    }

    var productList = arrayListOf<Product>(
        Product(1, "Dambbels", 3000,"https://www.ritfitsports.com/cdn/shop/products/ritfit-rubber-hex-dumbbell-set-10-60-lbs-weight-ritfit-613242.jpg?v=1669948018"),
        Product(1, "Dambbels", 3000,"https://www.ritfitsports.com/cdn/shop/products/ritfit-rubber-hex-dumbbell-set-10-60-lbs-weight-ritfit-613242.jpg?v=1669948018"),
        Product(1, "Dambbels", 3000,"https://www.ritfitsports.com/cdn/shop/products/ritfit-rubber-hex-dumbbell-set-10-60-lbs-weight-ritfit-613242.jpg?v=1669948018"),
        Product(1, "Dambbels", 3000,"https://www.ritfitsports.com/cdn/shop/products/ritfit-rubber-hex-dumbbell-set-10-60-lbs-weight-ritfit-613242.jpg?v=1669948018"),
        Product(1, "Dambbels", 3000,"https://www.ritfitsports.com/cdn/shop/products/ritfit-rubber-hex-dumbbell-set-10-60-lbs-weight-ritfit-613242.jpg?v=1669948018"),
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initializing variables of list view with their ids.


        // on below line we are adding on click
        // listener for mic image view.
        binding.imgMic.setOnClickListener {
            // on below line we are calling speech recognizer intent.
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            // on below line we are passing language model
            // and model free form in our intent
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )

            // on below line we are passing our
            // language as a default language.
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )

            // on below line we are specifying a prompt
            // message as speak to text on below line.
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

            // on below line we are specifying a try catch block.
            // in this block we are calling a start activity
            // for result method and passing our result code.
            try {
                startActivityForResult(intent, 10)
            } catch (e: Exception) {
                // on below line we are displaying error message in toast
                Toast
                    .makeText(
                        requireContext(), " " + e.message,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }

        adapter.addProductList(productList)
        binding.rvProduct.adapter = adapter
        binding.rvProduct.layoutManager = GridLayoutManager(requireContext(), 2)



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10) {
            // on below line we are checking if result code is ok
            if (resultCode == RESULT_OK && data != null) {

                // in that case we are extracting the
                // data from our array list
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                // on below line we are setting data
                // to our output text view.
                binding.tvMicOutput.text = Objects.requireNonNull(res)[0]
            }
        }
    }



}