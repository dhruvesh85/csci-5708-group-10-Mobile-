package com.example.recyclehorizontal



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.android.synthetic.main.book_reader.*


class BookReaderActivity : AppCompatActivity() {

    val StoryBook1 = "Catch 22.pdf"
    val StoryBook2 = "Coldest winter ever.pdf"
    val StoryBook3 = "Confessions of a shopaholic.pdf"
    val StoryBook4 = "Hacking For Dummies.pdf"
    val StoryBook5 = "Half Girlfriend.pdf"
    val FictionBook1 = "Gone Girl.pdf"
    val FictionBook2 = "Frankenstein.pdf"
    val FictionBook3 = "Jane Eyre.pdf"
    val FictionBook4 = "The Red Sari.pdf"
    val FictionBook5 = "One Hundred Years of Solitude.pdf"
    val NonFictionBook1 = "History of Middle Earth.pdf"
    val NonFictionBook2 = "Eye of the World.pdf"
    val NonFictionBook3 = "Discover Your Destiny.pdf"
    val NonFictionBook4 = "Home.pdf"
    val NonFictionBook5 = "It Started with a Friend Request.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_reader)

        val name=intent.getStringExtra("TAG")
        Log.d("Tag",name)


        if(name.contentEquals("A"))
        {
            story()
            Log.d("Tag","TAG A")
        }
        else if(name.contentEquals("B"))
        {
            fiction()
            Log.d("Tag","TAG B")
        }
        else if(name=="C")
        {
            nonFiction()
            Log.d("Tag","TAG C")
        }




    }

     fun story(){
         val storyBook: ArrayList<String> = ArrayList()

         storyBook.add(StoryBook1)
         storyBook.add(StoryBook2)
         storyBook.add(StoryBook3)
         storyBook.add(StoryBook4)
         storyBook.add(StoryBook5)
         var index=intent.getIntExtra("Position",2)


         var pdf = findViewById(R.id.pdf) as PDFView

         pdf.fromAsset(storyBook[index]).load()

     }
    fun fiction()
    {
        val fictionBook: ArrayList<String> = ArrayList()

        fictionBook.add(FictionBook1)
        fictionBook.add(FictionBook2)
        fictionBook.add(FictionBook3)
        fictionBook.add(FictionBook4)
        fictionBook.add(FictionBook5)
        var index=intent.getIntExtra("Position",2)


        var pdf = findViewById(R.id.pdf) as PDFView
        pdf.fromAsset(fictionBook[index]).load()


    }
    fun nonFiction(){

        val nonFictionBook: ArrayList<String> = ArrayList()
        nonFictionBook.add(NonFictionBook1)
        nonFictionBook.add(NonFictionBook2)
        nonFictionBook.add(NonFictionBook3)
        nonFictionBook.add(NonFictionBook4)
        nonFictionBook.add(NonFictionBook5)
        var index=intent.getIntExtra("Position",2)


        var pdf = findViewById(R.id.pdf) as PDFView
        pdf.fromAsset(nonFictionBook[index]).load()

    }
}
