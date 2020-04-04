package csci5708.group10.seeker

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.dailog_forgot_pass.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.login.*

import java.util.regex.Pattern


class login_act: AppCompatActivity() {
    val vali = Validate()
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance()

        textView4.setOnClickListener {
            when (it?.id) {
                R.id.textView4 -> {
                    val intent = Intent(this, signup::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        forgot.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dailog_forgot_pass,null)
            val email = view.findViewById<EditText>(R.id.et_email)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotpassword(email)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
        }
        skip.setOnClickListener {
            val sf=getSharedPreferences("loginpref",0)
            val ed=sf.edit()
            ed.putString("login","0")
            ed.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        button.setOnClickListener {
            when (it?.id) {
                R.id.button -> {
                    if (vali.checkEmail(uname) && validation()) {
                        val email = uname.text.toString().trim()
                        val pass = password.text.toString().trim()
                        auth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    val user = auth.currentUser
                                    Toast.makeText(applicationContext, "Successful", Toast.LENGTH_LONG).show()
                                    val sf=getSharedPreferences("loginpref",0)
                                    val ed=sf.edit()
                                    ed.putString("login","1")
                                    ed.putString("login_email","$email")
                                    ed.apply()

                                    updateUI(user)
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    if (task.exception is FirebaseNetworkException) {
                                        Toast.makeText(baseContext, "Network Failed", Toast.LENGTH_SHORT).show()
                                    } else if (task.exception is FirebaseAuthInvalidUserException) {
                                        uname.error = "No account found"
                                        uname.requestFocus()
                                    } else if (task.exception is FirebaseAuthInvalidCredentialsException) {
                                        password.error = "Wrong Password"
                                        password.requestFocus()
                                    }else{
                                        Toast.makeText(baseContext, "Try Later.", Toast.LENGTH_SHORT).show()
                                        updateUI(null)
                                    }

                                }

                            }

                    }
                }

            }
        }
    }

    private fun forgotpassword(edit: EditText) {
        if (edit.text.toString().isEmpty()) {
            edit.error = "Please enter email"
            edit.requestFocus()
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edit.text.toString()).matches()) {
            edit.error = "invalid"
            edit.requestFocus()
            return
        }

        auth.sendPasswordResetEmail(edit.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Email sent",Toast.LENGTH_SHORT).show()
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {

    }

    private fun validation(): Boolean {
        if (password.text.toString().trim().isEmpty()) {
            password.error = "Password should not be blank."
            password.requestFocus()
            return false
        }
        return true
    }


}
