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
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    protected lateinit var rootView: View
    lateinit var recyclerViewShortStories: RecyclerView
    lateinit var recyclerViewFictionStories: RecyclerView
    lateinit var recyclerView: RecyclerView

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val rootView = inflater.inflate(R.layout.fragment_home, container, false)

         //non fiction
         val nonFictionBooks: ArrayList<String> = ArrayList()
         val authorNonFictionBooks: ArrayList<String> = ArrayList()

         for(i in 1..5) {
             nonFictionBooks.add("#$i Non-Fiction Book")
             authorNonFictionBooks.add("Author #$i")
         }
         recyclerView = rootView.findViewById(R.id.viewNonFictionBooks) as RecyclerView // Add this
         recyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
         recyclerView.adapter = BooksAdaptor(nonFictionBooks,requireContext(),recyclerView)

         //fiction stories
         val fictionBooks: ArrayList<String> = ArrayList()
         val authorFictionBooks: ArrayList<String> = ArrayList()

         for(i in 1..5){
             fictionBooks.add("#$i Fiction Book" )
             authorFictionBooks.add("Author #$i")
         }
         recyclerViewFictionStories = rootView.findViewById(R.id.viewFictionBooks) as RecyclerView // Add this
         recyclerViewFictionStories.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
         recyclerViewFictionStories.adapter = BooksAdaptor(fictionBooks,requireContext(),recyclerViewFictionStories)

         //short stories
         val stories: ArrayList<String> = ArrayList()
         val authorStories: ArrayList<String> = ArrayList()
         for(i in 1..5){
             stories.add("#$i Short Story")
             authorStories.add("Author #$i")
         }
         recyclerViewShortStories = rootView.findViewById(R.id.viewShortStories) as RecyclerView // Add this
         recyclerViewShortStories.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
         recyclerViewShortStories.adapter = BooksAdaptor(stories,requireContext(),recyclerViewShortStories)

         return rootView

    }//create view

}//HomeFragment
