package com.example.car_parking

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Transformations.map
import com.example.car_parking.models.MarkerModel
import com.example.car_parking.models.RoomModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MapsActivity : AppCompatActivity(){
    val TAG = "MapsActivity"
    private lateinit var mMap: GoogleMap
    private val database = Firebase.database

    lateinit var locationParking: LocationParking
    lateinit var parking: Parking
    lateinit var account: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        val mapFragment = supportFragmentManager
//                .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
        locationParking = LocationParking()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, locationParking)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        val bottomNav : BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener { item: MenuItem ->
            when(item.itemId){
                R.id.locationParking -> {
                    locationParking = LocationParking()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, locationParking)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.Parking -> {
                    parking = Parking()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, parking)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.Account -> {
                    account = Account()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, account)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }
}


