package com.example.mynewsapp.Activites

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import com.example.mynewsapp.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val name=intent.getStringExtra("name")
        val title=intent.getStringExtra("title")
        val author=intent.getStringExtra("author")
        val time=intent.getStringExtra("time")
        val image=intent.getStringExtra("image")
        val content=intent.getStringExtra("content")
        val site=intent.getStringExtra("site")


        val newdate=time?.substring(0,10)



        binding.name.text=name?.uppercase()
        binding.title.text=title
        binding.author.text=author
        binding.time.text=newdate
        Picasso.get().load(image).into(binding.image)
        binding.content.text=content
        binding.siteurl.text=site
        Linkify.addLinks(binding.siteurl, Linkify.WEB_URLS)

        binding.ArticleLink.paintFlags = binding.ArticleLink.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
}
