package csci5708.group10.seeker

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_book.view.*

class BooksAdaptor (
                    private val books: ArrayList<String>,
                    private val pics: ArrayList<Int>,
                    private val context:Context,
                    private val view: RecyclerView
                    ) : RecyclerView.Adapter<BooksAdaptor.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val bookName: TextView = itemView.findViewById(R.id.bookName)
        //val authorName:TextView = itemView.findViewById(R.id.authorName)
        val coverPic: ImageView
            get() = itemView.cover
        val book: CardView =itemView.findViewById((R.id.bookView))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksAdaptor.ViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BooksAdaptor.ViewHolder, position: Int) {

        holder.bookName.text = books[position]
        holder.coverPic.setImageResource(pics[position])
        //Glide.with(context).load(pics).into(holder.coverPic)

        //holder.authorName.text=authors[position]
            holder.book.setOnClickListener {
                val intent = Intent(context, BookReaderActivity::class.java)
                intent.putExtra("Position",position)
                var name:String=view.tag.toString()
                intent.putExtra("TAG",name)
                Toast.makeText(context,"Clicked "+position,Toast.LENGTH_SHORT).show()
                context.startActivity(intent)
            }

        }


    }

//private fun ImageView.setImageResource(pics: ArrayList<Int>) {
//
//
//}
