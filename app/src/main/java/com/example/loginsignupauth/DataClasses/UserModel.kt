package com.example.loginsignupauth.DataClasses

class UserModel (){
    var name: String=""
    var email :String  = ""
    var mobilenumber: String = ""

    constructor(name: String,email: String, mobile: String) :this()
    {
        this.name = name
        this.email = email
        mobilenumber = mobile
    }

}