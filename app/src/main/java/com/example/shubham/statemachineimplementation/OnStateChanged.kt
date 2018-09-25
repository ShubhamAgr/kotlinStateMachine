package com.example.shubham.statemachineimplementation

interface OnStateChanged {
    fun consume(prevState:States,newState:States)
}