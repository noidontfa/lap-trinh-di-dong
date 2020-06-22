package com.example.car_parking

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.car_parking.models.MarkerModel
import com.example.car_parking.models.RoomModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    val TAG = "MapsActivity"
    private lateinit var mMap: GoogleMap
    private val database = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        onLoadMarkerFromDatabase(mMap)
        mMap.setOnMarkerClickListener {marker ->
            onLoadRoomFromDatabase(marker)
            true
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(10.7629183,106.679983),17.0f))
    }

    private fun onLoadRoomFromDatabase(marker: Marker) {
        val roomId = marker.tag
        database.getReference("rooms").orderByChild("roomId").equalTo(roomId.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG,error.toString())
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    for ( snap in snapshot.children) {
                        val value = snap.getValue(RoomModel::class.java)
                        Log.d(TAG,value.toString())
                        Toast.makeText(applicationContext,value!!.name,Toast.LENGTH_SHORT).show()
                        // xu ly man hinh car parking o day
                    }
                }

            })
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    private fun onLoadMarkerFromDatabase(mMap : GoogleMap) {
            val markerRef = database.getReference("marker")
            markerRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG,error.toString())
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (snap in snapshot.children) {
                        val marker = snap.getValue(MarkerModel::class.java)
                        val position = LatLng(marker!!.x,marker!!.y)
                        val m = mMap.addMarker(MarkerOptions().position(position).title(marker.title)
                            .icon(bitmapDescriptorFromVector(this@MapsActivity,R.drawable.test)))
                        m.tag = marker.roomId
                    }
                }
            })
        }

}


