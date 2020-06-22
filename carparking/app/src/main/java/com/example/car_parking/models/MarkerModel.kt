package com.example.car_parking.models

data class MarkerModel(var x: Double, var y: Double, var title: String, var roomId: String) {
    constructor() : this(0.0,0.0,"","")
}