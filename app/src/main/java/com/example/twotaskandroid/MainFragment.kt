package com.example.twotaskandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.twotaskandroid.objects.Cat
import com.example.twotaskandroid.presentationLayer.AdapterCat
import com.example.twotaskandroid.viewmodel.MainViewModel

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCats)
        val buttonReconnect = view.findViewById<Button>(R.id.button_reconnect)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        val adapter = AdapterCat()

        adapter.setOnItemClickListener(
            object : AdapterCat.OnItemClickListener {
                override fun onItemClick(cat: Cat) {

                }
            }
        )

        adapter.setOnReachEndListener(
            object : AdapterCat.OnReachEndListener{
                override fun onReachEnd() {
                    viewModel.loadCats()
                }
            }
        )

        recyclerView.adapter = adapter

        viewModel.cats.observe(
            viewLifecycleOwner,
            Observer {
                adapter.setCats(it)
            }
        )

        viewModel.isError.observe(
            viewLifecycleOwner,
            Observer {
                if (it) {
                    buttonReconnect.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE

                }
                else {
                    buttonReconnect.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
        )

        viewModel.isLoading.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                progressBar.visibility = View.VISIBLE
                buttonReconnect.isEnabled = false
            } else {
                progressBar.visibility = View.GONE
                buttonReconnect.isEnabled = true
            }
        }

        buttonReconnect.setOnClickListener {
            viewModel.loadCats()
        }

        return view
    }
}