package csci5708.group10.seeker
import android.widget.EditText
import java.util.regex.Pattern

class Validate {
    private val num = Pattern.compile("[0-9]")
    private val emailre = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"
    private val email = Pattern.compile(emailre)
    private val passre = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,}"
    private val password = Pattern.compile(passre)

    fun checkUname(edit: EditText): Boolean {
        val uname = edit.text.toString().trim()
        if (uname.isEmpty()) {
            edit.error = "Please enter user name."
            edit.requestFocus()
            return false
        }
        return true
    }

    fun checkFname(edit: EditText): Boolean {
        val fname = edit.text.toString().trim()
        if (fname.isEmpty()) {
            edit.error = "Please enter first name "
            edit.requestFocus()
            return false
        } else if (num.matcher(fname).find()) {
            edit.error = "digit not allowed"
            edit.requestFocus()
            return false
        }
        return true
    }

    fun checkLname(edit: EditText): Boolean {
        val lname = edit.text.toString().trim()
        if (lname.isEmpty()) {
            edit.error = "fill detail"
            edit.requestFocus()
            return false
        } else if (num.matcher(lname).find()) {
            edit.error = "digit not allowed"
            edit.requestFocus()
            return false
        }
        return true
    }


    fun checkEmail(edit: EditText): Boolean {
        val email = edit.text.toString().trim()
        if (email.isEmpty()) {
            edit.error = "Please enter email"
            edit.requestFocus()
            return false
        } else if (!this.email.matcher(email).find()) {
            edit.error = "invalid"
            edit.requestFocus()
            return false
        }
        return true
    }

//    fun checkPhone(edit: EditText): Boolean {
//        val phonenum = edit.text.toString().trim()
//        if (phonenum.isEmpty()) {
//            edit.error = "please enter phone number"
//            edit.requestFocus()
//            return false
//        } else if (phonenum.length != 10) {
//            edit.error = "invalid"
//            edit.requestFocus()
//            return false
//        } else if (!num.matcher(phonenum).find()) {
//            edit.error = "no character allowed"
//            edit.requestFocus()
//            return false
//        }
//        return true
//    }

    fun checkPassword(edit: EditText): Boolean {
        val password = edit.text.toString().trim()
        if (password.isEmpty()) {
            edit.error = "Please enter password"
            edit.requestFocus()
            return false
        } else if (!this.password.matcher(password).find()) {
            edit.error = "Please enter Strong password"
            edit.requestFocus()
            return false
        }
        return true
    }

    fun checkConfirmPassword(password: EditText, conpassword: EditText): Boolean {
        if (conpassword.text.toString().trim() != password.text.toString().trim()) {
            conpassword.error = "password not matched"
            conpassword.requestFocus()
            return false
        }
        return true
    }
}