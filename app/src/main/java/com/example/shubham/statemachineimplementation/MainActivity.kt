package com.example.shubham.statemachineimplementation

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private final val LOG_TAG = MainActivity::class.java.canonicalName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /**
         * Abstract Factory Implementation for Connection Switch
         * ***/

        val connection = AppConnection(this@MainActivity)
        connection.publish()
        connection.connect(ConnectionState.CLOUD)
        connection.publish()
        connection.switchToLocal()
        connection.publish()
        connection.disconnect()
        connection.switchToCloud()
        connection.publish()
        connection.disconnect()
        connection.publish()


        /**
         *State Machine Implementation
         * **/
        val appStateMachine = AppStateMachine(this@MainActivity)
        appStateMachine.getState().onAction(MyActions.Initial())
        appStateMachine.getState().onAction(MyActions.ConnectToCloud())

    }


    object ConnectionState{
        val CLOUD = 0
        val LOCAL = 1
    }

    class AppConnection(private val context: Context){
        private var cloudConnection: CloudConnection
        private var localConnection: LocalConnection
        private var curConnection : Connection
        private var connectionType:Int = 0

        init {
            curConnection = NoConnection

            val cloudConnectionAbstractFactoryImplementation = ConnectionAbstractFactoryImplementation.createFactory<CloudConnection>()
            cloudConnection = cloudConnectionAbstractFactoryImplementation.createConnection() as CloudConnection

            val localConnectionAbstractFactoryImplementation = ConnectionAbstractFactoryImplementation.createFactory<LocalConnection>()
            localConnection = localConnectionAbstractFactoryImplementation.createConnection() as LocalConnection

        }


       fun publish(){
           curConnection.publish()
       }

        fun connect(connectionType:Int){
          this.connectionType = connectionType
          when(this.connectionType){
              ConnectionState.CLOUD->{
                  cloudConnection.connect()
                  curConnection = cloudConnection
              }
              ConnectionState.LOCAL->{
                  localConnection.connect()
                  curConnection = localConnection
              }
          }
        }

        fun disconnect(){
            curConnection.disconnect()
            curConnection = NoConnection
        }

        fun switchToLocal(){
            if(curConnection is CloudConnection){
                cloudConnection.disconnect()
                curConnection = NoConnection
            }

            if(curConnection !is LocalConnection){
                curConnection = localConnection
                curConnection.connect()
                connectionType = ConnectionState.LOCAL
            }
        }

        fun switchToCloud(){
            if(curConnection is LocalConnection){
                localConnection.disconnect()
                curConnection = NoConnection
            }

            if(curConnection !is CloudConnection){
                curConnection = cloudConnection
                curConnection.connect()
                connectionType = ConnectionState.CLOUD
            }
        }
    }


}
