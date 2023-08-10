package com.apolisb42.newsproject.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.apolisb42.newsproject.R
import com.apolisb42.newsproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listOfNewsFragment: ListOfNewsFragment
    private lateinit var savedListFragment: SavedListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNav()
       setSupportActionBar(binding.toolbar)
        binding.bottomView.selectedItemId = R.id.home
        supportFragmentManager.addFragmentOnAttachListener { _, fragment ->
                binding.bottomView.isVisible = fragment !is DetailsDescriptionFragment
        }
        supportFragmentManager.addOnBackStackChangedListener {
            Log.d("BackStackEntryCount", supportFragmentManager.backStackEntryCount.toString())
            binding.bottomView.isVisible = supportFragmentManager.backStackEntryCount == 0
        }
    }

    private fun initBottomNav(){
        listOfNewsFragment = ListOfNewsFragment()
        savedListFragment = SavedListFragment()

        with(binding){
            bottomView.setOnItemSelectedListener {
                it.isChecked =true
                when(it.itemId) {
                    R.id.home -> initFrags(listOfNewsFragment)
                    R.id.saved -> initFrags(savedListFragment)
                }
                true
            }
        }
    }
    private fun initFrags(fragment: Fragment){

        supportFragmentManager.beginTransaction().replace(R.id.fragContainer, fragment).commit()

    }

}