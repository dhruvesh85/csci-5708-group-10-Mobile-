package csci5708.group10.seeker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.*
import android.widget.TextView



class SignUpFragment: Fragment()
{

    lateinit var signUpButton: Button
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText
    lateinit var email: EditText




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        // return inflater.inflate(R.layout.fragment_profile, container, false)
        val view: View = inflater!!.inflate(R.layout.fragment_signup, container, false)

        //validation for username and password
        signUpButton = view.findViewById(R.id.signUpButton) as Button
        username = view.findViewById(R.id.userNameSignUp) as EditText
        password = view.findViewById(R.id.passwordSignUp) as EditText
        confirmPassword = view.findViewById(R.id.confirmPasswordSignUp) as EditText
        email = view.findViewById(R.id.emailSignUp) as EditText

        signUpButton.setOnClickListener { view ->

            if(username.length()>0) {
                //Toast.makeText(applicationContext, "Message : "+msg_split, Toast.LENGTH_SHORT)
            }else{

                username.setError("Username cannot be empty")
                Toast.makeText(context,"Please enter username", Toast.LENGTH_SHORT).show()
            }
            if(password.length()>0) {
                //Toast.makeText(applicationContext, "Message : "+msg_split, Toast.LENGTH_SHORT)
            }else{

                password.setError("Password cannot be empty")
                Toast.makeText(context,"Please enter password ", Toast.LENGTH_SHORT).show()
            }
            if(confirmPassword.length()>0) {
                //Toast.makeText(applicationContext, "Message : "+msg_split, Toast.LENGTH_SHORT)
            }else{

                confirmPassword.setError("Confirm password cannot be empty")
                Toast.makeText(context,"Please re-enter password ", Toast.LENGTH_SHORT).show()
            }
            if(email.length()>0) {
                //Toast.makeText(applicationContext, "Message : "+msg_split, Toast.LENGTH_SHORT)
            }else{

                email.setError("Emailword cannot be empty")
                Toast.makeText(context,"Please enter email ", Toast.LENGTH_SHORT).show()
            }

        }//on click listener
        return view
    }

}