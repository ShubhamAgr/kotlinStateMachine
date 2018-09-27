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
        val Connection = Connection(this@MainActivity)
        Connection.connect(ConnectionState.CLOUD)
        Connection.switchToLocal()
        Connection.disconnect()
        Connection.switchToCloud()
        Connection.disconnect()



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

    class Connection(private val context: Context){
        private var cloudConnection: CloudConnection
        private var localConnection: LocalConnection
        private var connectionType:Int = 0

        init {
            val cloudConnectionAbstractFactoryImplementation = ConnectionAbstractFactoryImplementation.createFactory<CloudConnection>()
            cloudConnection = cloudConnectionAbstractFactoryImplementation.createConnection() as CloudConnection

            val localConnectionAbstractFactoryImplementation = ConnectionAbstractFactoryImplementation.createFactory<LocalConnection>()
            localConnection = localConnectionAbstractFactoryImplementation.createConnection() as LocalConnection

        }

            fun connect(connectionType:Int){
                this.connectionType = connectionType
                  when(this.connectionType){
                      ConnectionState.CLOUD->{
                          cloudConnection.connect()
                      }
                      ConnectionState.LOCAL->{
                          localConnection.connect()
                      }
                   }
            }

            fun disconnect(){
                when(connectionType){
                    ConnectionState.CLOUD->{
                        cloudConnection.disconnect()
                        connectionType = Int.MAX_VALUE
                    }
                    ConnectionState.LOCAL->{
                        localConnection.disconnect()
                        connectionType = Int.MAX_VALUE
                    }
                }
            }

            fun switchToLocal(){
                if(connectionType == ConnectionState.CLOUD){
                    cloudConnection.disconnect()
                }
                if(connectionType != ConnectionState.LOCAL){
                    localConnection.connect()
                    connectionType = ConnectionState.LOCAL
                }
            }

            fun switchToCloud(){
                if(connectionType == ConnectionState.LOCAL){
                    localConnection.disconnect()
                }
                if(connectionType != ConnectionState.CLOUD){
                    cloudConnection.connect()
                    connectionType = ConnectionState.CLOUD
                }
            }
    }


}
