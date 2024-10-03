package com.lessthanthree.galistapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lessthanthree.galistapp.core.R
import com.lessthanthree.galistapp.core.data.source.Resource
import com.lessthanthree.galistapp.core.ui.GameAdapter
import com.lessthanthree.galistapp.databinding.FragmentHomeBinding
import com.lessthanthree.galistapp.ui.detail.DetailGameActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val gameAdapter = GameAdapter()
            gameAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailGameActivity::class.java)
                intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.game.observe(viewLifecycleOwner) { game ->
                if (game != null) {
                    when (game) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            gameAdapter.submitList(game.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = game.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvGame) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }
}