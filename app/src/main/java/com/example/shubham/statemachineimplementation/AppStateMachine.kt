package com.example.shubham.statemachineimplementation

import android.content.Context
import android.util.Log
import kotlin.properties.Delegates

class AppStateMachine(val context:Context) {
    var LOG_TAG = AppStateMachine::class.java.canonicalName
    private var mNiniState :  AppState by Delegates.observable<AppState>(InitialState()) { _, oldValue, newValue ->
        myStateChanged(oldValue,newValue)
    }

    fun myStateChanged(oldState:AppState, newState:AppState){
        Log.d(LOG_TAG,"${oldState::class.java.canonicalName}-- to --${newState::class.java.canonicalName}")
    }

    fun setState(newState: AppState){
        mNiniState = newState
    }

    fun getState():AppState{
        return mNiniState
    }

    inner class InitialState():AppState{
        override fun onAction(action: MyActions) {
            when(action){
                is MyActions.Initial->{
                    Log.d(LOG_TAG,"Action::Intial Actions recieved")
                    mNiniState = OnNoInternetConnection()
                }
            }
        }

    }

    inner class OnNoInternetConnection():AppState{
        private val LOG_TAG = OnNoInternetConnection::class.java.canonicalName
        override fun onAction(action: MyActions) {
         when(action){
             is MyActions.ConnectToCloud ->{
                 Log.d(LOG_TAG,"Action connect to cloud")
             }
         }
        }

    }
}