package com.readendless.learning.passdatabetweenfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult

class BookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_book, container, false)

        val bookClick = view.findViewById<Button>(R.id.buttonBookClick)

        bookClick.setOnClickListener {
            Toast.makeText(context, "Book Clicked!", Toast.LENGTH_LONG).show()
            // Use the Kotlin extension in the fragment-ktx artifact
            val result  = "Book Clicked!"
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        }
        return view
    }
}