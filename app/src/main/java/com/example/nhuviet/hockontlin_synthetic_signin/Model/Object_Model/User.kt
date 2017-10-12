package com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model

import sega.fastnetwork.test.model.Facebook
import sega.fastnetwork.test.model.Google

/**
 * Created by nhuvi on 22/08/2017.
 */
class User {
    var _id: String? = null
    var name: String? = null
    var email: String? = null
    var photoprofile: String? = null
    var password: String? = null
    var tokenfirebase: String? = null
    val created_at: String? = null
    var facebook : Facebook? = Facebook()
    var google : Google? = Google()
}