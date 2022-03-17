package com.example.socketclienttest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread {
            ruSn()
        }.start()

    }

    override fun onResume() {
        super.onResume()
        Thread {
            ruSn()
        }.start()

    }
    
    private fun ruSn() {
        Log.e("mTag","Yes  ")
        var dstAddress: String
        var dstPort: Int
        var response = ""
        val msgToServer = "Yes code"
        var socket: Socket? = null
        var dataOutputStream: DataOutputStream? = null
        var dataInputStream: DataInputStream? = null
        try {
            socket = Socket("192.168.1.100", 8080)
            dataOutputStream = DataOutputStream(socket.getOutputStream())
            dataInputStream = DataInputStream(socket.getInputStream())
            if (msgToServer != null) {
                dataOutputStream.writeUTF(msgToServer)
            }
            response = dataInputStream.readUTF()
            Log.e("mTag", "response $response")
        } catch (e: UnknownHostException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            response = "UnknownHostException: $e"
            Log.e("mTag", "response ex $response")
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            response = "IOException: $e"
            Log.e("mTag", "response ex $response")

        } finally {
            if (socket != null) {
                try {
                    socket.close()
                } catch (e: IOException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    Log.e("mTag", "response ex 2 $e")
                }
            }
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close()
                } catch (e: IOException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    Log.e("mTag", "response ex 3 $e")
                }
            }
            if (dataInputStream != null) {
                try {
                    dataInputStream.close()
                } catch (e: IOException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    Log.e("mTag", "response ex 4 $e")
                }
            }
        }
    }

}