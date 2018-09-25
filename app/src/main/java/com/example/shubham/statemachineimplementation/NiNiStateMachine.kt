package com.example.shubham.statemachineimplementation

import android.content.Context
import android.util.Log
import kotlin.properties.Delegates

class NiNiStateMachine(val context:Context) {
    var LOG_TAG = NiNiStateMachine::class.java.canonicalName
    private var mNiniState :  NiniState by Delegates.observable<NiniState>(InitialState()) {_, oldValue, newValue ->
        myStateChanged(oldValue,newValue)
    }

    fun myStateChanged(oldState:NiniState,newState:NiniState){
        Log.d(LOG_TAG,"${oldState::class.java.canonicalName}-- to --${newState::class.java.canonicalName}")
    }

    fun setState(newState: NiniState){
        mNiniState = newState
    }

    fun getState():NiniState{
        return mNiniState
    }

    inner class InitialState():NiniState{
        override fun onAction(action: NiNiActions) {
            when(action){
                is NiNiActions.Initial->{
                    Log.d(LOG_TAG,"Action::Intial Actions recieved")
                    mNiniState = OnNoInternetConnection()
                }
            }
        }

    }

    inner class OnNoInternetConnection():NiniState{
        private val LOG_TAG = OnNoInternetConnection::class.java.canonicalName
        override fun onAction(action: NiNiActions) {
         when(action){
             is NiNiActions.ConnectToCloud ->{
                 Log.d(LOG_TAG,"Action connect to cloud")
             }
         }
        }

    }
}