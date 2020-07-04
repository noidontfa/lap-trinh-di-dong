package com.example.car_parking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*

/**
 * A simple [Fragment] subclass.
 */
class Parking : Fragment() {
    lateinit var slider: SeekBar
    lateinit var valueTime: TextView
    lateinit var totalPay: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                valueTime.text = seekBar?.progress.toString()
                totalPay.text = (seekBar!!.progress*10000.toInt()).toString()
            }
        })
    }

}
