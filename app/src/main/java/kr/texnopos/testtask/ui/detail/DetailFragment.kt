package kr.texnopos.testtask.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kr.texnopos.testtask.R
import kr.texnopos.testtask.core.ResourceState
import kr.texnopos.testtask.data.model.Detail
import kr.texnopos.testtask.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getInfo(args.id)
        setUpObservers()
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setUpObservers() {
        viewModel.details.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> binding.progressBar.isVisible = true
                ResourceState.SUCCESS -> {
                    setData(it.data!!)
                    binding.progressBar.isVisible = false
                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun setData(data: List<Detail>) {
        binding.apply {

            Glide.with(root.context)
                .load(data[0].flags.png)
                .into(ivFlag)
            tvCurrency.text =
                "Currency: ${data[0].currencies[0].symbol} (${data[0].currencies[0].name})"
            tvCountryName.text = data[0].name
            tvCapital.text = "Capital: ${data[0].capital}"
            tvRegion.text = "Region: ${data[0].region}"
            tvTimeZone.text = "Time Zone: ${data[0].timezones[0]}"
        }
    }
}