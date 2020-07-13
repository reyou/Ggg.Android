package com.readendless.learning.passdatabetweenfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener


class BookDescription : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_description, container, false)

        // Use the Kotlin extension in the fragment-ktx artifact
        this.setFragmentResultListener("requestKey") { key, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getString("bundleKey")
            // Do something with the result...
            val bookDescriptionText = view.findViewById<TextView>(R.id.bookdescriptiontext)
            bookDescriptionText.text = "Key: $key Result: $result"
        }

        return view
    }
}