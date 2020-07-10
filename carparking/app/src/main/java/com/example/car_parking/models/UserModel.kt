package com.example.car_parking.models

data class UserModel(var id : String, var email : String, var password : String, var fullName : String) {
    constructor() : this("","","", "")
}