package com.example.characterapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.characterapp.characters.CharactersAdapter
import com.example.characterapp.databinding.FragmentCharactersBinding
import com.example.characterapp.di.characters.CharactersViewModel
import com.example.characterapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

@AndroidEntryPoint
class CharactersFragment: Fragment() {

    private lateinit var binding: FragmentCharactersBinding

    private lateinit var adapter: CharactersAdapter

    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        adapter = CharactersAdapter(listOf())
        adapter.onItemClickListener = object : CharactersAdapter.ItemClickListener{

            override fun onItemClick(id: Int, itemRoot: ConstraintLayout) {

            }
        }

        binding.apply {

           recyclerView.layoutManager = LinearLayoutManager(activity)

            val alphaAdapter = AlphaInAnimationAdapter(adapter)
            recyclerView.adapter = ScaleInAnimationAdapter(alphaAdapter).apply {
                setDuration(500)
                setFirstOnly(false)
            }

            recyclerView.adapter = alphaAdapter
        }


        viewModel.getCharactersresponse().observe(viewLifecycleOwner

        ) { resource ->

            when (resource.status) {

                Resource.Status.LOADING -> {
                    binding.simmerLayout.visibility = View.VISIBLE
                    binding.simmerLayout.startShimmer()
                }

                Resource.Status.SUCCESS -> {
                    binding.simmerLayout.visibility = View.GONE
                    binding.simmerLayout.stopShimmer()

                    if (resource.data != null) {
                        adapter.setItems(resource.data.characters)
                    }
                }
                Resource.Status.ERROR -> {
                    binding.simmerLayout.visibility = View.GONE
                    binding.simmerLayout.stopShimmer()

                    Toast.makeText(activity, resource.message, Toast.LENGTH_LONG).show()
                }

            }
           }

        }
}