package csci5708.group10.seeker.data

import com.google.firebase.database.Exclude

class loggedin(@get:Exclude
               var username: String? = null,
               var email: String? = null,
               var imgurl: String? =null)