package com.example.userssp

data class User(val id:Long, val name:String, val lastName:String, val url:String=""){
    fun getFullName(): String = "$name $lastName"
}
