package com.example.car_parking.models

data class SlotModel(var ParkingFee : String, var LicensePlate: String, var ParkingTime: String, var isEmpty: Boolean, var roomId : String)
{
    constructor() : this("","", "", false, "")
}