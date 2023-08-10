package com.apolisb42.newsproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.newsproject.R
import com.apolisb42.newsproject.databinding.FragmentSavedListBinding
import com.apolisb42.newsproject.model.DaoNews
import com.apolisb42.newsproject.model.DbHelper
import com.apolisb42.newsproject.model.New
import com.apolisb42.newsproject.model.VolleyHandler
import com.apolisb42.newsproject.presenter.SavedListPresenter


class SavedListFragment : Fragment() {

    private lateinit var binding: FragmentSavedListBinding
    private lateinit var presenter: SavedListPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSavedListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SavedListPresenter(VolleyHandler(requireContext()),DaoNews(DbHelper(requireContext())))
        val adapter = NewsAdapter(presenter.fetchNews(), presenter.getImageLoader(),object:ItemClickListener{
            override fun send(news: New) {
            }
            override fun delete(id: String) {
            }
            override fun isSelected(news: New) {
                val bundle = Bundle()
                bundle.putParcelable("data",news)
                val detailsFragment = DetailsDescriptionFragment()
                detailsFragment.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragContainer, detailsFragment)?.
                addToBackStack(null)?.commit()
            }

        })
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.adapter = adapter

    }


}