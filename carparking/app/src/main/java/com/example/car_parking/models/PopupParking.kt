package com.example.car_parking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.car_parking.models.MarkerModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

val TAG = "MapsActivity"
private val database = Firebase.database

class PopupParking : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_popup_info, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvAddr: TextView = view.findViewById(R.id.tv_addr)
        val tvHour: TextView = view.findViewById(R.id.tv_hour)
        val tvRating: TextView = view.findViewById(R.id.tv_rating)
        val markerRef = database.getReference("marker")
        markerRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snap in dataSnapshot.children) {
                    val marker = snap.getValue(MarkerModel::class.java)
                    tvTitle.setText(marker!!.title)
                    tvAddr.setText(marker!!.address)
                    tvHour.setText(marker!!.hour)
                    tvRating.setText(marker!!.rating.toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }

}