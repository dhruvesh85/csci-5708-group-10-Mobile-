package com.example.recyclehorizontal

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.github.barteksc.pdfviewer.PDFView


import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var backButton: Button


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stories: ArrayList<String> = ArrayList()
        val authorStories: ArrayList<String> = ArrayList()


        for(i in 1..5){
            stories.add("#$i Short Story")
            authorStories.add("Author #$i")
        }

        viewShortStories.layoutManager=LinearLayoutManager(this, OrientationHelper.HORIZONTAL,false)
        viewShortStories.adapter=BooksAdaptor(stories,this@MainActivity,viewShortStories)


        val fictionBooks: ArrayList<String> = ArrayList()
        val authorFictionBooks: ArrayList<String> = ArrayList()

        for(i in 1..5){
            fictionBooks.add("#$i Fiction Book" )
            authorFictionBooks.add("Author #$i")
        }


        viewFictionBooks.layoutManager=LinearLayoutManager(this, OrientationHelper.HORIZONTAL,false)
        viewFictionBooks.adapter=BooksAdaptor(fictionBooks,this,viewFictionBooks)

        val nonFictionBooks: ArrayList<String> = ArrayList()
        val authorNonFictionBooks: ArrayList<String> = ArrayList()

        for(i in 1..5){
            nonFictionBooks.add("#$i Non-Fiction Book")
            authorNonFictionBooks.add("Author #$i")
        }


        viewNonFictionBooks.layoutManager=LinearLayoutManager(this, OrientationHelper.HORIZONTAL,false)
        viewNonFictionBooks.adapter=BooksAdaptor(nonFictionBooks,this,viewNonFictionBooks)

   }



}
