package csci5708.group10.seeker

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.fragment_add_book.*
import java.io.FileOutputStream
import java.lang.Exception
import kotlin.properties.Delegates
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_add_book.view.*



/**
 * A simple [Fragment] subclass.
 */
class AddBookFragment : Fragment() {
    val STORAGE_CODE:Int=100
    //var saveButton: Button by Delegates.notNull()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragme
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sf = activity?.getSharedPreferences("loginpref", 0)
        val login: String? = sf?.getString("login", "")

        if (login.equals("1")) {
            saveButton.setOnClickListener {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(
                            getActivity()!!.getApplicationContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_DENIED
                    ) {
                        val permissions =
                            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        requestPermissions(permissions, STORAGE_CODE)

                    } else {
                        savePdf()
                    }
                } else {
                    savePdf()
                }
            }
        }
        else{
            val toast = Toast.makeText(activity,"Please login first",Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 150)
            toast.show()

            val intent = Intent(activity, login_act::class.java)
            startActivity(intent)
        }



        }

        fun savePdf() {

            val doc=com.itextpdf.text.Document()
            val fileName="Story"
            var filePath=getActivity()?.getApplicationContext()?.getFilesDir().toString()+fileName+".pdf"
            //val filePath=Environment.getExternalStorageState().toString()+"/"+fileName+".pdf"
            try{
                PdfWriter.getInstance(doc, FileOutputStream(filePath))

                doc.open()
                val title=title.text.toString()
                val text=description.text.toString()
                doc.addAuthor("Vigu")
                doc.add(Paragraph(title))
                doc.add(Paragraph(text))


                doc.close()

                Toast.makeText(activity,"$fileName.pdf saved to path $filePath", Toast.LENGTH_LONG).show()



            }
            catch (e: Exception){
                Toast.makeText(getContext(),filePath, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            when(requestCode){
                STORAGE_CODE->{
                    if(grantResults.size>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                        savePdf()
                    }
                    else{
                        Toast.makeText(activity,"Permission denied", Toast.LENGTH_SHORT).show()

                    }
                }
            }

        }

    }


