package com.example.car_parking.models

// thay doi model de hien thi duoc day du thong in
data class RoomModel(var roomId : String, var name : String) {
    var timeCheckIn : Int
    var timeCheckOut : Int
    var isEmpty : Boolean
    var Id : Int
    var Slot = ArrayList<SlotModel>()

    init{
        timeCheckIn = 0
        timeCheckOut = 0
        isEmpty = false
        Id = -1
    }
    constructor() : this("", "")
}