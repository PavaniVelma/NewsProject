package com.apolisb42.newsproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.ImageLoader
import com.apolisb42.newsproject.R
import com.apolisb42.newsproject.model.NewsResponse
import com.apolisb42.newsproject.databinding.FragmentListOfNewsBinding
import com.apolisb42.newsproject.model.DaoNews
import com.apolisb42.newsproject.model.DbHelper
import com.apolisb42.newsproject.model.New
import com.apolisb42.newsproject.model.VolleyHandler
import com.apolisb42.newsproject.presenter.MVPNews
import com.apolisb42.newsproject.presenter.NewsPresenter


class ListOfNewsFragment : Fragment(),ItemClickListener {
    private lateinit var binding: FragmentListOfNewsBinding
    private lateinit var imageLoader: ImageLoader
    private lateinit var presenter: NewsPresenter
    private lateinit var detailsDescriptionFragment: DetailsDescriptionFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListOfNewsBinding.inflate(inflater,container,false )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = NewsPresenter(VolleyHandler(requireContext()), DaoNews(DbHelper(requireContext())),object : MVPNews.NewsView{
            override fun setResult(newsResponse: NewsResponse) {
                val adapter = NewsAdapter(newsResponse.news, imageLoader, this@ListOfNewsFragment)
                binding.rvNews.layoutManager = LinearLayoutManager(requireContext())

                binding.rvNews.adapter = adapter
            }

            override fun setError(error: String) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }

        })
        imageLoader = presenter.getImageLoader()

        presenter.getNews()
    }

    override fun send(news: New) {
        presenter.insertNews(news)
    }

    override fun delete(id: String) {
        presenter.deleteNews(id)
    }

    override fun isSelected(news: New) {
        val bundle = Bundle()
        bundle.putParcelable("data",news)
        detailsDescriptionFragment = DetailsDescriptionFragment()
        detailsDescriptionFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragContainer, detailsDescriptionFragment)?.
            addToBackStack(null)?.commit()
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu,menu)
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // call presenter

                query?.let{
                    presenter.searchNews(it)
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
            queryHint = "Search by category"
        }
    }




}


