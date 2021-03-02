package com.champion.theo.tp_retrofit.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.champion.theo.tp_retrofit.adapters.ListArticleAdapter
import com.champion.theo.tp_retrofit.databinding.ArticleListFragmentBinding
import com.champion.theo.tp_retrofit.models.Article
import com.champion.theo.tp_retrofit.viewModels.ListArticleViewModel

class ListArticleFragment: Fragment(), ListArticleAdapter.Listener {
    /**
     * View binding
     */
    private var _binding: ArticleListFragmentBinding? = null
    val binding: ArticleListFragmentBinding = _binding!!

    /**
     * View model
     */
    private lateinit var viewModel: ListArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ListArticleViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArticleListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    /**
     * Init data for adapter and view model
     */
    private fun initData() {
        // Adapter
        val adapter = ListArticleAdapter(this)
        binding.listArticles.adapter = adapter

        // ViewModel
        viewModel.articles.observe(
            viewLifecycleOwner,
            Observer<List<Article>> { articles ->
                adapter.setArticles(articles)
            }
        )
    }
}