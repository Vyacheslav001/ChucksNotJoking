package com.example.chucksnotjoking.fragments.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.chucksnotjoking.databinding.FragmentJokeBinding

class JokeFragment : Fragment() {

    companion object {
        private const val CATEGORY_KEY = "keyCategory"

        fun newInstance(category: String): JokeFragment {
            val fragment = JokeFragment()
            val arguments = Bundle()
            arguments.putString(CATEGORY_KEY, category)
            fragment.arguments = arguments
            return fragment
        }
    }

    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            getData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() = with(binding) {
        back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        error.retry.setOnClickListener {
            viewModel.onRetryClick()
        }
        swipeRefresh.setOnRefreshListener {
            viewModel.onUpdate()
        }
    }

    private fun initObservers() = with(binding) {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            error.root.isVisible = state is Error
            swipeRefresh.isRefreshing = state is Updating
            progressBar.root.isVisible = state is Loading
            if (state is Success) {
                categoryJoke.text = state.joke.categoryName
                joke.text = state.joke.text
            }
        }
    }

    private fun getData() {
        val category = arguments?.getString(CATEGORY_KEY)
            ?: throw IllegalArgumentException("Missing an argument: $CATEGORY_KEY")
        viewModel.setCategory(category)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}