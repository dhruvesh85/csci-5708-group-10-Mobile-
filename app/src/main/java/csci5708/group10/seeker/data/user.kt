package csci5708.group10.seeker.data

import com.google.firebase.database.Exclude

class user (
    @get:Exclude
    var id: String? =null,
    var username: String? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var email: String? = null,
    var pass: String? = null,
    var imgurl: String? =null,
    var imageuri: String? = null
)