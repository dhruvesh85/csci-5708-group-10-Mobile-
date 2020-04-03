//package csci5708.group10.seeker
////
////import android.os.Bundle
////import android.util.Log
////import androidx.fragment.app.Fragment
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import android.widget.Button
////import android.widget.EditText
////import android.widget.TextView
////import android.widget.Toast
////import androidx.navigation.fragment.findNavController
////import kotlinx.android.synthetic.main.fragment_profile.*
////
/////**
//// * A simple [Fragment] subclass.
//// */
////class ProfileFragment : Fragment() {
////
////    lateinit var loginButton: Button
////    lateinit var username: EditText
////    lateinit var passwordString: EditText
////
////
////fun openSignUp()
////{
////
////
////
////}
////
////    override fun onCreateView(
////        inflater: LayoutInflater, container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View? {
////        // Inflate the layout for this fragment
////
////
////       // return inflater.inflate(R.layout.fragment_profile, container, false)
////        val view: View = inflater!!.inflate(R.layout.fragment_profile, container, false)
////
////        //validation for username and password
////        loginButton = view.findViewById(R.id.loginButton) as Button
////        username = view.findViewById(R.id.userNameSignUp) as EditText
////        passwordString = view.findViewById(R.id.passwordSignUp) as EditText
////        var signUpButton = view.findViewById(R.id.signUpButton) as TextView
////
////
////        loginButton.setOnClickListener { view ->
////            val userName: String = userNameSignUp.text.toString()
////            println("username is $userName")
////            Log.d("btnSetup", "Selected Login button")
////            if(userName.trim().length>0) {
////                //Toast.makeText(applicationContext, "Message : "+msg_split, Toast.LENGTH_SHORT)
////            }else{
////
////                username.setError("Please enter username to login")
////                Toast.makeText(context,"Please enter username", Toast.LENGTH_SHORT).show()
////            }
////            if(passwordString.length()>0) {
////                //Toast.makeText(applicationContext, "Message : "+msg_split, Toast.LENGTH_SHORT)
////            }else{
////
////                passwordString.setError("Password cannot be empty")
////                Toast.makeText(context,"Please enter password ", Toast.LENGTH_SHORT).show()
////            }
////        }//on click listener
////
////
////        signUpButton.setOnClickListener{ findNavController().navigate(R.id.fragment_signup)
////
////
////        }
////        return view
////    }
////
////
////}

package csci5708.group10.seeker


import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import csci5708.group10.seeker.data.User_Node
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*




class ProfileFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val rollButton: TextView = findViewById(R.id.emailSignUp)
        // Inflate the layout for this fragment
        var v: View = inflater.inflate(R.layout.fragment_profile, container, false)

        val sf = activity?.getSharedPreferences("loginpref", 0)
        val str: String? = sf?.getString("login_email", "")
        val login: String? = sf?.getString("login", "")

        if (login.equals("1")) {
            Log.d("email", "$str")
            val dbuser = FirebaseDatabase.getInstance().getReference(User_Node)
                .orderByChild("email")
                .equalTo(str)
            dbuser.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (snap in snapshot.children) {
                            Log.i("snap", "$snap")
                            Log.i("snap", "${snap.value}")
                            Log.i("snap1", "${snap.child("imgurl")}")
                            var imgurl = snap.child("imgurl").value.toString()
                            var user_name = snap.child("username").value.toString()
                            var email_1 = snap.child("email").value.toString()
                            val ed = sf?.edit()
                            ed?.putString("login_name", "$user_name")
                            ed?.putString("login_email", "$email_1")
                            if (!imgurl.equals("null")) ed?.putString("login_url", "$imgurl")
                            ed?.apply()
                        }
                    }
                }
            })

            val e_mail: String? = sf?.getString("login_email", "")
            val u_na: String? = sf?.getString("login_name", "")
            val i_url: String? = sf?.getString("login_url", "")
            Log.i("abc1", "${i_url}")
            v.emailSignUp.setText("$e_mail")
            v.username.setText("$u_na")

            if (!i_url.equals("null")) Glide.with(this)?.load(i_url).into(v.imageButton)

            v.logout.setOnClickListener {
                val sf = activity?.getSharedPreferences("loginpref", 0)
                val ed = sf?.edit()
                ed?.putString("login", "0")
                ed?.putString("login_url", "null")
                ed?.apply()
                val intent = Intent(activity, login_act::class.java)
                startActivity(intent)
            }

        }
        else{
            val toast = Toast.makeText(activity,"Please login first",Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 150)
            toast.show()

            val intent = Intent(activity, login_act::class.java)
            startActivity(intent)
        }
        return v
    }

}
