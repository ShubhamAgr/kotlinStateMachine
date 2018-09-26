package com.example.shubham.statemachineimplementation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private final val LOG_TAG = MainActivity::class.java.canonicalName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val localConnectionAbstractFactoryImplementation = ConnectionAbstractFactoryImplementation.createFactory<LocalConnection>()
        val cloudConnectionAbstractFactoryImplementation = ConnectionAbstractFactoryImplementation.createFactory<CloudConnection>()

        val localConnection = localConnectionAbstractFactoryImplementation.createConnection()
        val cloudConnection = cloudConnectionAbstractFactoryImplementation.createConnection()
        Log.d(LOG_TAG,"local connection object ${localConnection}")
        Log.d(LOG_TAG,"cloud connection object ${cloudConnection}")


        val appStateMachine = AppStateMachine(this@MainActivity)
        appStateMachine.getState().onAction(MyActions.Initial())
        appStateMachine.getState().onAction(MyActions.ConnectToCloud())

    }


}
