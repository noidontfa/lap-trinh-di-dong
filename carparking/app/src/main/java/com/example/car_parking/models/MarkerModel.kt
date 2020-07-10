package com.example.car_parking.models

data class MarkerModel(var x: Double, var y: Double, var title: String, var roomId: String, var address: String, var hour: String, var rating: Double) {
    constructor() : this(0.0,0.0,"","","","",0.0)
}