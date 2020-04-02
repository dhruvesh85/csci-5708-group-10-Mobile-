package csci5708.group10.seeker

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import csci5708.group10.seeker.data.User_Node
import csci5708.group10.seeker.data.loggedin
import csci5708.group10.seeker.data.user
import kotlinx.android.synthetic.main.profile.view.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class ProfileFragment : Fragment() {

    val user_ = loggedin()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v: View = inflater.inflate(R.layout.profile, container, false)

        val sf = activity?.getSharedPreferences("loginpref", 0)
        val str: String? = sf?.getString("login_email", "")
        Log.d("email", "$str")

       retrive_img(str.toString())

        Log.i("abc","${user_.imgurl}")
        Picasso.get().load(user_.imgurl).into(v.imageButton);
        Log.i("abc1","${user_.imgurl}")
        v.email_text.text = user_.email
        return v
    }

    fun retrive_img(str: String){
        val dbuser = FirebaseDatabase.getInstance().getReference(User_Node)
            .orderByChild("email")
            .equalTo(str)

        dbuser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        Log.i("snap", "$snap")
                        Log.i("snap", "${snap.value}")
                        Log.i("snap1", "${snap.child("imgurl")}")
                        user_.imgurl = snap.child("imgurl").value.toString()
                        user_.username = snap.child("username").value.toString()
                        user_.email = snap.child("email").value.toString()
//                        val sf=getSharedPreferences("loginpref",0)
//                        val ed=sf.edit()
//                        ed.putString("login","1")
//                        ed.putString("login_email","$email")
//                        ed.apply()
                    }
                }
            }
        })
    }
}
