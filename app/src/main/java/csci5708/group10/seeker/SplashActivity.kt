package csci5708.group10.seeker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private val spashTime:Long=5000 // 3 sec
    lateinit var bgapp: ImageView
    lateinit var clover:ImageView

    lateinit var textsplash: LinearLayout
    lateinit var texthome :LinearLayout
    lateinit var menus:LinearLayout

    lateinit var frombottom: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom)
        bgapp = findViewById<ImageView>(R.id.bgapp)
        clover = findViewById<ImageView>(R.id.clover)
        textsplash = findViewById<LinearLayout>(R.id.textsplash)
        texthome = findViewById<LinearLayout>(R.id.texthome)
        menus = findViewById<LinearLayout>(R.id.menus)

        bgapp.animate().translationY(-2500f).setDuration(1600).startDelay = 800
        clover.animate().alpha(0f).setDuration(1600).startDelay = 1400
        textsplash.animate().translationY(140f).alpha(0f).setDuration(1600).startDelay = 800

        texthome.startAnimation(frombottom)
        menus.startAnimation(frombottom)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            val sf=getSharedPreferences("loginpref",0)
            val str: String? = sf.getString("login", "")
            if(str.equals("1"))
            {
                startActivity(Intent(this,MainActivity::class.java))
            }else
            {
                val intent = Intent(this, login_act::class.java)
                startActivity(intent)
            }

            // close this activity
            finish()
        }, spashTime)
    }
}