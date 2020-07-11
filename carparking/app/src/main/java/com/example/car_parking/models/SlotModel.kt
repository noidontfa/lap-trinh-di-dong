package com.example.car_parking.models

data class SlotModel(var parkingFee : String, var licensePlate: String, var parkingTime: String,
                     var empty: Boolean, var roomId : String, var slotId : String)
{
    constructor() : this("","", "", false, "", "")
}