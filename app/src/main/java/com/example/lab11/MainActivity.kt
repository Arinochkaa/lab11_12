package com.example.lab11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.telephony.SmsManager
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val mDialButton = findViewById<Button>(R.id.call_button)
            val smsEdit = findViewById<EditText>(R.id.sms_send_edit)


    }



    fun onSms(v: View)
    {
        val edit_Number = findViewById<EditText>(R.id.edit_text_number).text.toString()
        val phoneNo: String = edit_Number
        val sms_edit =findViewById<EditText>(R.id.sms_send_edit).text.toString()
        val toSms: String = "smsto:" + edit_Number
        val messageText : String = sms_edit
        val sms = Intent(Intent.ACTION_SENDTO, Uri.parse(toSms))
        sms.putExtra("sms_body", messageText);
        startActivity(sms);
        SmsManager.getDefault().sendTextMessage(phoneNo, null,
            messageText.toString(), null, null);
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun onCall(view: View)
    {
        val mPhoneNoEt = findViewById<EditText>(R.id.edit_text_number)
        val phoneNo: String = mPhoneNoEt.text.toString()
        if(!TextUtils.isEmpty(phoneNo))
        {
            val dial: String = "tel:" + phoneNo
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    performAction(...)
                }
                shouldShowRequestPermissionRationale(...)
                -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    showInContextUI(...)
                }
                else -> {
                    // You can directly ask for the permission.
                    requestPermissions(
                        CONTEXT,
                        arrayOf(Manifest.permission.REQUESTED_PERMISSION),
                        REQUEST_CODE
                    )
                }
            }
            if (ContextCompat.checkSelfPermission(this, Intent.ACTION_CALL) == PackageManager.PERMISSION_DENIED) {
                // Requesting the permission
                requestPermissions(this, arrayOf(Intent.ACTION_CALL), 1)
            } else {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
        else {
            Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show()
        }
    }


}