package csci5708.group10.seeker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {



     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         /*


         val stories: ArrayList<String> = ArrayList()
         val authorStories: ArrayList<String> = ArrayList()


         for(i in 1..5){
             stories.add("#$i Short Story")
             authorStories.add("Author #$i")
         }

         viewShortStories.layoutManager=LinearLayoutManager(this, OrientationHelper.HORIZONTAL,false)
         viewShortStories.adapter=BooksAdaptor(stories,this@HomeFragment,viewShortStories)


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




*/
         // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}
