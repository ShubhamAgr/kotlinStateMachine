package com.example.shubham.statemachineimplementation

import java.lang.IllegalArgumentException


interface Connection

class LocalConnection :Connection
class CloudConnection :Connection

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