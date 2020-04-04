package csci5708.group10.seeker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.util.LayoutDirection
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.login.*
//import sun.invoke.util.VerifyAccess.getPackageName



/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    protected lateinit var rootView: View
    lateinit var recyclerViewShortStories: RecyclerView
    lateinit var recyclerViewFictionStories: RecyclerView
    lateinit var recyclerView: RecyclerView


    val StoryBook1 = "Catch 22"
    val StoryBook2 = "Coldest winter ever"
    val StoryBook3 = "Confessions of a shopaholic"
    val StoryBook4 = "Hacking For Dummies"
    val StoryBook5 = "Half Girlfriend"
    val FictionBook1 = "Gone Girl"
    val FictionBook2 = "Frankenstein"
    val FictionBook3 = "Jane Eyre"
    val FictionBook4 = "The Red Sari"
    val FictionBook5 = "One Hundred Years of Solitude"
    val NonFictionBook1 = "History of Middle Earth"
    val NonFictionBook2 = "Eye of the World"
    val NonFictionBook3 = "Discover Your Destiny"
    val NonFictionBook4 = "Home"
    val NonFictionBook5 = "It Started with a Friend Request"

    val Img1 = "home.JPG"
    val Img2 = "coldestwinter.png"
    val Img3 = "confession.png"
    val Img4 = "hacking.png"
    val Img5 = "halfgf.png"
    val Img6 = "gonegirl.png"
    val Img7 = "frankistine.JPG"
    val Img8 = "janeeyre.JPG"
    val Img9 = "redsari.JPG"
    val Img10 = "home.JPG"
    val Img11 = "home.JPG"
    val Img12 = "eyeofthe.png"
    val Img13 = "home.JPG"
    val Img14 = "home.JPG"
    val Img15 = "itstarted.png"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        //non fiction
        val nonFictionBooks: ArrayList<String> = ArrayList()


        val nfpics: ArrayList<Int> = ArrayList()
        nfpics.add(R.drawable.middle)
        nfpics.add(R.drawable.eyeofthe)
        nfpics.add(R.drawable.destiny)
        nfpics.add(R.drawable.home)
        nfpics.add(R.drawable.itstarted)
        //imageView.setImageResource(id)
        val authorNonFictionBooks: ArrayList<String> = ArrayList()
        nonFictionBooks.add(NonFictionBook1)
        nonFictionBooks.add(NonFictionBook2)
        nonFictionBooks.add(NonFictionBook3)
        nonFictionBooks.add(NonFictionBook4)
        nonFictionBooks.add(NonFictionBook5)

//         for(i in 1..5) {
//             nonFictionBooks.add("#$i Non-Fiction Book")
//             authorNonFictionBooks.add("Author #$i")
//         }
        recyclerView = rootView.findViewById(R.id.viewNonFictionBooks) as RecyclerView // Add this
        recyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
        recyclerView.adapter = BooksAdaptor(nonFictionBooks,nfpics,requireContext(),recyclerView)

        //fiction stories
        val fictionBooks: ArrayList<String> = ArrayList()
        val authorFictionBooks: ArrayList<String> = ArrayList()

        fictionBooks.add(FictionBook1)
        fictionBooks.add(FictionBook2)
        fictionBooks.add(FictionBook3)
        fictionBooks.add(FictionBook4)
        fictionBooks.add(FictionBook5)

//         for(i in 1..5){
//             fictionBooks.add("#$i Fiction Book" )
//             authorFictionBooks.add("Author #$i")
//         }

        val fictionPics: ArrayList<Int> = ArrayList()
        fictionPics.add(R.drawable.gonegirl)
        fictionPics.add(R.drawable.frankistine)
        fictionPics.add(R.drawable.janeeyre)
        fictionPics.add(R.drawable.redsari)
        fictionPics.add(R.drawable.hundered)

        //

        recyclerViewFictionStories = rootView.findViewById(R.id.viewFictionBooks) as RecyclerView // Add this
        recyclerViewFictionStories.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
        recyclerViewFictionStories.adapter = BooksAdaptor(fictionBooks,fictionPics,requireContext(),recyclerViewFictionStories)

        //short stories
        val stories: ArrayList<String> = ArrayList()
        val authorStories: ArrayList<String> = ArrayList()

        stories.add(StoryBook1)
        stories.add(StoryBook2)
        stories.add(StoryBook3)
        stories.add(StoryBook4)
        stories.add(StoryBook5)
//         for(i in 1..5){
//             stories.add("#$i Short Story")
//             authorStories.add("Author #$i")
//         }
        val storiesPics: ArrayList<Int> = ArrayList()
        storiesPics.add(R.drawable.eyeofthe)
        storiesPics.add(R.drawable.coldestwinter)
        storiesPics.add(R.drawable.confession)
        storiesPics.add(R.drawable.hacking)
        storiesPics.add(R.drawable.halfgf)
        recyclerViewShortStories = rootView.findViewById(R.id.viewShortStories) as RecyclerView // Add this
        recyclerViewShortStories.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
        recyclerViewShortStories.adapter = BooksAdaptor(stories,storiesPics,requireContext(),recyclerViewShortStories)

        return rootView

    }//create view

}//HomeFragment