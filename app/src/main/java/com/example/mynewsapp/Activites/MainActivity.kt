package com.example.mynewsapp.Activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapp.SpacesItemDecoration
import com.example.mynewsapp.paging.NewsPagingAdapter
import com.example.mynewsapp.viewModel.NewsViewModel
import com.example.mynewsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter: NewsPagingAdapter
    val viewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= NewsPagingAdapter()
        binding.Rvmain.adapter=adapter
        binding.Rvmain.layoutManager = LinearLayoutManager(this)
        val itemDecoration = SpacesItemDecoration(20)
        binding.Rvmain.addItemDecoration(itemDecoration)




        viewModel.list.observe(this) { pagingData ->
            adapter.submitData(lifecycle, pagingData)

        }
    }
}