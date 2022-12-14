package com.example.chucksnotjoking.fragments.categorylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucksnotjoking.CategoryItemClickListener
import com.example.chucksnotjoking.data.Category
import com.example.chucksnotjoking.databinding.FragmentCategorylistBinding
import com.example.chucksnotjoking.fragments.joke.JokeFragment
import com.example.chucksnotjoking.replaceFragment

class CategoryListFragment : Fragment(), CategoryItemClickListener {

    companion object {
        fun newInstance() = CategoryListFragment()
    }

    private var _binding: FragmentCategorylistBinding? = null
    private val binding get() = _binding!!
    private val adapter = CategoryListAdapter(this)
    private val viewModel: CategoryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategorylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() = with(binding) {
        categoryListRecyclerView.layoutManager = LinearLayoutManager(activity)
        categoryListRecyclerView.adapter = adapter
        error.retry.setOnClickListener {
            viewModel.onRetryClick()
        }
        swipeRefresh.setOnRefreshListener {
            viewModel.onUpdate()
        }
    }

    private fun initObservers() = with(binding) {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            progressBar.root.isVisible = state is Loading
            swipeRefresh.isRefreshing = state is Updating
            error.root.isVisible = state is Error
            if (state is Success) {
                adapter.submitList(state.categoryList)
            }
        }
    }

    override fun onCategoryItemClick(item: Category) {
        replaceFragment(JokeFragment.newInstance(item.name))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}