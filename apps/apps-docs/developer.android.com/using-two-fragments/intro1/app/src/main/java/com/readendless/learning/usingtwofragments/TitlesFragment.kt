package com.readendless.learning.usingtwofragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment


class TitlesFragment : ListFragment() {
    private var dualPane: Boolean = false
    private var curCheckPosition = 0

/*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_layout, container, false)
    }
*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Populate list with our static array of titles.
        this.listAdapter =
            ArrayAdapter<String>(
                requireActivity(),
                android.R.layout.simple_list_item_1,
                Shakespeare.TITLES
            )

        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        val detailsFrame: View? = activity?.findViewById(R.id.details)
        dualPane = detailsFrame?.visibility == View.VISIBLE

        curCheckPosition = savedInstanceState?.getInt("curChoice", 0) ?: 0

        if (dualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            this.listView.choiceMode = ListView.CHOICE_MODE_SINGLE
            // Make sure our UI is in the correct state.
            showDetails(curCheckPosition)
        }
    }

    private fun showDetails(index: Int) {
        curCheckPosition = index

        if (dualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            listView.setItemChecked(index, true)

            // Check what fragment is currently shown, replace if needed
            var details = fragmentManager?.findFragmentById(R.id.details) as? DetailsFragment
            if (details?.shownIndex != index) {
                // Make new fragment to show this selection.
                details = DetailsFragment.newInstance(index)

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                fragmentManager?.beginTransaction()?.apply {
                    replace(R.id.details, details)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    commit()
                }
            }
        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            val intent = Intent().apply {
                setClass(requireActivity(), DetailsActivity::class.java)
                putExtra("index", index)
            }
            startActivity(intent)
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("curChoice", curCheckPosition)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        showDetails(position)
    }


}