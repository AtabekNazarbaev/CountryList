package kr.texnopos.testtask.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kr.texnopos.testtask.R
import kr.texnopos.testtask.core.ResourceState
import kr.texnopos.testtask.data.model.GenericResponse
import kr.texnopos.testtask.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val adapter = MainAdapter()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCountryList()
        binding.apply {
            recyclerView.adapter = adapter
            setUpObserverGetCategory()
            adapter.onItemClick = {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.update -> {
                        viewModel.getCountryList()
                        return@setOnMenuItemClickListener true
                    }
                }
                false
            }
        }
    }

    private fun setUpObserverGetCategory() {
        viewModel.countries.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    binding.progressBar.isVisible = true
                }
                ResourceState.SUCCESS -> {
                    setData(it.data!!)
                    binding.progressBar.isVisible = false
                }
                ResourceState.ERROR -> {
                    binding.progressBar.isVisible = false
                    it.message?.let { it1 -> Toast.makeText(requireContext(), it1, Toast.LENGTH_SHORT).show() }
                }
            }
        }
    }

    private fun setData(data: List<GenericResponse>) {
        adapter.model = data
    }
}
