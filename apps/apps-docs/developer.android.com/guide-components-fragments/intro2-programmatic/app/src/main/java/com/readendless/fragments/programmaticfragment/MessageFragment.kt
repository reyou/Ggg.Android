package com.readendless.fragments.programmaticfragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_message.*


class MessageFragment : Fragment() {
    lateinit var messageReadListener: OnMessageReadListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity = context as Activity
        try {
            messageReadListener = activity as OnMessageReadListener;
        } catch (e: ClassCastException) {
            throw java.lang.ClassCastException("$activity must override onMessageRead...")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        val bn = view.findViewById<Button>(R.id.bn)
        bn.setOnClickListener() {
            val text = text_message.text.toString();
            messageReadListener.onMessageRead(text)
        }
        return view;
    }


}