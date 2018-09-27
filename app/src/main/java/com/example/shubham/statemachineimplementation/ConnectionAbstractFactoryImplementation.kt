package com.example.shubham.statemachineimplementation

import android.util.Log
import java.lang.IllegalArgumentException


interface Connection

class LocalConnection :Connection{
    fun connect(){
        Log.d("Local Connection","Connected")
    }

    fun disconnect(){
        Log.d("Local Connection","Disconnected")
    }
}
class CloudConnection :Connection{
    fun connect(){
        Log.d("Cloud Connection","Connected")
    }

    fun disconnect(){
        Log.d("Cloud Connection","Disconnection")
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