package com.example.car_parking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.car_parking.models.RoomModel
import com.example.car_parking.models.SlotModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_parking.*

/**
 * A simple [Fragment] subclass.
 */
class  Parking : Fragment() {
    lateinit var slider: SeekBar
    lateinit var valueTime: TextView
    lateinit var totalPay: TextView
    lateinit var button: Button

    private val database = FirebaseDatabase.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val TAG = "AAA"
        var Slot = RoomModel()
        //textView2.text = arguments!!.getString("address")
        // Toast.makeText(activity, arguments!!.getString("address"), Toast.LENGTH_SHORT).show()
        txtAddress.text = arguments!!.getString("address")
        textView5.text = arguments!!.getString("hour")
        textView7.text = arguments!!.getString("slot")
        //Toast.makeText(activity, arguments!!.getString("roomId"), Toast.LENGTH_SHORT).show()
        val roomId = arguments!!.getString("roomId")
        Log.e(TAG, roomId)
        button = view.findViewById(R.id.button) as Button
        var editTextID = view.findViewById(R.id.editText2) as EditText

        slider = view.findViewById(R.id.seekBar) as SeekBar
        valueTime = view.findViewById(R.id.textView16) as TextView
        totalPay = view.findViewById(R.id.textView15) as TextView

        slider.max = 10

        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                valueTime.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                valueTime.text = seekBar?.progress.toString()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                valueTime.text = seekBar?.progress.toString() + " giờ"
                totalPay.text = (seekBar!!.progress*10000.toInt()).toString() + "đ"
            }
        })

        var editTextLicensePlate = view.findViewById(R.id.editText) as EditText
        var licensePlate = editTextLicensePlate.text.toString().trim()
        var parkingFee = valueTime.text
        var parkingTime = totalPay.text

        val slot = SlotModel(parkingFee as String, licensePlate , parkingTime as String)

        // Xử lý khi user nhấn nút Đăng kí đỗ xe
        button.setOnClickListener() {
            var SlotId = editTextID.text.toString()
            Toast.makeText(activity, SlotId, Toast.LENGTH_SHORT).show()
            // database.reference.child("rooms").orderByChild("roomId").equalTo(roomId.toString())

            database.getReference("rooms").orderByChild("roomId").equalTo(roomId.toString())
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (snap in snapshot.child("Slot").children) {
                            if (snap.child("Id").getValue().toString() == SlotId) {
                                  database.reference.child("rooms").child("Slot")
                                      .child("Slot${SlotId}").setValue(slot)
                            }
                        }
                    }
                })

            // Giảm số slot mỗi khi user đăng kí đỗ xe trong bãi
            var amountSlot = (textView7.text as String?)?.toInt()?.minus(1)
            if (amountSlot != null) {
                if(amountSlot < 0) {
                    Toast.makeText(activity, "Slot of parking is full!!!", Toast.LENGTH_SHORT).show()
                } else {
                    textView7.text = amountSlot.toString()
                }
            }
        }

    }

}
