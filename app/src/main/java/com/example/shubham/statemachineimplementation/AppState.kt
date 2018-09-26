package com.example.shubham.statemachineimplementation

interface AppState {
    fun onAction(action:MyActions)
}