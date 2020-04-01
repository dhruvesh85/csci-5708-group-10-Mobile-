package csci5708.group10.seeker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    lateinit var loginButton: Button
    lateinit var username: EditText
    lateinit var passwordString: EditText


fun openSignUp()
{



}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


       // return inflater.inflate(R.layout.fragment_profile, container, false)
        val view: View = inflater!!.inflate(R.layout.fragment_profile, container, false)

        //validation for username and password
        loginButton = view.findViewById(R.id.loginButton) as Button
        username = view.findViewById(R.id.userNameSignUp) as EditText
        passwordString = view.findViewById(R.id.passwordSignUp) as EditText
        var signUpButton = view.findViewById(R.id.signUpButton) as TextView


        loginButton.setOnClickListener { view ->
            val userName: String = userNameSignUp.text.toString()
            println("username is $userName")
            Log.d("btnSetup", "Selected Login button")
            if(userName.trim().length>0) {
                //Toast.makeText(applicationContext, "Message : "+msg_split, Toast.LENGTH_SHORT)
            }else{

                username.setError("Please enter username to login")
                Toast.makeText(context,"Please enter username", Toast.LENGTH_SHORT).show()
            }
            if(passwordString.length()>0) {
                //Toast.makeText(applicationContext, "Message : "+msg_split, Toast.LENGTH_SHORT)
            }else{

                passwordString.setError("Password cannot be empty")
                Toast.makeText(context,"Please enter password ", Toast.LENGTH_SHORT).show()
            }
        }//on click listener


        signUpButton.setOnClickListener{ findNavController().navigate(R.id.fragment_signup)


        }
        return view
    }


}
