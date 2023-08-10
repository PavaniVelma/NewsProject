package com.apolisb42.newsproject.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.android.volley.toolbox.ImageLoader
import com.apolisb42.newsproject.R
import com.apolisb42.newsproject.databinding.FragmentDetailsDiscriptionBinding
import com.apolisb42.newsproject.model.New
import com.apolisb42.newsproject.model.VolleyHandler
import com.apolisb42.newsproject.presenter.DetailsPresenter

class DetailsDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDetailsDiscriptionBinding
    private lateinit var detailsPresenter: DetailsPresenter
    private var newsList : New? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsList = arguments?.getParcelable("data")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding = FragmentDetailsDiscriptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsPresenter = DetailsPresenter(VolleyHandler(requireContext()))
        val imageLoader = detailsPresenter.getImageLoader()
        newsList?.let {
            binding.tvTitle.text = it.title
            imageLoader.get(it.image, ImageLoader.getImageListener(
                binding.NetworkImageView,
                0,
                0
            ))
            binding.NetworkImageView.setImageUrl(it.image, imageLoader)
            binding.tvAuthor.text = it.author
            binding.tvCategory.text = it.category.first()
            binding.tvDescription.text = it.description.trim()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu2,menu)
        menu.findItem(R.id.share).actionView
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Im sharing this news")
                shareIntent.putExtra(Intent.EXTRA_TEXT, newsList?.url)
                shareIntent.type = "text/plain"
                startActivity(shareIntent)
            }

            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}