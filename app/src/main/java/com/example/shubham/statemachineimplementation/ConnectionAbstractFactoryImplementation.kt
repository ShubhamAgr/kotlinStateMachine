package com.example.shubham.statemachineimplementation

import android.util.Log
import java.lang.IllegalArgumentException


interface Connection{
    fun connect()
    fun disconnect()
    fun publish()
}

object NoConnection:Connection{
    override fun connect() {
        Log.d("NO Connection","Unable to connect")
    }

    override fun disconnect() {
        Log.d("No Connection","unable to disconnect")
    }

    override fun publish() {
        Log.d("No Connection","unable to publish")
    }

}

class LocalConnection :Connection{
    override fun publish() {
        Log.d("Local Connection","Publish Data")
    }

    override fun connect(){
        Log.d("Local Connection","Connected")
    }

    override fun disconnect(){
        Log.d("Local Connection","Disconnected")
    }
}


class CloudConnection :Connection{
    override fun publish() {
        Log.d("Cloud Connection","Publish Data")
    }

    override fun connect(){
        Log.d("Cloud Connection","Connected")
    }

    override fun disconnect(){
        Log.d("Cloud Connection","Disconnected")
    }
}

abstract class ConnectionAbstractFactoryImplementation {
  abstract fun createConnection(): Connection
    companion object {
        inline  fun <reified T:Connection> createFactory():ConnectionAbstractFactoryImplementation = when(T:: class.java){
            LocalConnection::class.java -> LocalConnectionFactory()
            CloudConnection::class.java -> CloudConnectionFactory()
            else -> throw  IllegalArgumentException()
        }
    }
}

class LocalConnectionFactory:ConnectionAbstractFactoryImplementation(){
    override fun createConnection(): Connection = LocalConnection()
}

class CloudConnectionFactory:ConnectionAbstractFactoryImplementation(){
    override fun createConnection(): Connection = CloudConnection()

}