package com.readendless.learning.listfragmentexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.ListFragment

class MyListFragment : ListFragment(), AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var arrayAdapter =
            ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.fruits_list,
                android.R.layout.simple_list_item_1
            )

        this.listAdapter = arrayAdapter
        this.listView.onItemClickListener = this

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val itemList = requireActivity().resources.getStringArray(R.array.fruits_list)
        val item = itemList[position]
        Toast.makeText(context, "You picked: $item", Toast.LENGTH_LONG).show()
    }


}