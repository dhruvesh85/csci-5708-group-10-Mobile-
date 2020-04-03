package csci5708.group10.seeker

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import csci5708.group10.seeker.data.User_Node
import csci5708.group10.seeker.data.user
import kotlinx.android.synthetic.main.signup.*
import java.util.*

class signup : AppCompatActivity() {
    var Flag=0
    private lateinit var auth: FirebaseAuth
    var selectedphotouri: Uri? = null
    var mUri: Uri? = null
    var abc: String? =null
    val user_ = user()
    val vali = Validate()
    fun adduser(user: user) {
        val dbuser = FirebaseDatabase.getInstance().getReference(User_Node)
        user.id = dbuser.push().key
//        if(Flag == 1) uploadImage(mUri)
//        if(Flag ==2 ) uploadImage(selectedphotouri)
        user.imgurl = user_.imgurl.toString()
        user.imageuri = user_.imageuri.toString()
        dbuser.child(user.id!!).setValue(user)
            .addOnCompleteListener {
                val message = if (it.isSuccessful) {
                    getString(R.string.useradd)
                    val sf=getSharedPreferences("loginpref",0)
                    val ed=sf.edit()
                    val email = user_.email
                    val id = user.id
                    ed.putString("$email","$id")
                    ed.apply()
                } else {
                    getString(R.string.error, it.exception)
                }

                if (message.equals(R.string.useradd)) {
                    Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
                }
            }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        auth = FirebaseAuth.getInstance()
        back1.setOnClickListener {
            when (it?.id) {
                R.id.back1-> {
                    val intent = Intent(this, login_act::class.java)
                    startActivity(intent)
                }
            }
        }
        button2.setOnClickListener {
            when (it?.id) {
                R.id.button2 -> {
                    if (vali.checkUname(uname1) && vali.checkFname(firstname) && vali.checkLname(
                            lastname
                        ) && vali.checkEmail(email) && vali.checkPassword(pass1) && vali.checkConfirmPassword(
                            pass1,
                            pass2
                        )
                    ) {

                        val username = uname1.text.toString().trim()
                        val firstname1 = firstname.text.toString().trim()
                        val lastname1 = lastname.text.toString().trim()
                        val email1 = email.text.toString().trim()
                        val pass = pass1.text.toString().trim()


                        user_.firstname = firstname1
                        user_.lastname = lastname1
                        user_.email = email1
                        user_.username = username
                        user_.pass = pass


                        auth.createUserWithEmailAndPassword(email1, pass)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    adduser(user_)
                                    Toast.makeText(
                                        applicationContext,
                                        "Signup Successful",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val intent = Intent(this, login_act::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    if (task.exception is FirebaseAuthUserCollisionException) {
                                        email.error = " Email already used"
                                        email.requestFocus()
                                    } else {
                                        Log.e("Error", task.exception.toString())
                                        Toast.makeText(
                                            baseContext,
                                            "Sign up failed try again after some time.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }


                                }

                            }
                    }
                }
            }
        }
        profile_img.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choose")
            builder.setItems(R.array.choice) {dialog, which ->
                if (which == 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(android.Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED
                        ) {
                            val permissions =
                                arrayOf(
                                    android.Manifest.permission.CAMERA,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                                )
                            requestPermissions(permissions, PERMISSION_CO)
                        } else {
                            opencamera()
                        }
                    } else {
                        opencamera()
                    }
                } else if (which == 1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED
                        ) {
                            val permissions =
                                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            requestPermissions(permissions, PERMISSION_CODE)
                        } else {
                            pickImageFromGallery()
                        }
                    } else {
                        pickImageFromGallery()
                    }
                }
            }
            val dialog = builder.create()
            dialog.show()


        }
    }


    private fun opencamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        mUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri)
        startActivityForResult(intent, IMAGE_CAPTURE_CODE)

    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000;
        private val IMAGE_CAPTURE_CODE = 1007;
        private val PERMISSION_CODE = 1001;
        private val PERMISSION_CO = 1002;
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
            PERMISSION_CO -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    opencamera()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("find", "here")

        when (requestCode) {
            IMAGE_CAPTURE_CODE ->
                if (resultCode == Activity.RESULT_OK) {
                    profile_img.setImageURI(mUri)
                    Flag =1
                    abc= mUri.toString()
                    uploadImage(mUri)
                }
            IMAGE_PICK_CODE ->
                if (resultCode == Activity.RESULT_OK && data != null) {
                    selectedphotouri = data.data
                    Log.d("find", data.data.toString())
                    profile_img.setImageURI(selectedphotouri)
                    abc = selectedphotouri.toString()
                    Flag =2
                    uploadImage(selectedphotouri)
//                    val bitmap =
//                        MediaStore.Images.Media.getBitmap(contentResolver, selectedphotouri)
//                    val bitmapDrawable = BitmapDrawable(bitmap)
//                    Log.d("acd", bitmapDrawable.toString())
                }
        }
//        if (resultCode == Activity.RESULT_OK && (requestCode == IMAGE_PICK_CODE || requestCode == IMAGE_CAPTURE_CODE) && data != null) {
//             selectedphotouri = data.data
//            Log.d("find",data.data.toString())
//            profile_img.setImageURI(selectedphotouri)


    }

    private fun uploadImage(uri: Uri?) {
        if (uri == null) return
        val ran =UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/${ran}")
         ref.putFile(uri)
             .addOnSuccessListener {
                 ref.downloadUrl.addOnSuccessListener {
                     user_.imgurl = it.toString()
                     Log.d("acd", "user added to user")
                 }
             }


    }


}


