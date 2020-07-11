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
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_popup_info.*

val TAG = "Mln"
private val database = FirebaseDatabase.getInstance()

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
        //tv_title.text = arguments!!.getString("title")
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvAddr: TextView = view.findViewById(R.id.tv_addr)
        val tvHour: TextView = view.findViewById(R.id.tv_hour)
        val tvRating: TextView = view.findViewById(R.id.tv_rating)
        val markerRef = database.getReference("marker")
        val room = arguments?.getString("roomId")
        Log.e(TAG, room)
        markerRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (snap in dataSnapshot.children) {
                    val marker = snap.getValue(MarkerModel::class.java)
                    Log.e(TAG, marker!!.hour)
                    tvTitle.text = arguments!!.getString("title")
                    tvAddr.text = arguments!!.getString("address")
                    tvHour.text = arguments!!.getString("hour")
                    tvRating.text = arguments!!.getString("rating")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
//        database.reference.child("rooms").orderByChild("roomId").equalTo(roomId.toString())
//            .addValueEventListener(object : ValueEventListener {
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    for (snap in snapshot.child("Slot").children) {
//                        if (snap.child("Id").getValue().toString() == SlotId) {
//                            //  database.reference.child("rooms").child("Slot").child("Slot${SlotId}").setValue()
//                        }
//                    }
//                }
//            })
//        database.reference.child("rooms").orderByChild("roomId").equalTo(roomId.toString())
//            .addValueEventListener(object : ValueEventListener {
////        markerRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for(snap in dataSnapshot.children) {
//                    val marker = snap.getValue(MarkerModel::class.java)
//                    if(snap.child("roomId").getValue().toString() == roomId)
//                    {
////                        tvTitle.text = arguments!!.getString("title")
////                        tvAddr.text = arguments!!.getString("address")
////                        tvHour.text = arguments!!.getString("hour")
////                        tvRating.text = arguments!!.getString("rating")
//                        tvTitle.setText(marker!!.title)
//                        tvAddr.setText(marker!!.address)
//                        tvHour.setText(marker!!.hour)
//                        tvRating.setText(marker!!.rating.toString())
//
//                    }
//                }
//            }
//
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//                // ...
//            }
//        })

    }
}