package com.example.week4

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){

    abstract fun getLoggerTag() : String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(getLoggerTag(), "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(getLoggerTag(), "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.e(getLoggerTag(), "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(getLoggerTag(), "onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(getLoggerTag(), "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(getLoggerTag(), "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(getLoggerTag(), "onDetach")
    }
}