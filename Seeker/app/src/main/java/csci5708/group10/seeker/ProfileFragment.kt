package csci5708.group10.seeker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


       // return inflater.inflate(R.layout.fragment_profile, container, false)
        val view: View = inflater!!.inflate(R.layout.fragment_profile, container, false)

        /*loginButton.setOnClickListener { view ->
            val userName: String = userName.text.toString()
            println("username is $userName")
            Log.d("btnSetup", "Selected Login button")
            if(userName.trim().length>0) {
                //Toast.makeText(applicationContext, "Message : "+msg_split, Toast.LENGTH_SHORT)
            }else{

                Toast.makeText(context,"Please enter number of Splits! ", Toast.LENGTH_SHORT).show()
            }
        }*/
        return view
    }


}
